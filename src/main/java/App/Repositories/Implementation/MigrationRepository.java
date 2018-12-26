package App.Repositories.Implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import App.Helpers.Column;
import App.Helpers.ForeignKey;
import App.Helpers.Index;
import oracle.sql.DATE;

public class MigrationRepository {
    private final String oracleConnectionString = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String oracleUsername = "BP20";
    private final String oraclePassword = "bppassword";

    private final String mysqlConnectionString = "jdbc:mysql://127.0.0.1:3306/test_schema";
    private final String mysqlUsername = "root";
    private final String mysqlPassword = "password4root";

    public DatabaseMetaData getMetadata() throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        DatabaseMetaData md = null;

        try
        {
            con = DriverManager.getConnection( oracleConnectionString, oracleUsername, oraclePassword );
            md = con.getMetaData();
        }
        catch (Exception e)
        {

        }
        return md;
    }


    public ResultSet getOracleSchema() throws ClassNotFoundException, SQLException
    {
        ResultSet schema = null;

        try{
            DatabaseMetaData md = getMetadata();

            String[] objectTypes = {"TABLE"};

            schema = md.getTables(oracleConnectionString, "BP20", "%", objectTypes);

            return schema;

        }catch (SQLException e)
        {
            System.out.println("Exception 0 : " + e.getMessage());

        }

        return schema;
    }

    public Map<String, ArrayList<Column>> getTables() throws SQLException
    {
        Map<String, ArrayList<Column>> tables = new TreeMap<>();

        try
        {
            ResultSet cursor = getOracleSchema();
            while(cursor.next()) {
                String tableName = cursor.getString(3);
                System.out.println("Table Name: " + tableName);
                tables.put(tableName, getColumns(tableName));

            }
            cursor.close();

            return tables;

        }catch(Exception e)
        {
            System.out.println("Exception 1: " + e.getMessage());
        }

        return tables;

    }

    private String adjustColumnType(String columnType) {
        System.out.println("." + columnType + ".");
        if(columnType.equals("DATE"))
            return "DATE";
        else if(columnType.equals("NUMBER"))
            return "NUMERIC";
        else if(columnType.contains("VARCHAR2"))
            return columnType.replace("VARCHAR2", "VARCHAR");
        else if(columnType.contains("CLOB"))
            return columnType.replace("CLOB", "TEXT");
        return columnType;
    }

    public ArrayList<Column> getColumns(String tableName) {
        ArrayList<Column> tableColumns =  new ArrayList<Column>();
        try {
            DatabaseMetaData md = getMetadata();
            ResultSet columns = md.getColumns(null, "%", tableName, "%");
            while(columns.next()) {
                Column column = new Column();
                column.setName(columns.getString("COLUMN_NAME"));
                column.setDataType(adjustColumnType(columns.getString("TYPE_NAME")));
                if(columns.getString("TYPE_NAME").equals("VARCHAR2"))
                    System.out.println("Column size : " + columns.getInt("COLUMN_SIZE"));
                column.setDataSize(columns.getInt("COLUMN_SIZE"));
                column.setDigits(columns.getInt("DECIMAL_DIGITS"));
                column.setNullable(columns.getInt("NULLABLE"));
                column.setIsNull( (columns.getInt("NULLABLE") == 1) ? "NULL" : "NOT NULL");
                tableColumns.add(column);
            }
            columns.close();
            return tableColumns;
        } catch( Exception e ) {
            System.out.println("Exception 2: " + e.getMessage());
        }
        return tableColumns;
    }

    public ArrayList<ForeignKey> getForeignKeys(String tableName) {
        ArrayList<ForeignKey> tableKeys = new ArrayList<>();
        try {
            DatabaseMetaData md = getMetadata();
            ResultSet keys = md.getImportedKeys("%", "BP20", tableName);
            while(keys.next()) {
                ForeignKey fk = new ForeignKey();
                fk.setFKName(keys.getString("FK_NAME"));
                fk.setFKTableName(keys.getString("FKTABLE_NAME"));
                fk.setFKColumnName(keys.getString("FKCOLUMN_NAME"));
                fk.setPKTableName(keys.getString("PKTABLE_NAME"));
                fk.setPKColumnName(keys.getString("PKCOLUMN_NAME"));
                tableKeys.add(fk);
            }
            keys.close();
        } catch (Exception e) {
            System.out.println("Foreign Key Exception: " + e.getMessage());
        }
        return tableKeys;
    }

    public ArrayList<Index> getIndexes(String tableName) {
        ArrayList<Index> tableIndexes = new ArrayList<>();
        try {
            DatabaseMetaData md = getMetadata();
            ResultSet indexes = md.getIndexInfo("%", "BP20", tableName,
                    false, true);
            while (indexes.next()) {
                Index index = new Index();
                index.setUnique(indexes.getString("NON_UNIQUE"));
                index.setName(indexes.getString("INDEX_NAME"));
                index.setType(indexes.getString("TYPE"));
                index.setPosition(indexes.getInt("ORDINAL_POSITION"));
                index.setColumnName(indexes.getString("COLUMN_NAME"));
                index.setAscending(indexes.getString( "ASC_OR_DESC"));
                tableIndexes.add(index);
            }
            indexes.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tableIndexes;
    }

    public ArrayList<String> migrateTriggers() {
        ArrayList<String> sqlStatements = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(oracleConnectionString, oracleUsername, oraclePassword);
            Statement triggerStatement = con.createStatement();
            ResultSet triggers = triggerStatement.executeQuery("SELECT * FROM ALL_TRIGGERS WHERE owner='BP20'");
            while(triggers.next()) {
                if(!triggers.getString("TRIGGER_NAME").contains("_ID_")) {
                    String dropTriggerStatement = "";
                    String sqlStatement = "";
                    dropTriggerStatement += "DROP TRIGGER IF EXISTS " + triggers.getString("TRIGGER_NAME") + ";";
                    sqlStatements.add(dropTriggerStatement);
                    sqlStatement += "CREATE TRIGGER ";
                    sqlStatement += triggers.getString("DESCRIPTION").toUpperCase();
                    sqlStatement += "FOR EACH ROW \n";
                    String body = triggers.getString("TRIGGER_BODY");
                    int beginIndex = body.indexOf("BEGIN");
                    String declareStetement = body.substring(0, beginIndex);
                    body = body.substring(beginIndex, body.length());
                    body = body.substring(0, 5) + "\n" + declareStetement + body.substring(5, body.length());
                    sqlStatement += body.toUpperCase().replace("VARCHAR2", "VARCHAR");
                    sqlStatements.add(sqlStatement);
                }
            }
            triggers.close();
        } catch (Exception e) {
            System.out.println("Trigger exception: " + e.getMessage());
        }
        return sqlStatements;
    }

    public ArrayList<String> migrateProcedures() {
        ArrayList<String> sqlStatements = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(oracleConnectionString, oracleUsername, oraclePassword);
            Statement procedureStatement = con.createStatement();
            ResultSet procedures = procedureStatement.executeQuery("SELECT * FROM ALL_OBJECTS WHERE OWNER='BP20' AND OBJECT_TYPE='PROCEDURE'");
            while(procedures.next()) {
                String procedureName = procedures.getString("OBJECT_NAME");
                Statement procedureBodyStatement = con.createStatement();
                String query = "SELECT * FROM all_source WHERE owner='BP20' AND type='PROCEDURE'" + " AND NAME='" + procedureName.toUpperCase() + "'";
                ResultSet proceduresBody = procedureBodyStatement.executeQuery(query);
                String sqlStatement = "";
                String dropStatement = "";
                dropStatement = "DROP PROCEDURE IF EXISTS " + procedureName + ";";
                sqlStatements.add(dropStatement);
                sqlStatement += "CREATE ";
                while(proceduresBody.next()) {
                    String parameters = "";
                    String body = proceduresBody.getString("TEXT");
                    if( body.contains("procedure") ) {
                        parameters = body.substring( body.indexOf("("), body.indexOf(')'));
                        int parameterIN = parameters.indexOf("IN ");
                        parameters = "(IN " + parameters.substring(1, parameterIN) + parameters.substring(parameterIN + 3, parameters.length());
                        body = body.substring (0, body.indexOf("(")) + parameters + body.substring(body.indexOf(")"), body.length());
                        sqlStatement += body.replace("NUMBER", "NUMERIC");
                    } else {
                        sqlStatement += body;
                    }
                }
                proceduresBody.close();
                sqlStatements.add(sqlStatement);
            }
            procedures.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Procedures exception: " + e.getMessage());
        }
        return sqlStatements;
    }

    public void migrateProceduresToMySQL(ArrayList<String> sqlStatements) {
        Connection con = null;
        Statement tStatement = null;
        try
        {
            con = DriverManager.getConnection( mysqlConnectionString, mysqlUsername, mysqlPassword );
            //Transactional part
            //tStatement = con.createStatement();
            //Start Transaction
            //Set Autocommit OFF
            //tStatement.executeQuery("SET autocommit = OFF;");
            con.setAutoCommit(false);
            //tStatement.executeQuery("START TRANSACTION;");

            for (String sqlStatement: sqlStatements) {

                Statement query = con.createStatement();
                System.out.println(sqlStatement);
                query.execute(sqlStatement);
            }
            //Migration Finished, time for commit
            //tStatement.executeQuery("COMMIT;");
            con.commit();
        }
        catch (Exception e)
        {
            try {
                //If Any of above queries fail;
                //tStatement.executeQuery("ROLLBACK;");
                con.rollback();
                System.out.println("Procedure migration failed, executing rollback");
                System.out.println(e.getMessage());
            } catch (Exception e1) {
                // Who cares?
            }
        }
    }

    public void migrateTriggersToMySQL(ArrayList<String> sqlStatements) {
        Connection con = null;
        Statement tStatement = null;
        try
        {
            con = DriverManager.getConnection( mysqlConnectionString, mysqlUsername, mysqlPassword );
            //Transactional part
            //tStatement = con.createStatement();
            //Start Transaction
            //Set Autocommit OFF
            //tStatement.executeQuery("SET autocommit = OFF;");
            con.setAutoCommit(false);
            //tStatement.executeQuery("START TRANSACTION;");

            for (String sqlStatement: sqlStatements) {

                Statement query = con.createStatement();

                System.out.println(sqlStatement);
                System.out.println("-");
                query.execute(sqlStatement);
            }
            //Migration Finished, time for commit
            //tStatement.executeQuery("COMMIT;");
            con.commit();
        }
        catch (Exception e)
        {
            try {
                //If Any of above queries fail;
                //tStatement.executeQuery("ROLLBACK;");
                con.rollback();
                System.out.println("Trigger migration failed, executing rollback");
                System.out.println(e.getMessage());
            } catch (Exception e1) {
                // Who cares?
                System.out.println("Jel' se ovo desilo");
            }
        }
    }

    public ArrayList<String> migrateIndexesToMySQL(Map<String, ArrayList<Column>> tables) {
        ArrayList<String> sqlStatements = new ArrayList<>();
        for(String tableName : tables.keySet()) {
            ArrayList<Index> tableIndexes = getIndexes(tableName);
            for(Index index: tableIndexes) {
                String sqlStatement = "";
                sqlStatement +="CREATE INDEX ";
                sqlStatement += index.getName();
                sqlStatement += " ON " + tableName;
                sqlStatement += " (" + index.getColumnName() + ");";
                if(index.getName() == null  || index.getName().contains("SYS"))
                    continue;
                sqlStatements.add(sqlStatement);
            }
        }
        return sqlStatements;
    }


    public ArrayList<String> migrateTablesToMySQL(Map<String, ArrayList<Column>> tables)
    {
        ArrayList<String> sqlStatements = new ArrayList<String>();

        for(String tableName : tables.keySet()) {
            String sqlStatement = "";

            sqlStatement += "CREATE TABLE ";
            sqlStatement += tableName;
            sqlStatement += "(";
            ArrayList<Column> columns = tables.get(tableName);
            for(Column column : columns) {
                sqlStatement += column.getName() + " " + column.getDataType();
                if(column.getDataType().equals("VARCHAR"))
                    sqlStatement += "(" + column.getDataSize() + ")";
                sqlStatement += " " + column.getIsNull() +",";
            }
            sqlStatement = sqlStatement.substring(0, sqlStatement.length()-1);
            sqlStatement += ", PRIMARY KEY(ID) );";

            sqlStatements.add(sqlStatement);
        }

        return sqlStatements;
    }

    public void migrateTableData(Map<String, ArrayList<Column>> tables) {
        Map<String, ArrayList<Column>> data = new TreeMap<>();
        Connection con = null;
        Connection con2 = null;

        Statement tStatement = null;
        try {
            con = DriverManager.getConnection(oracleConnectionString, oracleUsername, oraclePassword);
            con2 = DriverManager.getConnection(mysqlConnectionString, mysqlUsername, mysqlPassword);

            //Transactional part
            //tStatement = con2.createStatement();
            //Start Transaction
            //tStatement.executeQuery("START TRANSACTION;");
            //Set Autocommit OFF
            //tStatement.executeQuery("SET autocommit = 0;");
            con2.setAutoCommit(false);

            for (String table : tables.keySet()) {
                Statement query = con.createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM " + table);
                Statement mySQL_Statement = con2.createStatement();
                String mySQL_Query = "INSERT INTO " + table + "(";

                for (Column column : tables.get(table)) {

                    mySQL_Query += column.getName() + ",";

                }

                mySQL_Query = mySQL_Query.substring(0, mySQL_Query.length() - 1);

                mySQL_Query += ") VALUES ";


                while (rs.next()) {
                    String valuesQuery = "(";
                    for (Column column : tables.get(table)) {

                        if (column.getDataType().equals("NUMERIC")) valuesQuery += rs.getInt(column.getName());
                        else if (column.getDataType().equals("VARCHAR"))
                            valuesQuery += "'" + rs.getString(column.getName()) + "'";
                        else if (column.getDataType().equals("DATE"))
                            valuesQuery += "'" + rs.getDate(column.getName()) + "'";
                        else if (column.getDataType().equals("BLOB")) valuesQuery += rs.getBlob(column.getName());
                        else if (column.getDataType().equals("TEXT"))
                            valuesQuery += "'" + rs.getString(column.getName()) + "'";

                        valuesQuery += ", ";

                    }
                    valuesQuery = valuesQuery.substring(0, valuesQuery.length() - 2);
                    valuesQuery += ");";
                    System.out.println(mySQL_Query + valuesQuery);
                    mySQL_Statement.executeUpdate(mySQL_Query + valuesQuery);
                }
            }
            //Migration finished, time for commit
            //tStatement.executeQuery("COMMIT;");
            //Additional query just to test if fail and rollback works fine
            //tStatement.executeQuery("INSERT INTO RandomTabela (id, nesto) VALUES (0, 'nece raditi') ");
            con2.commit();
        } catch (Exception e) {
            try {
                //If Any of above queries fail;
                //tStatement.executeQuery("ROLLBACK;");
                con2.rollback();
            } catch (Exception e1) {
                // Who cares?
            }
            System.out.println("EXCEPTION MIGRATE DATA" + e.getMessage());
        }


    }

    public void migrateTables(ArrayList<String> sqlStatements)
    {
        Connection con = null;
        Statement tStatement = null;
        try
        {
            con = DriverManager.getConnection( mysqlConnectionString, mysqlUsername, mysqlPassword );
            //Transactional part
            //tStatement = con.createStatement();
            //Start Transaction
            //Set Autocommit OFF
            //tStatement.executeQuery("SET autocommit = OFF;");
            con.setAutoCommit(false);
            //tStatement.executeQuery("START TRANSACTION;");

            for (String sqlStatement: sqlStatements) {

                Statement query = con.createStatement();

                System.out.println(sqlStatement);

                query.execute(sqlStatement);
            }
            //Migration Finished, time for commit
            //tStatement.executeQuery("COMMIT;");
            con.commit();
        }
        catch (Exception e)
        {
            try {
                //If Any of above queries fail;
                //tStatement.executeQuery("ROLLBACK;");
                con.rollback();
                System.out.println("Opalio sam rollback");
                System.out.println(e.getMessage());
            } catch (Exception e1) {
                // Who cares?
                System.out.println("Jel' se ovo desilo");
            }
        }
    }

    public ArrayList<String> migrateForeignKeysToMySQL(Map<String, ArrayList<Column>> tables) {
        ArrayList<String> sqlStatements = new ArrayList<>();
        for(String tableName: tables.keySet()) {
            ArrayList<ForeignKey> foreignKeys = getForeignKeys(tableName);
            for(ForeignKey fk : foreignKeys) {
                String sqlStatement = "";
                sqlStatement += "ALTER TABLE ";
                sqlStatement += fk.getFKTableName();
                sqlStatement += " ADD CONSTRAINT ";
                sqlStatement += fk.getFKName();
                sqlStatement += " FOREIGN KEY (";
                sqlStatement += fk.getFKColumnName();
                sqlStatement += ") REFERENCES ";
                sqlStatement += fk.getPKTableName();
                sqlStatement += "(" + fk.getPKColumnName() + ");";
                sqlStatements.add(sqlStatement);
            }
        }
        return sqlStatements;
    }

    public void migrateForeignKeys(ArrayList<String> sqlStatements) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(mysqlConnectionString, mysqlUsername, mysqlPassword);
            con.setAutoCommit(false);
            for (String sqlStatement: sqlStatements) {
                Statement query = con.createStatement();
                System.out.println(sqlStatement);
                query.execute(sqlStatement);
            }
            con.commit();
        } catch (Exception e) {
            try {
                //Rollback;
                System.out.println("Ocekujemo da me lupi ovdje");
                con.rollback();
            } catch (Exception e2) {
                //Who cares
            }
            System.out.println("Migrate foreign keys exception: " + e.getMessage());
        }
    }

    public void migrateIndexes(ArrayList<String> sqlStatements) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(mysqlConnectionString, mysqlUsername, mysqlPassword);
            con.setAutoCommit(false);
            for (String sqlStatement: sqlStatements) {
                Statement query = con.createStatement();
                System.out.println(sqlStatement);
                query.execute(sqlStatement);
            }
            con.commit();
        } catch (Exception e) {
            try {
                //Rollback;
                con.rollback();
            } catch (Exception e2) {
                //Who cares
            }
            System.out.println("Migrate indexes exception: " + e.getMessage());
        }
    }

}

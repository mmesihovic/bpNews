package App.Repositories.Implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import App.Helpers.Column;
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

    public void migrateTableData(Map<String, ArrayList<Column>> tables)
    {
        Map<String, ArrayList<Column>> data = new TreeMap<>();
        Connection con = null;
        Connection con2 = null;

        try {
            con = DriverManager.getConnection(oracleConnectionString, oracleUsername, oraclePassword);
            con2 = DriverManager.getConnection(mysqlConnectionString, mysqlUsername, mysqlPassword);


            for (String table : tables.keySet()) {
                Statement query = con.createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM " + table);
                Statement mySQL_Statement = con2.createStatement();
                String mySQL_Query = "INSERT INTO " + table + "(";

                for (Column column: tables.get(table)) {

                    mySQL_Query += column.getName() + ",";

                }

                mySQL_Query = mySQL_Query.substring(0, mySQL_Query.length()-1);

                mySQL_Query += ") VALUES ";


                while(rs.next())
                {
                    String valuesQuery = "(";
                    for (Column column: tables.get(table)) {

                        if (column.getDataType().equals("NUMERIC"))
                        {
                            valuesQuery += rs.getInt(column.getName());
                        }
                        else if(column.getDataType().equals("VARCHAR")) valuesQuery += "'" + rs.getString(column.getName()) + "'";
                        else if(column.getDataType().equals("DATE")) valuesQuery += "'" + rs.getDate(column.getName()) + "'";
                        else if(column.getDataType().equals("BLOB")) valuesQuery += rs.getBlob(column.getName());
                        else if(column.getDataType().equals("TEXT")) valuesQuery += "'" + rs.getString(column.getName()) + "'";

                        valuesQuery += ", ";

                    }
                    valuesQuery = valuesQuery.substring(0, valuesQuery.length()-2);
                    valuesQuery += ");";
                    System.out.println(mySQL_Query + valuesQuery);
                    mySQL_Statement.executeUpdate(mySQL_Query + valuesQuery);
                }
            }
        }catch (Exception e)
        {
            System.out.println("EXCEPTION MIGRATE DATA" + e.getMessage());
        }



    }



    public void migrateTables(ArrayList<String> sqlStatements)
    {
        Connection con = null;



        try
        {
            con = DriverManager.getConnection( mysqlConnectionString, mysqlUsername, mysqlPassword );

            for (String sqlStatement: sqlStatements) {

                Statement query = con.createStatement();

                System.out.println(sqlStatement);

                query.execute(sqlStatement);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void migrateData()
    {

    }

}

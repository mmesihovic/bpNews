package App.Repositories.Implementation;

import java.sql.*;

public class MetaDataRepository {
   private final String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String username = "BP20";
    private final String password = "bppassword";


    public DatabaseMetaData getMetadata() throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        DatabaseMetaData md = null;

        try
        {
            con = DriverManager.getConnection( connectionString, username, password );
            md = con.getMetaData();
        }
        catch (Exception e)
        {

        }
        return md;
    }

    public void getSchema() throws ClassNotFoundException, SQLException{
        DatabaseMetaData md = getMetadata();

        ResultSet schema = null;
        String[] objectTypes = {"TABLE"};
        try {
            schema = md.getTables(connectionString, "BP20", "%", objectTypes);
            while (schema.next()) {
                String dbName = schema.getString(1);
                String tableNames = schema.getString(3);
                String tableTypes = schema.getString(4);
                System.out.println(tableNames + " " + tableTypes);
                if (tableTypes.equalsIgnoreCase("TABLE"))
                    getTables(md, tableNames);
                else if (tableTypes.equalsIgnoreCase("VIEW"))
                    getViews(md, tableNames);
            }
            schema.close();
            getProcedures(md, connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTables(DatabaseMetaData md, String tableName) {
        getColumns(md, tableName);
        getPrimaryKeys(md, tableName);
        getIndexes(md, tableName);
    }

    public void getViews(DatabaseMetaData md, String tableName) {
        getColumns(md, tableName);
    }

    public void getColumns(DatabaseMetaData md, String tableName) {
        try {
            ResultSet columns = md.getColumns(null, "%", tableName, "%");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String datatype = columns.getString("TYPE_NAME");
                int datasize = columns.getInt("COLUMN_SIZE");
                int digits = columns.getInt("DECIMAL_DIGITS");
                int nullable = columns.getInt("NULLABLE");
                boolean isNull = (nullable == 1);
                String nul;
                if (isNull) nul = "NULL";
                else nul = "NOT NULL";
                System.out.println(" " + columnName + " "+ datatype
                        + "(" + datasize + "," + digits + ") "
                        + nul);
            }
            columns.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getPrimaryKeys(DatabaseMetaData md, String tableName) {
        try {
            ResultSet primaryKeys = md.getPrimaryKeys("Test2", "%",
                    tableName);
            while (primaryKeys.next()) {
                System.out.println(" "
                        + primaryKeys.getInt("KEY_SEQ") + " "
                        + primaryKeys.getString("COLUMN_NAME"));
            }
            primaryKeys.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getIndexes(DatabaseMetaData md, String imeTabele) {
        try {
            ResultSet indexes = md.getIndexInfo("Test2", "%", imeTabele,
                    false, true);
            while (indexes.next()) {
                String unique = indexes.getString("NON_UNIQUE");
                String name = indexes.getString("INDEX_NAME");
                String type = indexes.getString("TYPE");
                int position = indexes.getInt("ORDINAL_POSITION");
                String column = indexes.getString("COLUMN_NAME");
                String asc = indexes.getString("ASC_OR_DESC");
                System.out.println(" " + unique + " " + name + " "
                        + position + " " + column + " " + asc);
            }
            indexes.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getProcedures(DatabaseMetaData md, String databaseConnection) {
        try {
            ResultSet rsProcedure = md.getProcedures(databaseConnection, null, "%");
            while (rsProcedure.next()) {
                String procedureName = rsProcedure.getString("PROCEDURE_NAME");
                String remarks = rsProcedure.getString("REMARKS");
                System.out.print(" "+ procedureName + " " + remarks);
                getProcedureColumns(md, databaseConnection, procedureName);
            }
            rsProcedure.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getProcedureColumns(DatabaseMetaData md, String databaseConnection, String procedure) {
        try {
            ResultSet rsKolone = md.getProcedureColumns("%", null, procedure,
                    "%");
            getResultSetProperty(rsKolone);
            while (rsKolone.next()) {
                String procedureName = rsKolone.getString("PROCEDURE_NAME");
                String remarks = rsKolone.getString("REMARKS");
                System.out.print(" "+procedureName + " " + remarks);
                getProcedureColumns(md, databaseConnection, procedureName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getResultSetProperty(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            for (int i=1; i<= colCount; i++) {
                System.out.println(i + ". " + rsmd.getColumnClassName(i)
                        + " " + rsmd.getColumnName(i) + " "
                        + rsmd.getColumnType(i) + " "
                        + rsmd.getColumnTypeName(i) + " "
                        + rsmd.getPrecision(i));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connectionString, username, password);
    }
}



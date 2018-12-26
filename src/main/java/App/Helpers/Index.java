package App.Helpers;

public class Index {
    private String name;
    private String type;
    private String columnName;
    private String unique;
    private int position;
    private String ascending;

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAscending() {
        return ascending;
    }

    public void setAscending(String ascending) {
        this.ascending = ascending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    //Currently not needed
    /*public String getTypeAsString() {
        switch(type) {
            case 1:
                return "tableIndexStatistic";
            case 2:
                return "tableIndexClustered";
            case 3:
                return "tableIndexHashed";
            case 4:
                return "tableIndexOther";
            default:
                return "";
        }
    }*/
}

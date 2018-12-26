package App.Helpers;

public class ForeignKey {
    private String PKTableName;
    private String PKColumnName;
    private String FKTableName;
    private String FKColumnName;
    private String FKName;

    public String getPKTableName() {
        return PKTableName;
    }

    public void setPKTableName(String PKTableName) {
        this.PKTableName = PKTableName;
    }

    public String getPKColumnName() {
        return PKColumnName;
    }

    public void setPKColumnName(String PKColumnName) {
        this.PKColumnName = PKColumnName;
    }

    public String getFKTableName() {
        return FKTableName;
    }

    public void setFKTableName(String FKTableName) {
        this.FKTableName = FKTableName;
    }

    public String getFKColumnName() {
        return FKColumnName;
    }

    public void setFKColumnName(String FKColumnName) {
        this.FKColumnName = FKColumnName;
    }

    public String getFKName() {
        return FKName;
    }

    public void setFKName(String FKName) {
        this.FKName = FKName;
    }
}

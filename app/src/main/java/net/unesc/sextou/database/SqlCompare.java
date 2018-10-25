package net.unesc.sextou.database;

public class SqlCompare {
    private String name;
    private Object value;

    private SqlCompare() {
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public static SqlCompare of(String name, Object value) {
        SqlCompare sqlCompare = new SqlCompare();
        sqlCompare.name = name;
        sqlCompare.value = value;
        return sqlCompare;
    }
}

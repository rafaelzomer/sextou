package net.unesc.sextou.database;

public class SqlCompare {
    private String name;
    private String operator = "=";
    private Object value;

    private SqlCompare() {
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getOperator() {
        return operator;
    }

    public static SqlCompare of(String name, Object value) {
        return SqlCompare.of(name,"=", value);
    }

    public static SqlCompare of(String name, String operator, Object value) {
        SqlCompare sqlCompare = new SqlCompare();
        sqlCompare.name = name;
        sqlCompare.operator = operator;
        sqlCompare.value = value;
        return sqlCompare;
    }
}

package net.unesc.sextou.database;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import net.unesc.sextou.event.Event;
import net.unesc.sextou.login.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseCreator {
    private final static List<Class<? extends SqlTable>> TABLES = new ArrayList<Class<? extends SqlTable>>() {
        {
            add(Event.class);
            add(User.class);
        }
    };

    private static String getType(Class clazz) {
        if (clazz == String.class) {
            return "text";
        }
        if (clazz == Long.class) {
            return "integer";
        }
        return null;
    }

    public static List<Field> getFields(Class item) {
        List<Field> fields = new ArrayList<>();
        for (Field field : item.getDeclaredFields()) {
            if (field.isSynthetic() || "serialversionuid".equals(field.getName().toLowerCase())) {
                continue;
            }
            fields.add(field);
        }
        return fields;
    }

    private static void backupData(SQLiteDatabase db, Class table) {
        //TODO
    }

    private static void restoreData(SQLiteDatabase db, Class table) {
        //TODO
    }

    public static void execute(SQLiteDatabase db) {
        for (Class<? extends SqlTable> table : TABLES) {
            String tableName = table.getSimpleName();
            backupData(db, table);
            db.execSQL("drop table if exists "+tableName);
            List<String> columns = new ArrayList<>();
            for (Field field : getFields(table)) {
                String columnName = field.getName();
                Class type = field.getType();
                String isPrimary = "";
                if ("id".equals(columnName)) {
                    isPrimary = "primary";
                }
                String sqlCreate = columnName + " " + getType(type);
                if (!isPrimary.isEmpty()) {
                    sqlCreate = " " + isPrimary;
                }
                columns.add(sqlCreate);
            }
            String allColumns = TextUtils.join(",", columns);
            String tableSql = "create table " + tableName + " (" + allColumns + ")";
            db.execSQL(tableSql);
            restoreData(db, table);
        }
    }
}

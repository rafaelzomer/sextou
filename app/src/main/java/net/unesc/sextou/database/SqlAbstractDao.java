package net.unesc.sextou.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlAbstractDao<T extends SqlTable> {

    final private SqlDatabase sqlDatabase;
    private SQLiteDatabase db;

    public SqlAbstractDao(Context context) {
        this.sqlDatabase = new SqlDatabase(context);
    }

    private static void put(ContentValues contentValues, Field field, Object entity) {
        Object value = null;
        String name = field.getName();
        Class type = field.getType();
        try {
            field.setAccessible(true);
            value = field.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (value == null) {
            return;
        }
        if (String.class.equals(type)) {
            contentValues.put(name, String.valueOf(value));
            return;
        }
        if (Integer.class.equals(type)) {
            contentValues.put(name, Integer.parseInt(String.valueOf(value)));
            return;
        }
        throw new ClassCastException("Tipo para inserção não encontrado: " + type);
    }

    protected abstract Class<T> getClazz();

    private String getTableName() {
        return getClazz().getSimpleName();
    }

    private void open() throws SQLException {
        db = sqlDatabase.getWritableDatabase();
    }

    private void close() throws SQLException {
        sqlDatabase.close();
    }

    public long insert(T item) {
        Long id;
        try {
            open();
            ContentValues values = new ContentValues();
            for (Field field : SqlDatabaseCreator.getFields(getClazz())) {
                put(values, field, item);
            }
            id = db.insert(getTableName(), null, values);
        } finally {
            close();
        }
        return id;
    }

    public List<T> select() {
        return select(new ArrayList<>());
    }

    public List<T> select(List<SqlCompare> where) {
        List<T> result = new ArrayList<>();
        Cursor cursor = null;
        try {
            open();
            List<String> expressions = new ArrayList<>();
            List<String> columns = new ArrayList<>();
            for (SqlCompare sqlCompare : where) {
                expressions.add(sqlCompare.getName() + " = " + sqlCompare.getValue());
            }
            for (Field field : SqlDatabaseCreator.getFields(getClazz())) {
                columns.add(field.getName());
            }
            String finalExpression = TextUtils.join(",", expressions);
            String[] arrayColumns = columns.toArray(new String[columns.size()]);
            cursor = db.query(getTableName(), arrayColumns, finalExpression, null, null, null, null);
            while (cursor.moveToNext()) {
                T entity = getClazz().newInstance();
                for (Field field : SqlDatabaseCreator.getFields(getClazz())) {
                    field.setAccessible(true);
                    int index = cursor.getColumnIndex(field.getName());
                    Class type = field.getType();
                    if (index > -1) {
                        if (String.class.equals(type)) {
                            field.set(entity, cursor.getString(index));
                        }
                        if (Integer.class.equals(type)) {
                            field.set(entity, cursor.getInt(index));
                        }
                    }
                }
                result.add(entity);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            close();
        }
        return result;
    }
}

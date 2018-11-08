package net.unesc.sextou.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public abstract class SqlAbstractDao<T extends SqlTable> {

    final private static SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss", new Locale("pt", "BR"));
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
        if (Date.class.equals(type)) {
            Date valueDate = (Date) value;
            String valueString = DATE_TIME_FORMATTER.format(valueDate);
            contentValues.put(name, valueString);
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
        return select(new ArrayList<>(), 0);
    }

    public T selectOne(List<SqlCompare> where) {
        List<T> list = select(where, 1);
        return list.isEmpty() ? null : list.get(0);
    }

    public T selectOne() {
        return selectOne(new ArrayList<>());
    }

    public List<T> select(List<SqlCompare> where, int limit) {
        List<T> result = new ArrayList<>();
        Cursor cursor = null;
        try {
            open();
            List<String> expressions = new ArrayList<>();
            List<String> columns = new ArrayList<>();
            for (SqlCompare sqlCompare : where) {
                expressions.add(sqlCompare.getName() + " "+ sqlCompare.getOperator() +" \"" + sqlCompare.getValue() + "\"");
            }
            for (Field field : SqlDatabaseCreator.getFields(getClazz())) {
                columns.add(field.getName());
            }
            String finalExpression = TextUtils.join(" AND ", expressions);
            if (limit > 0) {
                finalExpression += " LIMIT " + limit;
            }
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
                        if (Date.class.equals(type)) {
                            String textDate = cursor.getString(index);
                            if (textDate != null) {
                                Date valueDate = DATE_TIME_FORMATTER.parse(textDate);
                                field.set(entity, valueDate);
                            }
                        }
                    }
                }
                result.add(entity);
            }
        } catch (IllegalAccessException | InstantiationException | ParseException e) {
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

package com.CocktailsLibrary.DataAccess.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.CocktailsLibrary.Common.Interfaces.IEntity;
import com.CocktailsLibrary.Common.Interfaces.IRepositoryInt;
import com.CocktailsLibrary.DataAccess.Helpers.DBColumn;
import com.CocktailsLibrary.DataAccess.Helpers.DBKey;
import com.CocktailsLibrary.DataAccess.Helpers.DBMapping;
import com.CocktailsLibrary.DataAccess.Helpers.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
    Represents sql lite repository witch provides base data access methods.
 */
public class SqlRepository<TEntity extends IEntity<Integer>> extends SQLiteOpenHelper implements IRepositoryInt<TEntity> {
    private static final int dbVersion = 1;
    private static final String dbName = "CocktailsLibraryDb";
    private Class entityClass;
    private List<DBColumn> listColumns;

    private String getTableName(){
        return ReflectionUtils.stripPackageName(ReflectionUtils.stripPackageName(ReflectionUtils.getGenericParameterClass(this.getClass(), 0).getName()));
    }

    private void initializeColumns(){
        Field[] fields = entityClass.getFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(DBMapping.class)){
                DBMapping dbMappingAnnotation = field.getAnnotation(DBMapping.class);
                listColumns.add(new DBColumn(field.getName(), dbMappingAnnotation.dataType(), dbMappingAnnotation.dataKey(), dbMappingAnnotation.dataNullable()));
            }
        }
    }

    private DBColumn getPrimaryKeyColumn(){
        for (DBColumn column: listColumns){
            if (column.isPrimaryKey()){
                return column;
            }
        }

        return null;
    }

    private String[] getColumnsName() {
        String[] ColumnName = new String[listColumns.size()];
        for(int i = 0; i <listColumns.size(); i++){
            ColumnName[i] = listColumns.get(i).getName();
        }
        return ColumnName;
    }

    private String getCreateTableQuery(){
        String tableName = getTableName();

        StringBuilder sbCreateTable = new StringBuilder();
        sbCreateTable.append("CREATE TABLE ")
                .append(tableName)
                .append(" (");

        for (int i = 0; i < listColumns.size(); i++){
            DBColumn column = listColumns.get(i);

            if (i != 0) {
                sbCreateTable.append(", ");
            }

            sbCreateTable.append(column.getName())
                    .append(" ")
                    .append(column.getTypeExtended());
        }

        sbCreateTable.append(")");

        return sbCreateTable.toString();
    }

    private TEntity createEntityInstance(){
        TEntity entity = null;

        try {
            entity = (TEntity) entityClass.newInstance();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return entity;
    }

    private ContentValues convertEntityToContentValues(TEntity entity){
        ContentValues cv = new ContentValues();

        try{
            for (DBColumn column : listColumns){
                Field field = entityClass.getField(column.getName());
                DBMapping dbMappingAnnotation = field.getAnnotation(DBMapping.class);
                Object value = field.get(entity);

                if (dbMappingAnnotation.dataKey() != DBKey.PRIMARY_KEY){
                    switch (dbMappingAnnotation.dataType()){
                        case INTEGER:
                            cv.put(column.getName(), (Integer)value);
                            break;
                        case DOUBLE:
                            cv.put(column.getName(), (Double)value);
                            break;
                        case TEXT:
                            cv.put(column.getName(), (String)value);
                            break;
                        default:
                            throw new NoSuchFieldException("This type is unknown");
                    }
                }
            }
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return cv;
    }

    private TEntity createEntityByCursor(Cursor cursor){
        TEntity entity = createEntityInstance();

        try {
            for (DBColumn column : listColumns){
                Field field = entity.getClass().getField(column.getName());
                DBMapping dbMappingAnnotation = field.getAnnotation(DBMapping.class);

                switch (dbMappingAnnotation.dataType()) {
                    case INTEGER:
                        field.set(entity, cursor.getInt(cursor.getColumnIndex(column.getName())));
                        break;
                    case DOUBLE:
                        field.set(entity, cursor.getDouble(cursor.getColumnIndex(column.getName())));
                        break;
                    case TEXT:
                        field.set(entity, cursor.getString(cursor.getColumnIndex(column.getName())));
                        break;
                    default:
                        throw new NoSuchFieldException("This type is unknown");
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public SqlRepository(Context context) {
        super(context, dbName, null, dbVersion);

        listColumns = new ArrayList<DBColumn>();
        entityClass = ReflectionUtils.getGenericParameterClass(this.getClass(), 0);

        initializeColumns();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(getCreateTableQuery());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (dbVersion < newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + getTableName());
            onCreate(sqLiteDatabase);
        }
    }

    @Override
    public List<TEntity> getItems() {
        List<TEntity> listItems = new ArrayList<TEntity>();
        String query = "SELECT * FROM " + getTableName();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor.moveToFirst()){
            do {
                TEntity entity = createEntityByCursor(cursor);
                listItems.add(entity);
            } while (cursor.moveToNext());
        }

        return listItems;
    }

    @Override
    public int getItemsCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TEntity getItem(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(getTableName(), getColumnsName(), getPrimaryKeyColumn().getName() + "=?", new String[]{id.toString()}, null, null ,null);

        if (cursor != null){
            cursor.moveToFirst();
            return createEntityByCursor(cursor);
        }

        return null;
    }

    @Override
    public void addItem(TEntity item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = convertEntityToContentValues(item);

        db.insertOrThrow(getTableName(), null, cv);
        db.close();
    }

    @Override
    public void updateItem(TEntity item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = convertEntityToContentValues(item);
        //db.update(db, cv,)
    }

    @Override
    public void removeItem(TEntity item) {
        SQLiteDatabase dv = this.getWritableDatabase();
        ContentValues cv = convertEntityToContentValues(item);

    }
}

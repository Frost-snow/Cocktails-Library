package com.CocktailsLibrary.DataAccess.Repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.CocktailsLibrary.Common.Interfaces.IEntity;
import com.CocktailsLibrary.Common.Interfaces.IRepository;
import com.CocktailsLibrary.Common.Interfaces.IRepositoryInt;
import com.CocktailsLibrary.DataAccess.Helpers.DBColumn;
import com.CocktailsLibrary.DataAccess.Helpers.DBMapping;
import com.CocktailsLibrary.DataAccess.Helpers.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 29.03.13
 * Time: 23:36
 * To change this template use File | Settings | File Templates.
 */
public class SqlRepository<TEntity extends IEntity<Integer>> extends SQLiteOpenHelper implements IRepositoryInt<TEntity> {
    private static final int dbVersion = 1;
    private static final String dbName = "CocktailsLibraryDb";
    private Class entityClass;

    private List<DBColumn> listColumns;

    private String getTableName(){
        return ReflectionUtils.getGenericParameterClass(this.getClass(), 0).getName();
    }

    private String getCreateTableQuery(){
        Field[] fields = entityClass.getFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(DBMapping.class)){
                DBMapping dbMappingAnnotation = (DBMapping)field.getAnnotation(DBMapping.class);
                listColumns.add(new DBColumn(field.getName(), dbMappingAnnotation.dataType().toString()
                        + (dbMappingAnnotation.notNull() ? " NOT NULL" : "")));
            }
        }

        String tableName = entityClass.getName();

        StringBuilder sbCreateTable = new StringBuilder();
        sbCreateTable.append("CREATE TABLE ")
                .append(tableName)
                .append(" (");

        sbCreateTable.append(listColumns.get(0).getName())
                .append(" ")
                .append(listColumns.get(0).getType())
                .append(" PRIMARY KEY AUTOINCREMENT");

        for (int i = 1; i < listColumns.size(); i++){
            DBColumn column = listColumns.get(i);
            sbCreateTable.append(", ")
                    .append(column.getName())
                    .append(" ")
                    .append(column.getType());
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

    public SqlRepository(Context context) {
        super(context, dbName, null, dbVersion);

        listColumns = new ArrayList<DBColumn>();
        entityClass = ReflectionUtils.getGenericParameterClass(this.getClass(), 0);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getCreateTableQuery());
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
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()){
                do {
                    TEntity entity = createEntityInstance();
                    for (DBColumn column : listColumns){
                        Field field = entity.getClass().getField(column.getName());
                        field.set(entity, cursor.getString(cursor.getColumnIndex(column.getName())));
                    }
                    listItems.add(entity);
                } while (cursor.moveToNext());
            }
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return listItems;
    }

    @Override
    public int getItemsCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TEntity getItem(Integer integer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addItem(IEntity item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateItem(IEntity item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeItem(IEntity item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

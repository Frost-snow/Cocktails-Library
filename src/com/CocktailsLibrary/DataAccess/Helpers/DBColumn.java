package com.CocktailsLibrary.DataAccess.Helpers;

/**
 * Created with IntelliJ IDEA.
 * User: Volron
 * Date: 29.03.13
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class DBColumn {
    private String name;
    private DBType type;
    private DBKey key;
    private DBNullable nullable;

    public DBColumn(String name, DBType type, DBKey key, DBNullable nullable){
        this.name = name;
        this.type = type;
        this.key = key;
        this.nullable = nullable;
    }

    public String getName(){
        return name;
    }

    public String getTypeExtended(){
        StringBuilder sb = new StringBuilder();
        sb.append(type.toString());

        if (key == DBKey.PRIMARY_KEY){
            sb.append(" PRIMARY KEY AUTOINCREMENT");
        } else {
            if (key == DBKey.FOREIGN_KEY){
                // TODO: (dk) add foreign key mechanism.
                sb.append(" FOREIGN KEY");
            }

            if (nullable == DBNullable.NOT_NULL){
                sb.append(" NOT NULL");
            }
        }

        return sb.toString();
    }

    public boolean isPrimaryKey(){
        return key == DBKey.PRIMARY_KEY;
    }
}

package com.CocktailsLibrary.DataAccess;

import com.CocktailsLibrary.Common.Interfaces.IEntity;
import com.CocktailsLibrary.Common.Interfaces.IRepository;

import java.io.File;
import java.util.Queue;
import java.util.UUID;

// Represents an XML-File based repository implementation.
public class XmlFileRepository<TEntity extends IEntity<UUID>> extends IRepository<TEntity>{
    // Initializes the repository.
    public XmlFileRepository(String rootPath){

    }

    // Gets root directory.
    private File _directory;

    // Gets a file extension fro entity file.
    private final String _fileExt = ".entity.xml";

    // File name format: {RootPath}/{EntityTypeName}/{id:00000000000000000000000000000000}.entity.xml.
    protected String getFileNameForEntity(UUID id){
        return String.format("%s/%s%s", this._directory.getName(), id, this._fileExt);
    }

    // Gets a guid from file info.
    protected UUID getIdFromFileInfo(String fileName){
        if (!fileName.endsWith(this._fileExt))
        {
            return null;
        }

        fileName = fileName.substring(0, fileName.length() - this._fileExt.length() - 1);

        try{
            return UUID.fromString(fileName);
        }
        catch (IllegalArgumentException e){
            return null;
        }

    }

    @Override
    public Queue items() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IEntity<TEntity> getById(TEntity tEntity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(IEntity<TEntity> entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove(IEntity<TEntity> entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

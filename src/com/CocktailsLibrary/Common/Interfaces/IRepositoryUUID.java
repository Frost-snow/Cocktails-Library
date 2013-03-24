package com.CocktailsLibrary.Common.Interfaces;

import java.util.UUID;

// Represents a repository for entities with Guid ID.
public interface IRepositoryUUID<TEntity extends IEntity<UUID>> extends IRepository<UUID, TEntity>{

}

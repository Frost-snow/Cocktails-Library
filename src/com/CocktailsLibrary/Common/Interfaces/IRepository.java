package com.CocktailsLibrary.Common.Interfaces;

import java.util.Queue;

// Represents a repository for entity repository.
public interface IRepository<TId extends Comparable<TId>, TEntity extends IEntity<TId>> {
    // Defines a query to all items.
    Queue<TEntity> items();

    // Gets an item by specified id.
    TEntity getById(TId id);

    // Saves a specified entity.
    void save(TEntity entity);

    // Removes a specified entity.
    void remove(TEntity entity);

    // Creates a new entity instance.
    void create();
}


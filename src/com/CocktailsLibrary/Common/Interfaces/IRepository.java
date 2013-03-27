package com.CocktailsLibrary.Common.Interfaces;

import java.util.List;

// Represents a repository for entity repository.
public interface IRepository<TId extends Comparable<TId>, TEntity extends IEntity<TId>> {
    // Gets all items.
    List<TEntity> getItems();

    // Gets items count.
    int getItemsCount();

    // Gets an item by specified id.
    TEntity getItem(TId id);

    // Adds item to repository.
    void addItem(TEntity item);

    // Updates a specified entity.
    void updateItem(TEntity item);

    // Removes a specified entity.
    void removeItem(TEntity item);
}


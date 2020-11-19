package com.openclassrooms.entrevoisins.service.Favourite;

import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.List;

/**
 * Favourite API Client
 * */

public interface FavouriteApiService {

    /**
     * Get all my favourites
     * @return {@link List}
     */

    List<Favourite> getFavourites();

    /**
     * Deletes a favourite
     * @param favourite
     */
    void deleteFavourite(Favourite favourite);

    /**
     * Deletes a favourite
     * @param id
     */
    void deleteFavouriteById(long id);


    /**
     * Add a favourite to the list.
     * @param favourite
     */
    void addFavourite(Favourite favourite);

}

package com.openclassrooms.entrevoisins.service.Neighbour;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add a neighbour to favourite
     * @param neighbour
     */

    void addNeighbourToFavourite(Neighbour neighbour);

    /**
     * Delete a neighbour to favourite
     * @param neighbour
     */

    void deleteNeighbourFromFavourite(Neighbour neighbour);


    /**
    * Retrieve all the neighbours that are marked as favourite
     * @return {@link List}
    * */
    List<Neighbour> getFavouriteNeighbours();

}

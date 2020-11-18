package com.openclassrooms.entrevoisins.di;

import com.openclassrooms.entrevoisins.service.Favourite.DummyFavouriteApiService;
import com.openclassrooms.entrevoisins.service.Favourite.FavouriteApiService;
import com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static NeighbourApiService neighbourApiService = new DummyNeighbourApiService();

    private static FavouriteApiService favouriteApiService = new DummyFavouriteApiService();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static NeighbourApiService getNeighbourApiService() {
        return neighbourApiService;
    }

    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }


    public static FavouriteApiService getFavouriteApiService() {
        return favouriteApiService;
    }

    public static FavouriteApiService getNewInstaceFavouriteApiService() {
        return new DummyFavouriteApiService();
    }


}

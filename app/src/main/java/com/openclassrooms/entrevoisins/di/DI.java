package com.openclassrooms.entrevoisins.di;


import com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static NeighbourApiService neighbourApiService = new DummyNeighbourApiService();


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



}

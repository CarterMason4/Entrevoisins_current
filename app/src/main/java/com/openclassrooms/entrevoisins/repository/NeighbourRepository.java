package com.openclassrooms.entrevoisins.repository;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

import java.util.List;

public class NeighbourRepository {

    private final NeighbourApiService apiService;

    public NeighbourRepository(NeighbourApiService apiService) {
        this.apiService = apiService;
    }

    public void createNeighbour(Neighbour neighbour) {
        apiService.createNeighbour(neighbour);
    }

    public void deleteNeighbour(Neighbour neighbour) {
        apiService.deleteNeighbour(neighbour);
    }

    public List<Neighbour> getNeighbours() {
        return apiService.getNeighbours();
    }

    public void addNeighbourToFavourite(Neighbour neighbour) {
        apiService.addNeighbourToFavourite(neighbour);
    }

    public void deleteNeighbourFromFavourite(Neighbour neighbour) {
        apiService.deleteNeighbourFromFavourite(neighbour);
    }


    public List<Neighbour> getFavouritesNeighbours() {
        return apiService.getFavouriteNeighbours();
    }


}

package com.openclassrooms.entrevoisins.service.Neighbour;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public void addNeighbourToFavourite(Neighbour neighbour) {
        for(Neighbour loopNeighbour : neighbours) {
            if(neighbour.equals(loopNeighbour)) {
                loopNeighbour.setFavourite(true);
                break;
            }
        }
    }

    @Override
    public void deleteNeighbourFromFavourite(Neighbour neighbour) {
        for(Neighbour loopNeighbour : neighbours) {
            if(neighbour.equals(loopNeighbour)) {
                loopNeighbour.setFavourite(false);
                break;
            }
        }
    }

    @Override
    public List<Neighbour> getFavouriteNeighbours() {
        List<Neighbour> favourites = new ArrayList<>();

        for(Neighbour neighbour : neighbours) {
            if(neighbour.isFavourite()) {
                favourites.add(neighbour);
            }
        }
        return favourites;
    }



}
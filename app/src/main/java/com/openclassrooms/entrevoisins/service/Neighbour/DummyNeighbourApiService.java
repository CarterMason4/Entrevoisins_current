package com.openclassrooms.entrevoisins.service.Neighbour;

import android.widget.Toast;

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
        for(Neighbour loopNeighobur : neighbours) {
            if(neighbour.equals(loopNeighobur)) {
                loopNeighobur.setFavori(true);
                break;
            }
        }
    }

    @Override
    public void deleteNeighbourFromFavourite(Neighbour neighbour) {
        for(Neighbour loopNeighbour : neighbours) {
            if(neighbour.equals(loopNeighbour)) {
                loopNeighbour.setFavori(false);
                break;
            }
        }
    }

    @Override
    public List<Neighbour> getFavouriteNeighbours() {
        List<Neighbour> favourites = new ArrayList<>();

        for(Neighbour neighbour : neighbours) {
            if(neighbour.estFavori()) {
                favourites.add(neighbour);
            }
        }
        return favourites;
    }

    @Override
    public boolean isFavourite(long id) {
        boolean isFavourite = false;

        for(Neighbour neighbour : neighbours) {
            if(id == neighbour.getId()) {
                isFavourite = true;
                break;
            }
        }
        return isFavourite;
    }
}

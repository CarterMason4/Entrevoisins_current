package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Random;

import static com.openclassrooms.entrevoisins.service.Neighbour.DummyNeighbourGenerator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }


    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();

        // We create a new neighbour and add it to the main list.
        Neighbour newNeighbour = generateNeighbour();
        service.createNeighbour(newNeighbour);

        assertEquals(neighbours.get(neighbours.size() - 1), newNeighbour);
    }

    @Test
    public void getFavouritesWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();

        // We add several neighbours to the favourites list.
        for(int i = 0 ; i < new Random().nextInt(DUMMY_NEIGHBOURS.size()) ; i++) {
            service.addNeighbourToFavourite(neighbours.get(i));
        }

        List<Neighbour> favourites = service.getFavouriteNeighbours();

        // Test the size of the list, must be greater than 0
        // Since favourite neighbours have been added in the for loop.
        assertNotEquals(0, favourites.size());
    }

    @Test
    public void deleteFromFavouriteWithSuccess() {

        List<Neighbour> neighbours = service.getNeighbours();

        // We retrieve a random neighbour
        Neighbour neighbourToDelete = neighbours.get(new Random().nextInt(DUMMY_NEIGHBOURS.size()));

        // Add the neighbour the favourite list.
        service.addNeighbourToFavourite(neighbourToDelete);

        // Then delete it
        service.deleteNeighbourFromFavourite(neighbourToDelete);

        // Check if the neighbour has correctly been deleted from the favourites list.
        assertFalse(service.getFavouriteNeighbours().contains(neighbourToDelete));
    }

}
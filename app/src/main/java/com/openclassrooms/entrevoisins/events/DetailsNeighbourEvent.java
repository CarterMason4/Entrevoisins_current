package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DetailsNeighbourEvent {

    /**
     * Neighbour to view
     * */

    public Neighbour neighbour;


    /**
     * Constructor
     * @param neighbour
     * */
    public DetailsNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }


}

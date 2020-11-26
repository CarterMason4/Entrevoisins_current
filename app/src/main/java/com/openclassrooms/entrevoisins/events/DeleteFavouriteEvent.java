package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavouriteEvent {

    public Neighbour neighbour;

    public DeleteFavouriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

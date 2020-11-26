package com.openclassrooms.entrevoisins.events;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

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

    public DetailsNeighbourEvent(Neighbour neighbour, Context context, Class targetClass) {
        this.neighbour = neighbour;

        Intent intent = new Intent(context, targetClass);
        intent.putExtra("neighbour", this.neighbour);
        context.startActivity(intent);
    }


}

package com.openclassrooms.entrevoisins.utils;

import com.openclassrooms.entrevoisins.model.Favourite;
import com.openclassrooms.entrevoisins.model.Neighbour;

public abstract class Utils {

    public static Favourite convertNeighbourToFavorite(Neighbour n) {

        return new Favourite(
            n.getId(),
            n.getName(),
            n.getAvatarUrl(),
            n.getAddress(),
            n.getPhoneNumber(),
            n.getAboutMe());
    }
}
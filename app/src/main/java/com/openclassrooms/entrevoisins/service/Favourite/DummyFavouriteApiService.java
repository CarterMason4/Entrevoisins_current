package com.openclassrooms.entrevoisins.service.Favourite;

import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.List;

public class DummyFavouriteApiService implements FavouriteApiService {

    private List<Favourite> favourites = DummyFavouriteGenerator.generateFavourites();

    @Override
    public List<Favourite> getFavourites() {
        return favourites;
    }

    @Override
    public void deleteFavourite(Favourite favourite) {
        // On récupère l'ID passé en paramèter.
        // On vérifie
        favourites.remove(favourite);
    }

    @Override
    public void addFavourite(Favourite favourite) {
        favourites.add(favourite);
    }
}

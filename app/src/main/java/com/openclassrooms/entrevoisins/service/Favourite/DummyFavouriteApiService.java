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
        favourites.remove(favourite);
    }

    @Override
    public void deleteFavouriteById(long id) {

        boolean correspondance = false;
        Favourite favourite = null;

        for(int i = 0 ; i < favourites.size() ; i++) {
            if(id == favourites.get(i).getN_id()) {
                correspondance = true;
                favourite = favourites.get(i);
                break;
            }
        }

        if(correspondance) {
            favourites.remove(favourite);
        }
    }

    @Override
    public void addFavourite(Favourite favourite) {
        favourites.add(favourite);
    }
}

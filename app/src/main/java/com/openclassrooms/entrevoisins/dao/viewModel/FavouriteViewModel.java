package com.openclassrooms.entrevoisins.dao.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.openclassrooms.entrevoisins.dao.repository.FavouriteRepository;
import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    private FavouriteRepository repository;
    private LiveData<List<Favourite>> allFavourites;


    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        repository = new FavouriteRepository(application);
        allFavourites = repository.getAllFavourites();
    }

    public void insert(Favourite f) {
        repository.insert(f);
    }

    public void delete(Favourite f) {
        repository.delete(f);
    }

   /* public void deleteAll() {
        repository.deleteAll();
    }*/

    public LiveData<List<Favourite>> getAllFavourites() {
        return allFavourites;
    }
}

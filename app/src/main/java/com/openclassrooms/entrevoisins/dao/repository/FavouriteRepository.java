package com.openclassrooms.entrevoisins.dao.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.openclassrooms.entrevoisins.dao.FavouriteDao;
import com.openclassrooms.entrevoisins.dao.FavouriteDatabase;
import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.List;

public class FavouriteRepository {

    private FavouriteDao dao;
    private LiveData<List<Favourite>> allFavourites;

    public FavouriteRepository (Application application) {
        FavouriteDatabase database = FavouriteDatabase.getInstance(application);
        dao = database.fdao();
       // allFavourites = dao.getAllFavourites();
    }

    public void insert(Favourite f) {
        new InsertFavouriteAsyncTask(dao).execute(f);

    }

    public void delete(Favourite f) {
        new DeleteFavouriteAsyncTask(dao).execute(f);
    }


    public LiveData<List<Favourite>> getAllFavourites() {
        return allFavourites;
    }


    // Tâche asynchrone pour l'insertion d'un favori.

    private static class InsertFavouriteAsyncTask extends AsyncTask<Favourite, Void ,Void> {

        private FavouriteDao favouriteDao;

        private InsertFavouriteAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }


        @Override
        protected Void doInBackground(Favourite... favourites) {
            favouriteDao.insert(favourites[0]);
            return null;
        }
    }

    // Tâche asynchrone pour la suppression d'un favori.

    private static class DeleteFavouriteAsyncTask extends AsyncTask<Favourite, Void, Void> {

        private FavouriteDao favouriteDao;

        private DeleteFavouriteAsyncTask(FavouriteDao favouriteDao) {
            this.favouriteDao = favouriteDao;
        }

        @Override
        protected Void doInBackground(Favourite... favourites) {
            favouriteDao.delete(favourites[0]);
            return null;
        }
    }



}

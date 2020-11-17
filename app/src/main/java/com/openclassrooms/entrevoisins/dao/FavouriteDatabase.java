package com.openclassrooms.entrevoisins.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.openclassrooms.entrevoisins.model.Favourite;


@Database(entities = Favourite.class, version = 1)
public abstract class FavouriteDatabase extends RoomDatabase {


    private static FavouriteDatabase instance;

    public abstract FavouriteDao fdao();


    public static synchronized FavouriteDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavouriteDatabase.class, "favourite_database")
                    .fallbackToDestructiveMigration().build();
        }

        return instance;
    }
    
}

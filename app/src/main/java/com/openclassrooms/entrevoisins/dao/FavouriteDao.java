package com.openclassrooms.entrevoisins.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.List;

@Dao
public interface FavouriteDao {

    @Insert
    void insert(Favourite favourite);

    @Delete
    void delete(Favourite favourite);

    @Query("DELETE FROM favourites_table")
    void deleteAll();


}

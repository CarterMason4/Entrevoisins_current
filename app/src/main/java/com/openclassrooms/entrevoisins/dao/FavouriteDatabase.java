package com.openclassrooms.entrevoisins.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.model.Favourite;


@Database(entities = Favourite.class, version = 2)
public abstract class FavouriteDatabase extends RoomDatabase {


    private static FavouriteDatabase instance;

    public abstract FavouriteDao fdao();


    public static synchronized FavouriteDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavouriteDatabase.class, "favourite_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(instance).execute();

        }
    };



    // Tâche asynchrone utilisée pour populer la base de données pour les tests.

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {

        private FavouriteDao fdao;

        public PopulateDatabaseAsyncTask(FavouriteDatabase db) {
            fdao = db.fdao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           /* fdao.insert(new Favourite(1, "Caroline","https://i.pravatar.cc/150?u=a042581f4e29026704d", "44300 Nantes", "+33 6 03 87 65 44", "I love Deftones and cat", 1));
            fdao.insert(new Favourite(2, "Jean-Pierre","https://i.pravatar.cc/150?u=a042581f4e29026704e", "44300 Nantes", "+33 6 03 87 65 44", "I love Seether and dogs", 2));
            fdao.insert(new Favourite(3, "Marcos", "https://i.pravatar.cc/150?u=a042581f4e29026704f", "44300 Nantes", "+33 6 03 87 65 44", "I love Deftones and cat", 3));*/

            return null;
        }
    }


}

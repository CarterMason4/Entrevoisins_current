package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Favourite;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Favourite.FavouriteApiService;

import java.util.ArrayList;
import java.util.List;

public class NeighbourDetailsActivity extends AppCompatActivity {


    private FavouriteApiService fApiService;
    private Neighbour n;

    private List<Favourite> favouriteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

        // Identification de tous les élements graphiques à utiliser.

        Toolbar toolbar = findViewById(R.id.toolbar_details);
        ImageView avatar = findViewById(R.id.avatar);
        TextView neighbour_address = findViewById(R.id.neighbour_address);
        TextView neighbour_name = findViewById(R.id.neighbour_name);
        TextView neighbour_phone = findViewById(R.id.neighbour_phone);
        TextView neighbour_network = findViewById(R.id.neighbour_network);
        TextView neighbour_aboutMe = findViewById(R.id.aboutMe_text);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab2 = findViewById(R.id.fab2);

        fApiService = DI.getFavouriteApiService();
        favouriteList = fApiService.getFavourites();


        // On récupère l'intent utilisé pour passer de la liste complète à
        // l'activité de détails.

        Intent intent = getIntent();

        // Déclaration et instanciation d'un objet Neighbour


        // Vérification afin d'éviter d'éviter les NullPointerException.

        if(intent != null) {
            if(intent.getExtras() != null) {
                n = intent.getParcelableExtra("neighbour");
                toolbar.setTitle(n.getName());
            }
        }


        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        fab.setImageDrawable(getDrawable(R.drawable.ic_star_unfilled));


        // SI le voisin que l'on veut ajouter est déjà présent dans les favoris ALORS
            // l'icône du bouton sera dorée
              // SI l'utilisateur appuie sur le bouton et que le voisin est déjà présent
              // ALORS on retire le voisin de la liste des favoris.

        // SINON, on ajoute le voisin dans les favoris et on modifie l'icône du bouton

        // *******

        boolean presence = false;


        for(int i = 0 ; i < favouriteList.size() ; i++) {

            if(favouriteList.get(i).getN_id() == n.getId()) {
                presence = true;
                break;
            }
        }

        // Tout se joue dans le 'if-else' ci-dessus.

        if(presence) {
            makeToast(n.getName() + " est dans les favoris.");
        } else {
            makeToast(n.getName() + " n'est pas dans les favoris.");
        }




        fab.setOnClickListener(v -> {


            fApiService.addFavourite(new Favourite(
                    n.getId(),
                    n.getName(),
                    n.getAvatarUrl(),
                    n.getAddress(),
                    n.getPhoneNumber(),
                    n.getAboutMe()));

            /*Toast toast = Toast.makeText(getApplicationContext(), n.getName() + " a ajouté à vos favoris", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();*/

            Drawable d = fab.getDrawable();


            if(d.getConstantState() == (getResources().getDrawable(R.drawable.ic_star_filled).getConstantState())) {

               fab.setImageDrawable(getDrawable(R.drawable.ic_star_unfilled));

            } else {
                fab.setImageDrawable(getDrawable(R.drawable.ic_star_filled));
            }



        });

        fab2.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getApplicationContext(), favouriteList.toString(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        });


        // NEIGHBOUR




        if(intent != null) {
            if(intent.getExtras() != null) {

                if(n != null) {

                    // Chargement de l'avatar avec Glide.

                    Glide.with(this)
                            .load(n.getAvatarUrl())
                            .fitCenter()
                            .into(avatar);

                    neighbour_address.setText(n.getAddress());
                    neighbour_name.setText(n.getName());
                    neighbour_phone.setText(n.getPhoneNumber());

                    String network_string = getString(R.string.facebook) + "/" + firstToLower(n.getName());
                    neighbour_network.setText(network_string);
                    neighbour_aboutMe.setText(n.getAboutMe());


                }
            }
        }





    }


    private String firstToLower(String string) {
        char[] lettres = string.toCharArray();

        lettres[0] = Character.toLowerCase(lettres[0]);

        return new String(lettres);

    }

    private Favourite convertNeighbourToFavourite(Neighbour n) {
        return new Favourite(
                n.getId(),
                n.getName(),
                n.getAvatarUrl(),
                n.getAddress(),
                n.getPhoneNumber(),
                n.getAboutMe());
    }

    private void makeToast(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Intent;

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
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {

    private NeighbourApiService nApiService;
    private Neighbour neighbour;
    private List<Neighbour> neighboursList = new ArrayList<>();
    private boolean hasBeenPressed;

    @BindView(R.id.toolbar_details)
    Toolbar toolbar;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.neighbour_address)
    TextView neighbour_address;
    @BindView(R.id.neighbour_name)
    TextView neighbour_name;
    @BindView(R.id.neighbour_phone)
    TextView neighbour_phone;
    @BindView(R.id.neighbour_network)
    TextView neighbour_network;
    @BindView(R.id.aboutMe_text)
    TextView neighbour_aboutMe;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);

        nApiService = DI.getNeighbourApiService();
        neighboursList = nApiService.getNeighbours();
        printFavourites();


        // On récupère l'intent utilisé pour passer de la liste complète à
        // l'activité de détails.
        Intent intent = getIntent();

        // Vérification afin d'éviter les NullPointerException.
        if(intent != null) {
            if(intent.getExtras() != null) {
                neighbour = intent.getParcelableExtra("neighbour");
                toolbar.setTitle(neighbour.getName());

                Glide.with(this)
                        .load(neighbour.getAvatarUrl())
                        .fitCenter()
                        .into(avatar);

                neighbour_address.setText(neighbour.getAddress());
                neighbour_name.setText(neighbour.getName());
                neighbour_phone.setText(neighbour.getPhoneNumber());

                String network_string = getString(R.string.facebook) + firstToLower(neighbour.getName());
                neighbour_network.setText(network_string);
                neighbour_aboutMe.setText(neighbour.getAboutMe());
            }
        }

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        int starImageId = neighbour.estFavori() ? R.drawable.ic_star_filled : R.drawable.ic_star_unfilled;
        fab.setImageDrawable(getDrawable(starImageId));

        fab.setOnClickListener(v -> {
            String message = neighbour.getName() + ' ';

            if(!neighbour.estFavori()) {
                nApiService.addNeighbourToFavourite(neighbour);
                message += getString(R.string.ajoute);
                neighbour.setFavori(true);
            } else {
                nApiService.deleteNeighbourFromFavourite(neighbour);
                message += getString(R.string.retire);
                neighbour.setFavori(false);
            }

            int imageId = neighbour.estFavori() ? R.drawable.ic_star_filled : R.drawable.ic_star_unfilled;
            fab.setImageDrawable(getDrawable(imageId));
            makeToast(message);
        });
    }





    @Subscribe
    public String firstToLower(String string) {
        char[] lettres = string.toCharArray();

        lettres[0] = Character.toLowerCase(lettres[0]);

        return new String(lettres);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    // @Subscribe
    public void makeToast(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void printFavourites() {
        List<Neighbour> favourites = new ArrayList<>();

        for(Neighbour neighbour : neighboursList) {
            if(neighbour.estFavori()) {
                favourites.add(neighbour);
            }
        }

        makeToast(favourites.toString());
    }
}
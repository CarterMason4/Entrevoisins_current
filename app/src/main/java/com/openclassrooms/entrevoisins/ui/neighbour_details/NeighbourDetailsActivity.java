package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {

    private NeighbourApiService nApiService;
    private Neighbour neighbour;
    private List<Neighbour> neighboursList = new ArrayList<>();

    private Toolbar toolbar;
    private ImageView avatar;
    private TextView neighbour_address;
    private TextView neighbour_name;
    private TextView neighbour_phone;
    private TextView neighbour_network;
    private TextView neighbour_aboutMe;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

        toolbar = findViewById(R.id.toolbar_details);
        avatar = findViewById(R.id.avatar);
        neighbour_address = findViewById(R.id.neighbour_address);
        neighbour_name = findViewById(R.id.neighbour_name);
        neighbour_phone = findViewById(R.id.neighbour_phone);
        neighbour_network = findViewById(R.id.neighbour_network);
        neighbour_aboutMe = findViewById(R.id.aboutMe_text);
        fab = findViewById(R.id.fab);


        nApiService = DI.getNeighbourApiService();
        neighboursList = nApiService.getNeighbours();

        populateViews();

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fab.setImageDrawable(ContextCompat.getDrawable(this, getImageId(neighbour.isFavourite())));

        operateButton();
    }

    /**
     *  Big chunks of the code are placed in methods/functions
     * in order for the onCreate() method to be cleaner.
     * */

    private void populateViews() {

        Intent intent = getIntent();

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
    }

    private void operateButton() {
        fab.setOnClickListener(v -> {
            String message = neighbour.getName() + ' ';

            if(!neighbour.isFavourite()) {
                nApiService.addNeighbourToFavourite(neighbour);
                message += getString(R.string.ajoute);
                neighbour.setFavourite(true);
            } else {
                nApiService.deleteNeighbourFromFavourite(neighbour);
                message += getString(R.string.retire);
                neighbour.setFavourite(false);
            }

            fab.setImageDrawable(ContextCompat.getDrawable(this, getImageId(neighbour.isFavourite())));

            makeToast(message);
        });
    }

    private String firstToLower(String string) {
        char[] letters = string.toCharArray();

        letters[0] = Character.toLowerCase(letters[0]);

        return new String(letters);
    }

    private void makeToast(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    private int getImageId(boolean myBoolean) {
        return myBoolean ? R.drawable.star_icon_filled : R.drawable.star_icon_unfilled;
    }
}
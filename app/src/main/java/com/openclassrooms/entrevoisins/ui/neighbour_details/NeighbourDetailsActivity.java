package com.openclassrooms.entrevoisins.ui.neighbour_details;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourDetailsActivity extends AppCompatActivity {

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


        Intent intent = getIntent();
        Neighbour n = null;


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


        fab.setOnClickListener(v -> {

            Drawable d = fab.getDrawable();


            if(d.getConstantState() == (getResources().getDrawable(R.drawable.ic_star_filled).getConstantState())) {

               fab.setImageDrawable(getDrawable(R.drawable.ic_star_unfilled));

            } else {
                fab.setImageDrawable(getDrawable(R.drawable.ic_star_filled));
            }

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
}
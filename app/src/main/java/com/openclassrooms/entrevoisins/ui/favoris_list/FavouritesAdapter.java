package com.openclassrooms.entrevoisins.ui.favoris_list;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Favourite;

import java.util.ArrayList;
import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesHolder> {


    private List<Favourite> favourites = new ArrayList<>();

    public FavouritesAdapter(List<Favourite> favourites) {
        this.favourites = favourites;
    }



    @NonNull
    @Override
    public FavouritesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View favouriteView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favourites_item_layout, viewGroup, false);

        return new FavouritesHolder(favouriteView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesHolder holder, int i) {
        Favourite favourite = favourites.get(i);

        Glide.with(holder.avatar.getContext())
                .load(favourite.getAvatarUrl())
                .fitCenter()
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatar);

        holder.name.setText(favourite.getName());

        holder.deleteButton.setOnClickListener(v -> {
            Toast toast = Toast.makeText(holder.deleteButton.getContext(),"je supprime", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        });




    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }


    class FavouritesHolder extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView name;
        private ImageButton deleteButton;


        public FavouritesHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.favourite_avatar);
            name = itemView.findViewById(R.id.favourite_name);
            deleteButton = itemView.findViewById(R.id.favourites_list_delete_button);

        }
    }
}

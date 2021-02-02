package com.openclassrooms.entrevoisins.ui.favoris_list;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavouriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesHolder> {


    private List<Neighbour> neighbours = new ArrayList<>();

    public FavouritesAdapter(List<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }



    @NonNull
    @Override
    public FavouritesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View favouriteView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favourites_item_layout, viewGroup, false);


        return new FavouritesHolder(favouriteView);
    }


    @Override
    public void onBindViewHolder(@NonNull FavouritesHolder holder, int position) {

            Neighbour neighbour = neighbours.get(position);

            Glide.with(holder.avatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .fitCenter()
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.avatar);

            holder.name.setText(neighbour.getName());

            holder.deleteButton.setOnClickListener(v -> EventBus.getDefault().
                    post(new DeleteFavouriteEvent(neighbour)));
    }



    @Override
    public int getItemCount() {
        return neighbours.size();
    }


    class FavouritesHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView name;
        public ImageButton deleteButton;


        public FavouritesHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.favourites_neighbour_avatar);
            name = itemView.findViewById(R.id.mNeighbour_name);
            deleteButton = itemView.findViewById(R.id.neighbours_list_delete_button);
        }
    }
}
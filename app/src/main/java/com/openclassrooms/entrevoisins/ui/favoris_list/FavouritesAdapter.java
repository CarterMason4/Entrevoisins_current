package com.openclassrooms.entrevoisins.ui.favoris_list;

import android.content.Context;
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
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavouriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DetailsNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesHolder> {


    private List<Neighbour> neighbours = new ArrayList<>();

    private NeighbourApiService nApiService;


    public FavouritesAdapter(List<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }



    @NonNull
    @Override
    public FavouritesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View favouriteView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favourites_item_layout, viewGroup, false);

        nApiService = DI.getNeighbourApiService();

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

            holder.deleteButton.setOnClickListener(v -> {
                EventBus.getDefault().
                        post(new DeleteFavouriteEvent(neighbour));
            });
    }



    @Override
    public int getItemCount() {
        return neighbours.size();
    }


    class FavouritesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.favourite_avatar)
        public ImageView avatar;
        @BindView(R.id.favourite_name)
        public TextView name;
        @BindView(R.id.favourites_list_delete_button)
        public ImageButton deleteButton;


        public FavouritesHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

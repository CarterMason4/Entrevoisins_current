package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DetailsNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(v ->
                EventBus.getDefault()
                .post(new DeleteNeighbourEvent(neighbour)));


        holder.itemView.setOnClickListener(v ->
            EventBus.getDefault().
                    post(new DetailsNeighbourEvent(neighbour)));

    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mNeighbourAvatar;
        public TextView mNeighbourName;
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mNeighbourAvatar = view.findViewById(R.id.favourites_neighbour_avatar);
            mNeighbourName = view.findViewById(R.id.mNeighbour_name);
            mDeleteButton = view.findViewById(R.id.neighbours_list_delete_button);

        }
    }
}
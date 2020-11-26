package com.openclassrooms.entrevoisins.ui.favoris_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavouriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    private List<Neighbour> favourites;
    private RecyclerView recyclerView;

    private NeighbourApiService nApiService;
    private FavouritesAdapter fAdapter;

    private MyNeighbourRecyclerViewAdapter neighbourAdapter;


    /**
     * Create and return a new instance
     * @return @{@link FavouritesFragment}
     */

    public static FavouritesFragment newInstance() {
        return new FavouritesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nApiService = DI.getNeighbourApiService();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser) {
            initList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        favourites = nApiService.getFavouriteNeighbours();
        fAdapter = new FavouritesAdapter(favourites);
        recyclerView.setAdapter(fAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */


    @Subscribe
    public void onDeleteFavourite(DeleteFavouriteEvent event) {
        nApiService.deleteNeighbourFromFavourite(event.neighbour);
        initList();
    }


}

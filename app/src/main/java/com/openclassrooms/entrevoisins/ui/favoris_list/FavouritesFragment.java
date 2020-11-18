package com.openclassrooms.entrevoisins.ui.favoris_list;

import android.arch.lifecycle.ViewModelProviders;
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
import com.openclassrooms.entrevoisins.dao.viewModel.FavouriteViewModel;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Favourite;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.Favourite.FavouriteApiService;
import com.openclassrooms.entrevoisins.service.Neighbour.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class FavouritesFragment extends Fragment {

    private List<Favourite> favourites;
    private RecyclerView recyclerView;

    private FavouriteApiService mApiService;
    private FavouritesAdapter fAdapter;



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
        mApiService = DI.getFavouriteApiService();

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


    private void initList() {
        favourites = mApiService.getFavourites();
        fAdapter = new FavouritesAdapter(favourites);
        recyclerView.setAdapter(fAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        initList();
    }


    public FavouritesAdapter getAdapter() {
        return fAdapter;
    }


}

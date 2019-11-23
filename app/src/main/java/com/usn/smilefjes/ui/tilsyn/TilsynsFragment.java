
package com.usn.smilefjes.ui.tilsyn;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.R;
import com.usn.smilefjes.TilsynActivity;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.FragmentTilsynBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TilsynsFragment extends Fragment implements TilsynAdapter.OnTilsynLytter {

   final List<Tilsyn> tilsynsListe = new ArrayList<>();
   final List<Tilsyn> full = new ArrayList<>();


    TilsynAdapter tilsynAdapter = new TilsynAdapter(tilsynsListe, this, full);
    FragmentTilsynBinding bindingTilsyn;
    RecyclerView recyclerView;


    ItemTouchHelper touchHelper = new ItemTouchHelper(
            new SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                      @NonNull RecyclerView.ViewHolder target) {
                    int fraPos = viewHolder.getAdapterPosition();
                    int tilPos = target.getAdapterPosition();


                    Collections.swap(tilsynsListe, fraPos, tilPos);
                    tilsynAdapter.notifyItemMoved(fraPos, tilPos);
                    return true;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    Log.d("Removed", tilsynsListe.get(viewHolder.getAdapterPosition()).getNavn());

                    tilsynsListe.remove(viewHolder.getAdapterPosition());
                    tilsynAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());


                }
            });
    private TilsynsViewModel tilsynsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        FragmentActivity activity = this.getActivity();
        boolean isChangingConfigurations = activity != null && activity.isChangingConfigurations();
        if (this.getViewModelStore() != null && !isChangingConfigurations) {
            this.getViewModelStore().clear();
        }






        //this.getViewModelStore().clear();

        //Log.d("Frag", "I was killed");

    }



    @Override
    public void onResume() {
        super.onResume();



        Log.d("Frag", "I was not killed");


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        bindingTilsyn = FragmentTilsynBinding.inflate(inflater, container, false);

        tilsynsViewModel = new ViewModelProvider(this).get(TilsynsViewModel.class);

        View root = bindingTilsyn.getRoot();

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());


        //Deklarasjon av recyclerview og dens adapter

        recyclerView = bindingTilsyn.recyclerViewTilsyn;
        recyclerView.setLayoutManager(layoutManager);


        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(tilsynAdapter);



        tilsynsViewModel.getTilsynListe().observe(this, new Observer<List<Tilsyn>>() {
            @Override
            public void onChanged(List<Tilsyn> tilsynsListeNett) {
                tilsynsListe.addAll(tilsynsListeNett);
                full.addAll(tilsynsListeNett);
                Collections.sort(tilsynsListe);
                Collections.reverse(tilsynsListe);

                tilsynAdapter.setTilsynListe(tilsynsListe);

            }

        });

        return root;
    }

    /**
     * @param menu
     * @param menuInflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tilsynAdapter.getFilter().filter(newText);
                tilsynAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.app_bar_search){

            Log.d("taglaunch", "Search Icon Clicked from fragment");

            return false;

        }

        return super.onOptionsItemSelected(item);

    }*/



    @Override
    public void onTilsynClick(int pos) {

        Intent intent = new Intent(getContext(), TilsynActivity.class);
        if (tilsynsListe.get(pos).getTilsynid() != null) {
            intent.putExtra("test", tilsynsListe.get(pos));


            FragmentActivity activity = this.getActivity();
            if(!activity.isFinishing())
            activity.startActivityFromFragment(this, intent, 1);
            //startActivity(intent);

        }



    }




}
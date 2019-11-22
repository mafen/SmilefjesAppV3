
package com.usn.smilefjes.ui.tilsyn;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.smilefjes.BR;
import com.usn.smilefjes.MainActivity;
import com.usn.smilefjes.R;
import com.usn.smilefjes.TilsynActivity;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.databinding.*;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static androidx.core.content.ContextCompat.getSystemService;


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


        Log.d("Frag", "I was killed");

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

        recyclerView.setAdapter(tilsynAdapter);
        recyclerView.setHasFixedSize(true);


        tilsynsViewModel.getTilsynListe().observe(this, new Observer<List<Tilsyn>>() {
            @Override
            public void onChanged(List<Tilsyn> tilsynsListeNett) {
                tilsynsListe.addAll(tilsynsListeNett);
                full.addAll(tilsynsListeNett);

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
            intent.putExtra("test", tilsynsListe.get(pos).getTilsynid());

            bindingTilsyn.unbind();
            startActivity(intent);
        }



    }




}
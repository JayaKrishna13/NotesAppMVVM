package com.krishna.jaya.notesappmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.krishna.jaya.notesappmvvm.Activity.InsertNotesActivity;
import com.krishna.jaya.notesappmvvm.Adapter.NotesAdapter;
import com.krishna.jaya.notesappmvvm.Model.Notes;
import com.krishna.jaya.notesappmvvm.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel viewModel;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    TextView noFilter, highToLow, lowToHigh;
    List<Notes> filteredAllNotesList;
    ImageView filterbtn;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNotesBtn = findViewById(R.id.insertNotesBtn);
        recyclerView = findViewById(R.id.notesRecyclerView);
        noFilter = findViewById(R.id.noFilter);
        highToLow = findViewById(R.id.highToLow);
        lowToHigh = findViewById(R.id.lowToHigh);
        filterbtn = findViewById(R.id.filter);
        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter==0){
                    noFilter.setVisibility(View.VISIBLE);
                    highToLow.setVisibility(View.VISIBLE);
                    lowToHigh.setVisibility(View.VISIBLE);
                    counter=1;
                }
                else if(counter==1){
                    noFilter.setVisibility(View.INVISIBLE);
                    highToLow.setVisibility(View.INVISIBLE);
                    lowToHigh.setVisibility(View.INVISIBLE);
                    counter=0;

                }
            }

        });


        noFilter.setBackgroundResource(R.drawable.filter_selected);
        noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(0);
                noFilter.setBackgroundResource(R.drawable.filter_selected);
                highToLow.setBackgroundResource(R.drawable.filter_unselected);
                lowToHigh.setBackgroundResource(R.drawable.filter_unselected);

            }
        });
        highToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(1);
                highToLow.setBackgroundResource(R.drawable.filter_selected);
                noFilter.setBackgroundResource(R.drawable.filter_unselected);
                lowToHigh.setBackgroundResource(R.drawable.filter_unselected);

            }
        });
        lowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(2);
                lowToHigh.setBackgroundResource(R.drawable.filter_selected);
                noFilter.setBackgroundResource(R.drawable.filter_unselected);
                highToLow.setBackgroundResource(R.drawable.filter_unselected);

            }
        });

        //viewModel=  ViewModelProviders.of(this).get(NotesViewModel.class);->deprecated now
        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        newNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
            }
        });

        viewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {

                setAdapter(notes);
                filteredAllNotesList = notes;
            }
        });

    }

    private void loadData(int i) {
        if (i == 0) {
            viewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filteredAllNotesList = notes;
                }
            });
        } else if (i == 1) {
            viewModel.highToLow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filteredAllNotesList = notes;
                }
            });
        } else if (i == 2) {
            viewModel.lowToHigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {

                    setAdapter(notes);
                    filteredAllNotesList = notes;
                }
            });
        }

    }

    private void setAdapter(List<Notes> notes) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes);
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem item = menu.findItem(R.id.search_bar);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("please search here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                NotesFilter(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String s) {

        ArrayList<Notes> FilterNames = new ArrayList<>();

        for (Notes notes : this.filteredAllNotesList) {
            if (notes.title.contains(s) || notes.subtitle.contains(s)) {
                FilterNames.add(notes);
            }
        }
        this.notesAdapter.searchNotes(FilterNames);
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
package com.lykanotes.takenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.lykanotes.takenotes.Adapter.NotesAdapter;
import com.lykanotes.takenotes.Model.Notes;
import com.lykanotes.takenotes.R;
import com.lykanotes.takenotes.activity.InsertnoteActivity;
import com.lykanotes.takenotes.activity.Setting;
import com.lykanotes.takenotes.viewmodel.Notesviewmodel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private FloatingActionButton newnotetbn;
private RecyclerView notesrecyclerview;
NotesAdapter notesAdapter;
    Notesviewmodel notesviewmodel;
TextView nofilter,htol,ltoh;
MaterialToolbar toolbar;
Gridnoholder gridnoholder;

List<Notes> filteredAllNotes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
loadSetting();
        //search bar
        toolbar=(MaterialToolbar)findViewById(R.id.appbar);

        MenuItem menuItem= toolbar.getMenu().findItem(R.id.searchicon);
        MenuItem settingMenu=toolbar.getMenu().findItem(R.id.settingicon);


        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("search here");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                notesfilter(newText);

                return false;
            }
        });


        settingMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent= new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
                finish();
                return true;
            }
        });


        //filter
        nofilter=(TextView)findViewById(R.id.nofilter);
        htol=(TextView)findViewById(R.id.htol);
        ltoh=(TextView)findViewById(R.id.ltoh);

        nofilter.setBackgroundResource(R.drawable.filterselecshape);

        nofilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nofilter.setBackgroundResource(R.drawable.filterselecshape);
                htol.setBackgroundResource(R.drawable.filterunshape);
                ltoh.setBackgroundResource(R.drawable.filterunshape);
                notesviewmodel.getAllNotes.observe(MainActivity.this,notes -> {
                    filteredAllNotes=notes;
                    notesAdapter=new NotesAdapter(MainActivity.this,notes);
                    notesrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(gridnoholder.getNo(),StaggeredGridLayoutManager.VERTICAL));
                    notesrecyclerview.setAdapter(notesAdapter);


                });

            }
        });

        htol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nofilter.setBackgroundResource(R.drawable.filterunshape);
                htol.setBackgroundResource(R.drawable.filterselecshape);
                ltoh.setBackgroundResource(R.drawable.filterunshape);
                notesviewmodel.gethtol.observe(MainActivity.this,notes -> {
                    filteredAllNotes=notes;
                    notesAdapter=new NotesAdapter(MainActivity.this,notes);
                    notesrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(gridnoholder.getNo(),StaggeredGridLayoutManager.VERTICAL));
                    notesrecyclerview.setAdapter(notesAdapter);


                });
            }
        });

        ltoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nofilter.setBackgroundResource(R.drawable.filterunshape);
                htol.setBackgroundResource(R.drawable.filterunshape);
                ltoh.setBackgroundResource(R.drawable.filterselecshape);
                notesviewmodel.getltoh.observe(MainActivity.this,notes -> {
                    filteredAllNotes=notes;
                    notesAdapter=new NotesAdapter(MainActivity.this,notes);
                    notesrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(gridnoholder.getNo(),StaggeredGridLayoutManager.VERTICAL));

                    notesrecyclerview.setAdapter(notesAdapter);


                });
            }
        });



        notesrecyclerview=findViewById(R.id.notesrecyclerview);
        notesviewmodel= ViewModelProviders.of(this).get(Notesviewmodel.class);

        newnotetbn=findViewById(R.id.newnotebtn);

        newnotetbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertnoteActivity.class));
            }
        });

        notesviewmodel.getAllNotes.observe(this,notes -> {
            filteredAllNotes=notes;
            notesAdapter=new NotesAdapter(MainActivity.this,notes);
            notesrecyclerview.setLayoutManager(new StaggeredGridLayoutManager(gridnoholder.getNo(),StaggeredGridLayoutManager.VERTICAL));
            notesrecyclerview.setAdapter(notesAdapter);


        });


    }

    private void notesfilter(String newText) {

        ArrayList<Notes> filternotes=new ArrayList<Notes>();
        for (Notes note:this.filteredAllNotes)
        {
            if(note.title.contains(newText) || note.subtitle.contains(newText) || note.datanote.contains(newText))

            {
                   filternotes.add(note);
            }
        }
        this.notesAdapter.searchNotes(filternotes);

    }


    private void loadSetting() {
        SharedPreferences sharedPreferences = getSharedPreferences("gridnosetting", MODE_PRIVATE);


        Gson gson = new Gson();
        String json = sharedPreferences.getString("gridnovalue", null);
        Type type = new TypeToken<Gridnoholder>() {
        }.getType();
        gridnoholder = gson.fromJson(json, type);

        if (gridnoholder == null) {
            gridnoholder = new Gridnoholder(1);
        }



    }
}
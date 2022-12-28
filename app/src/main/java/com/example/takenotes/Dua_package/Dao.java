package com.example.takenotes.Dua_package;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.takenotes.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * from notes_db")
    LiveData< List<Notes>> gettAllNotes();


    @Query("SELECT * from notes_db order by notes_Sort asc")
    LiveData< List<Notes>> gethtol();

    @Query("SELECT * from notes_db order by notes_Sort desc")
    LiveData< List<Notes>> gethloh();

    @Insert
    void insertNotes(Notes...notes);


    @Query("DELETE FROM notes_db WHERE id=:id")
    void deleteNotes(int id);



    @Update
    void updateNotes(Notes note);
}

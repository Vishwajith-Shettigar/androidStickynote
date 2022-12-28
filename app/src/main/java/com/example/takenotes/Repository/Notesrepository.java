package com.example.takenotes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.takenotes.Database.Notesdatabase;
import com.example.takenotes.Dua_package.Dao;
import com.example.takenotes.Model.Notes;

import java.util.List;

public class Notesrepository {

    public Dao dao;
    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> gethtol;
    public LiveData<List<Notes>> getltoh;
    public  Notesrepository(Application application){

        Notesdatabase notesdatabase=Notesdatabase.getNotesDatabaseInstance(application);
        dao=notesdatabase.dao();
        getallNotes=dao.gettAllNotes();
        gethtol=dao.gethtol();
        getltoh=dao.gethloh();

    }

   public void insertNotes(Notes note)
    {
        dao.insertNotes(note);
    }

   public void deleteNotes(int id)
    {
        dao.deleteNotes(id);
    }

  public   void updateNotes(Notes note)
    {
        dao.updateNotes(note);
    }

}

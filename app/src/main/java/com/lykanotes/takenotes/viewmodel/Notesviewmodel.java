package com.lykanotes.takenotes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lykanotes.takenotes.Model.Notes;
import com.lykanotes.takenotes.Repository.Notesrepository;

import java.util.List;

public class Notesviewmodel extends AndroidViewModel {
Notesrepository notesrepository;
public LiveData<List<Notes>>getAllNotes;
    public LiveData<List<Notes>> gethtol;
    public LiveData<List<Notes>> getltoh;
    public Notesviewmodel(@NonNull Application application) {
        super(application);

         notesrepository=new Notesrepository(application);

         getAllNotes=notesrepository.getallNotes;
         gethtol=notesrepository.gethtol;
         getltoh=notesrepository.getltoh;

    }

  public  void  viewinsertNotes(Notes note){
notesrepository.insertNotes(note);

    }


  public  void  viewdeletenotes(int id){
        notesrepository.deleteNotes(id);

    }

  public  void  viewupdatenotes(Notes note){
        notesrepository.updateNotes(note);

    }


}

package com.lykanotes.takenotes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lykanotes.takenotes.Dua_package.Dao;
import com.lykanotes.takenotes.Model.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract class Notesdatabase extends RoomDatabase {


    public abstract Dao dao();
    public  static Notesdatabase INSTANCE;

    public static Notesdatabase getNotesDatabaseInstance(Context context){


        if(INSTANCE==null){
        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),Notesdatabase.class,"notes_db").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }


}

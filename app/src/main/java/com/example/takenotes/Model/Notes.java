package com.example.takenotes.Model;

import androidx.annotation.ColorInt;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes_db")
public class Notes {

    @PrimaryKey(autoGenerate = false)
    public int id;

    @ColumnInfo(name="notes_title")
    public   String title;

    @ColumnInfo(name="notes_subtitle")
    public String subtitle;
    @ColumnInfo(name="notes_date")
    public String date;

    @ColumnInfo(name="notes_datanote")
    public   String datanote;


    @ColumnInfo(name="notes_tpriority")
    public   String priority;

    @ColumnInfo(name="notes_Sort")
    public   String sortnotes;



}

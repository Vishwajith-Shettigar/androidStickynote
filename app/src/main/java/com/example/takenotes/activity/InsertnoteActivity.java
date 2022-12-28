package com.example.takenotes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.takenotes.Model.Notes;
import com.example.takenotes.R;
import com.example.takenotes.viewmodel.Notesviewmodel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class InsertnoteActivity extends AppCompatActivity {

    Notesviewmodel notesviewmodel;
private FloatingActionButton savebtn;
private EditText titleinput,subtitleinput,notesdatainput;
private ImageView redbtn,greenbtn,yellowbtn;
String title,subtitle,notesdata;
String priority="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertnote);

        notesviewmodel= ViewModelProviders.of(this).get(Notesviewmodel.class);
        titleinput=findViewById(R.id.title);
        subtitleinput=findViewById(R.id.subtitle);
        notesdatainput=findViewById(R.id.notedata);

        savebtn=findViewById(R.id.savebtn);

        redbtn=findViewById(R.id.redbtn);
        greenbtn=findViewById(R.id.greenbtn);
        yellowbtn=findViewById(R.id.yellowbtn);

        redbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority="1";
                redbtn.setImageResource(R.drawable.ic_baseline_done_24);
                greenbtn.setImageResource(0);
                yellowbtn.setImageResource(0);

            }
        });
        greenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority="2";
                greenbtn.setImageResource(R.drawable.ic_baseline_done_24);
                redbtn.setImageResource(0);
                yellowbtn.setImageResource(0);

            }
        });

        yellowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority="3";
                yellowbtn.setImageResource(R.drawable.ic_baseline_done_24);
                greenbtn.setImageResource(0);
                redbtn.setImageResource(0);

            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=titleinput.getText().toString();
                subtitle=subtitleinput.getText().toString();
                notesdata=notesdatainput.getText().toString();
                Notes notes=new Notes();
                notes.id= (int) (((Math.random()*100*Math.random()*100)/Math.random()*100));

                notes.title=title;
                notes.subtitle=subtitle;
                notes.datanote=notesdata;
                notes.priority=priority;
                LocalDate myObj = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     myObj = LocalDate.now();

                }
                LocalTime myObjt = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    myObjt = LocalTime.now();
                }

                String sortnotet=myObjt.toString();

          sortnotet=sortnotet.replaceAll(":","");

                Log.e("&&",sortnotet+"ppp");

                String sortnote=myObj.toString();
                sortnote=sortnote.replaceAll("-","");
                notes.sortnotes=(sortnote+sortnotet);

                Log.e("&&",sortnote+sortnotet+"ppp");

                Date date=new Date();
                CharSequence chr= DateFormat.format("MMMM d yyyy",date.getTime());
   notes.date=chr.toString();

                notesviewmodel.viewinsertNotes(notes);
                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                finish();


            }
        });


    }
}
package com.example.takenotes.activity;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takenotes.AppWidget;
import com.example.takenotes.Model.Notes;
import com.example.takenotes.R;
import com.example.takenotes.viewmodel.Notesviewmodel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class UpdatenoteActivity extends AppCompatActivity {

    Notesviewmodel notesviewmodel;
    private FloatingActionButton updatebtn;
    private EditText titleinput,subtitleinput,notesdatainput;
    private ImageView redbtn,greenbtn,yellowbtn;
    String utitle,usubtitle,unotesdata,udate;
    int id;
MaterialToolbar toolbar;
    String priority="1";
    Notes notes=new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenote);
toolbar=(MaterialToolbar) findViewById(R.id.updateappbar);

toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId()==R.id.deletenote){

            TextView yes,no;
            BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(UpdatenoteActivity.this,R.style.BottomSheetStyle);

            View view= LayoutInflater.from(UpdatenoteActivity.this).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottomsheet));
            bottomSheetDialog.setContentView(view);

            yes=view.findViewById(R.id.deleteyes);
            no=view.findViewById(R.id.deleteno);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notesviewmodel.viewdeletenotes(id);
                    bottomSheetDialog.dismiss();
                    finish();
                    Toast.makeText(UpdatenoteActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();

                }
            });
            bottomSheetDialog.show();

        }
        else if(item.getItemId() == R.id.stickynote){
            String title=titleinput.getText().toString();
            String subtitle=subtitleinput.getText().toString();
            String notesdata=notesdatainput.getText().toString();





            notes.id= id;

            notes.title=title;
            notes.subtitle=subtitle;
            notes.datanote=notesdata;
            notes.priority=priority;
            updateWidget(  notes.title,notes.subtitle,notes.datanote,  notes.priority);

        }
        return  false;
    }
});
        id=getIntent().getIntExtra("id",0);
        utitle=getIntent().getStringExtra("title");
        usubtitle=getIntent().getStringExtra("subtitle");
        priority=getIntent().getStringExtra("priority");
        unotesdata=getIntent().getStringExtra("notesdata");
        udate=getIntent().getStringExtra("date");



        notesviewmodel= ViewModelProviders.of(this).get(Notesviewmodel.class);
        titleinput=findViewById(R.id.title);
        subtitleinput=findViewById(R.id.subtitle);
        notesdatainput=findViewById(R.id.notesdata);



        updatebtn=findViewById(R.id.updatebtn);

        redbtn=findViewById(R.id.redbtn);
        greenbtn=findViewById(R.id.greenbtn);
        yellowbtn=findViewById(R.id.yellowbtn);



        titleinput.setText(utitle);
        subtitleinput.setText(usubtitle);
        notesdatainput.setText(unotesdata);

        if(priority.equals("1")){
            redbtn.setImageResource(R.drawable.ic_baseline_done_24);
            greenbtn.setImageResource(0);
            yellowbtn.setImageResource(0);
        }
        else if(priority.equals("2")){
            greenbtn.setImageResource(R.drawable.ic_baseline_done_24);
            redbtn.setImageResource(0);
            yellowbtn.setImageResource(0);
        }
        else
        {
            yellowbtn.setImageResource(R.drawable.ic_baseline_done_24);
            greenbtn.setImageResource(0);
            redbtn.setImageResource(0);
        }

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


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String title=titleinput.getText().toString();
               String subtitle=subtitleinput.getText().toString();
               String notesdata=notesdatainput.getText().toString();





                notes.id= id;

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



                String sortnote=myObj.toString();
                sortnote=sortnote.replaceAll("-","");
                notes.sortnotes=sortnote+sortnotet;

                Date date=new Date();
                CharSequence chr= DateFormat.format("MMMM d yyyy",date.getTime());
                notes.date=chr.toString();

                notesviewmodel.viewupdatenotes(notes);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                finish();

            }
        });



    }

    public void updateWidget(String t,String st,String dt,String priority)
    {
        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(this);
        RemoteViews remoteViews=new RemoteViews(this.getPackageName(),R.layout.widgetlayout);
        ComponentName thewidget=new ComponentName(this, AppWidget.class);

        if(priority.equals("1")) {
            remoteViews.setInt(R.id.widgetlayoutv, "setBackgroundColor",
                    Color.RED);
        }
        else if(priority.equals("2"))
        {
            remoteViews.setInt(R.id.widgetlayoutv, "setBackgroundColor",
                    Color.GREEN);
        }
        else if(priority.equals("3"))
        {
            remoteViews.setInt(R.id.widgetlayoutv, "setBackgroundColor",
                    Color.YELLOW);
        }
        remoteViews.setTextViewText(R.id.wtitle,t);
        remoteViews.setTextViewText(R.id.wsubtitle,st);
        remoteViews.setTextViewText(R.id.wnotedata,dt);


        appWidgetManager.updateAppWidget(thewidget,remoteViews);
    }

}
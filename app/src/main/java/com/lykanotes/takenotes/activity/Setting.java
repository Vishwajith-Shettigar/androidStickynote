package com.lykanotes.takenotes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lykanotes.takenotes.Gridnoholder;
import com.lykanotes.takenotes.MainActivity;
import com.lykanotes.takenotes.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Setting extends AppCompatActivity {
TextView gridno;
Gridnoholder gridnoholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        gridno=findViewById(R.id.gridno);
        loadSetting();
        gridno.setText(""+gridnoholder.getNo());

        gridno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder builder= new MaterialAlertDialogBuilder(Setting.this);
                builder.setTitle("Select grid column");

                CharSequence list[]={"1","2","3"};
                // to show list you can have message and buttons like posi and neg
                builder.setSingleChoiceItems(list, gridnoholder.getNo()-1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String n= (String) list[which];
  gridno.setText(n);
                       gridnoholder.setNo((int)(Integer.parseInt(n)));
                        saveSettingvalues();
                        dialog.dismiss(); // this will dismiss the dialogue
                    }
                });
                builder.show();

            }
        });

        TextView submitfeed=findViewById(R.id.submitfeed);
        submitfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"zekromvishwa56789@gmail.com"});
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Feedback Note app");
             EditText feedback=findViewById(R.id.feedback);
                intent.putExtra(android.content.Intent.EXTRA_TEXT, feedback.getText());
                startActivity(Intent.createChooser(intent,"Send Email"));
                feedback.setText("");
            }
        });




    }


    @Override
    public void onBackPressed() {

        Intent send = new Intent(getApplicationContext(), MainActivity.class);


        startActivity(send);
      finish();
    }


    private void saveSettingvalues() {
        SharedPreferences sharedPreferences = getSharedPreferences("gridnosetting", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
;
        String json = gson.toJson(gridnoholder);
        editor.putString("gridnovalue", json);
        editor.apply();


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
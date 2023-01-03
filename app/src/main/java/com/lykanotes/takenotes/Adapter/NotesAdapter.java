package com.lykanotes.takenotes.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lykanotes.takenotes.MainActivity;
import com.lykanotes.takenotes.Model.Notes;
import com.lykanotes.takenotes.R;
import com.lykanotes.takenotes.activity.UpdatenoteActivity;

import java.util.List;
import java.util.Objects;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {
    MainActivity mainActivity;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

this.mainActivity=mainActivity;
this.notes=notes;

    }


    public  void searchNotes(List<Notes> filterednotes)
    {
this.notes=filterednotes;
notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }






    @Override
    public void onBindViewHolder(@NonNull notesViewholder holder, int position) {

        holder.cardView.setBackgroundResource(R.color.green);
        holder.title.setText(notes.get(position).title);
        holder.subtitle.setText(notes.get(position).subtitle);
        holder.date.setText(notes.get(position).date);

        Log.e("&&",notes.get(position).sortnotes +"lllll");
        if(Objects.equals(notes.get(position).priority, "1"))
        {
            holder.cardView.setBackgroundResource(R.color.red);
        holder.priority.setBackgroundResource(R.drawable.red_dot);

        }
        else if(Objects.equals(notes.get(position).priority, "2"))
        {
            holder.cardView.setBackgroundResource(R.color.green);
            holder.priority.setBackgroundResource(R.drawable.green_dot);
        }
        else
        {
            holder.cardView.setBackgroundResource(R.color.yellow);
            holder.priority.setBackgroundResource(R.drawable.yellow_dot);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent= new Intent(mainActivity, UpdatenoteActivity.class);
                intent.putExtra("title",notes.get(position).title);
                intent.putExtra("subtitle",notes.get(position).subtitle);
                intent.putExtra("notesdata",notes.get(position).datanote);
                intent.putExtra("priority",notes.get(position).priority);
                intent.putExtra("date",notes.get(position).date);
                intent.putExtra("id",notes.get(position).id);
                mainActivity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

  static  class notesViewholder extends  RecyclerView.ViewHolder{

        TextView title,subtitle,date;
        View priority;
CardView cardView;

        public notesViewholder(@NonNull View itemView) {
            super(itemView);
cardView=itemView.findViewById(R.id.card);
            title=itemView.findViewById(R.id.title);
            subtitle=itemView.findViewById(R.id.subtitle);
            date=itemView.findViewById(R.id.date);
priority=itemView.findViewById(R.id.priority);


        }
    }

}

package com.krishna.jaya.notesappmvvm.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krishna.jaya.notesappmvvm.Activity.UpdateNotesActivity;
import com.krishna.jaya.notesappmvvm.MainActivity;
import com.krishna.jaya.notesappmvvm.Model.Notes;
import com.krishna.jaya.notesappmvvm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesSearch;



    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity=mainActivity;
        this.notes=notes;
        allNotesSearch=new ArrayList<>();
    }

    public void searchNotes(List<Notes> filterednotes){
        this.notes=filterednotes;
        notifyDataSetChanged();

    }

    @NonNull
    @NotNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotesViewHolder holder, int position) {
        Notes note=notes.get(position);

        if(note.notesPriority.equals("1")){
            holder.priority.setBackgroundResource(R.drawable.green_shape);
        }
        else if(note.notesPriority.equals("2")){
            holder.priority.setBackgroundResource(R.drawable.yellow_shape);
        }else if(note.notesPriority.equals("3")){
            holder.priority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(note.title);
        holder.subtitle.setText(note.subtitle);
        holder.date.setText(note.date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mainActivity, UpdateNotesActivity.class);
                intent.putExtra("id",note.id);
                intent.putExtra("title",note.title);
                intent.putExtra("subtitle",note.subtitle);
                intent.putExtra("priority",note.notesPriority);
                intent.putExtra("notes",note.notes);
                mainActivity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView title,subtitle,date;
        View priority;

        public NotesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.itemTitle);
            subtitle=itemView.findViewById(R.id.itemSubTitle);
            date=itemView.findViewById(R.id.itemDate);
            priority=itemView.findViewById(R.id.itemPriority);


        }
    }
}

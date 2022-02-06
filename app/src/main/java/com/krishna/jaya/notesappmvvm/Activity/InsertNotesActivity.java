package com.krishna.jaya.notesappmvvm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.krishna.jaya.notesappmvvm.MainActivity;
import com.krishna.jaya.notesappmvvm.Model.Notes;
import com.krishna.jaya.notesappmvvm.R;
import com.krishna.jaya.notesappmvvm.ViewModel.NotesViewModel;
import com.krishna.jaya.notesappmvvm.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title, subTitle, notes;
    NotesViewModel notesViewModel;
    String priority="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Create Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notesViewModel= new ViewModelProvider(this).get(NotesViewModel.class);
        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority="1";
            }
        });
        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.greenPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority="2";
            }
        });
        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                priority="3";
            }
        });


        binding.doneNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = binding.notesTitle.getText().toString();
                subTitle = binding.notesSubTitle.getText().toString();
                notes = binding.notes.getText().toString();

                CreateNotes(title,subTitle,notes);


            }
        });

    }

    private void CreateNotes(String title, String subTitle, String notes) {
        Date date=new Date();
        CharSequence sequence= DateFormat.format("MMMM d yyyy",date);

        Notes notes1=new Notes();
        notes1.title=title;
        notes1.subtitle=subTitle;
        notes1.notes=notes;
        notes1.date=sequence.toString();
        notes1.notesPriority=priority;
        notesViewModel.insertNote(notes1);
        finish();
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
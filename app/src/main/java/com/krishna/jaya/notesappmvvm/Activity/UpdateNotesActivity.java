package com.krishna.jaya.notesappmvvm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.krishna.jaya.notesappmvvm.MainActivity;
import com.krishna.jaya.notesappmvvm.Model.Notes;
import com.krishna.jaya.notesappmvvm.R;
import com.krishna.jaya.notesappmvvm.ViewModel.NotesViewModel;
import com.krishna.jaya.notesappmvvm.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String stitle, ssubtitle, spriority, snotes;
    int iid;
    NotesViewModel notesViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Update Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("notes");
        iid = getIntent().getIntExtra("id", 0);


        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);
        if(spriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);

        }
        else if(spriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);

        }else if(spriority.equals("3")){
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);

        }

        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "1";
            }
        });
        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.greenPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "2";
            }
        });
        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                priority = "3";
            }
        });

        binding.updateNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.upTitle.getText().toString();
                String subTitle = binding.upSubtitle.getText().toString();
                String notes = binding.upNotes.getText().toString();

                UpdateNotes(title, subTitle, notes);

            }
        });


    }

    private void UpdateNotes(String title, String subTitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d yyyy", date);
        Notes updatenotes = new Notes();
        updatenotes.id = iid;
        updatenotes.title = title;
        updatenotes.subtitle = subTitle;
        updatenotes.notes = notes;
        updatenotes.date = sequence.toString();
        updatenotes.notesPriority = priority;
        notesViewModel.updateNote(updatenotes);
        finish();
        Toast.makeText(this, updatenotes.title +"Notes updated Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== R.id.delete){
            BottomSheetDialog sheetDialog=new BottomSheetDialog(UpdateNotesActivity.this, R.style.BottomSheetStyle);
            View view= LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_layout,(LinearLayout)findViewById(R.id.bottomSheet));
            sheetDialog.setContentView(view);
            sheetDialog.show();

            TextView Yes,No;

            Yes=view.findViewById(R.id.deleteYes);
            No=view.findViewById(R.id.deleteNo);

            Yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notesViewModel.deleteNote(iid);
                    finish();
                }
            });
            No.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sheetDialog.dismiss();
                }
            });
        }


       //for setting on click for up arrow button i.e setDisplayHomeAsUpEnabled(true);
        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(this, MainActivity.class));
          finish();
          return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.krishna.jaya.notesappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.krishna.jaya.notesappmvvm.Model.Notes;
import com.krishna.jaya.notesappmvvm.Repsitory.NotesRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;


    public NotesViewModel(@NonNull @NotNull Application application) {
        super(application);

        repository=new NotesRepository(application);
        getAllNotes= repository.getAllNotes;
        highToLow=repository.highToLow;
        lowToHigh=repository.lowToHigh;

    }
    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }
    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }
    public void deleteNote(int id){
        repository.deleteNotes(id);
    }

}

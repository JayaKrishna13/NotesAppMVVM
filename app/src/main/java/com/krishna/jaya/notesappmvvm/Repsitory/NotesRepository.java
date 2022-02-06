package com.krishna.jaya.notesappmvvm.Repsitory;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.krishna.jaya.notesappmvvm.Dao.NotesDao;
import com.krishna.jaya.notesappmvvm.Database.NotesDatabase;
import com.krishna.jaya.notesappmvvm.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesRepository(Context context){

         NotesDatabase database=NotesDatabase.getDatabaseInstance(context);
         notesDao=database.notesDao();
         getAllNotes=notesDao.getAllNotes();
        highToLow=notesDao.highToLow();
        lowToHigh=notesDao.lowToHigh();


    }

    public void insertNotes(Notes notes){
        notesDao.insert(notes);
    }

    public void updateNotes(Notes notes){
        notesDao.update(notes);
    }

    public void deleteNotes(int id){
        notesDao.delete(id);
    }


}

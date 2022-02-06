package com.krishna.jaya.notesappmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.krishna.jaya.notesappmvvm.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority Asc")
    LiveData<List<Notes>> lowToHigh();


    @Insert
    public void insert(Notes... notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    public void delete(int id);

    @Update
    public void update(Notes... notes);
}

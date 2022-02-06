package com.krishna.jaya.notesappmvvm.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.krishna.jaya.notesappmvvm.Dao.NotesDao;
import com.krishna.jaya.notesappmvvm.Model.Notes;


@Database(entities = {Notes.class},version =2,exportSchema = false)
public abstract  class NotesDatabase extends RoomDatabase {

    public  abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;



    public static NotesDatabase getDatabaseInstance(Context context){
      if(INSTANCE==null){

          INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                  NotesDatabase.class,"Notes_database")
                  .fallbackToDestructiveMigration()
                  .allowMainThreadQueries()
                  .build();
      }
      return INSTANCE;
    }
}

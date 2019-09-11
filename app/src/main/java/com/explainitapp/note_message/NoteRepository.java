package com.explainitapp.note_message;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by rikmen00@gmail.com on 11.09.2019.
 */

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List <Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);


    }
    public void uppdate(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData <List <Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delite(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;

        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}

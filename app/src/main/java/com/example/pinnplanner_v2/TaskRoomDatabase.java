package com.example.pinnplanner_v2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static volatile TaskRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TaskDao dao = INSTANCE.taskDao();
                dao.deleteAll();

                Task task = new Task("Tugas StatProb","Deskripsi","3/042021","10:00 pm", "notStarted");
                dao.insert(task);
                task = new Task("Tugas Pkn","Deskripsi","10/04/2021","07:00","notStarted");
                dao.insert(task);
                task = new Task("Tugas StatProb","Deskripsi","31/04/2021","23:59","notStarted");
                dao.insert(task);
                task = new Task("Tugas PPL","Deskripsi","13/04/2021","17:00 pm", "inProgress");
                dao.insert(task);
                task = new Task("Tugas APSI","Deskripsi","7/04/2021","12:00","inProgress");
                dao.insert(task);
                task = new Task("Tugas PPL","Deskripsi","19/05/2021","10:00","inProgress");
                dao.insert(task);
                task = new Task("Tugas PPB","Tugas mockup dan deskripsi solusi","12/04/2021","10:00", "complete");
                dao.insert(task);
                task = new Task("Koding tugas PHP","latihan 14","7/04/2021","05:00","complete");
                dao.insert(task);
                task = new Task("Tugas PPB","Tugas mockup dan deskripsi solusi","14/04/2021","10:00","complete");
                dao.insert(task);
            });
        }
    };

}

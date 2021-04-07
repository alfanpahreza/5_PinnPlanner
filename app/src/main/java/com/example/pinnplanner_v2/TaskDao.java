package com.example.pinnplanner_v2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getQueriedTask();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();


}

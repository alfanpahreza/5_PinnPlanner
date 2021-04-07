package com.example.pinnplanner_v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "dueDate")
    private String dueDate;

    @NonNull
    @ColumnInfo(name = "dueTime")
    private String dueTime;


    @ColumnInfo(name = "status")
    private String status;

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public boolean equals(Task tsk) {
        return (this == tsk);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.dueDate);
        dest.writeString(this.dueTime);
        dest.writeString(this.status);
    }
    public Task(@NonNull String title,@NonNull String description,@NonNull String dueDate,@NonNull String dueTime, String status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.status = status;
    }
    public Task(Parcel in){
        this.title = in.readString();
        this.description = in.readString();
        this.dueDate = in.readString();
        this.dueTime = in.readString();
        this.status = in.readString();
    }

    public Task getTask(){return this;}
    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
    @NonNull
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(@NonNull String dueDate) {
        this.dueDate = dueDate;
    }
    @NonNull
    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(@NonNull String dueTime) {
        this.dueTime = dueTime;
    }

    public String getStatus() {
        if(!(status.equals("complete") || status.equals("notStarted") || status.equals("inProgress"))){
            return "Progress not set";
        }else{
            return status;
        }
    }

    public void setStatus(String status){
        this.status = status;
    }
}

package com.example.pinnplanner_v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class TaskListAdapter extends ListAdapter<Task,TaskListAdapter.MyViewHolder> {

    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText;
        private TextView descriptionText;
        private TextView dateText;
        private TextView timeText;

        public MyViewHolder(final View view){
            super(view);
            titleText = view.findViewById(R.id.titleText);
            descriptionText = view.findViewById(R.id.descText);
            dateText = view.findViewById(R.id.dateText);
            timeText = view.findViewById(R.id.timeText);
        }
        public void bind(Task mTask) {
            titleText.setText(mTask.getTitle());
            descriptionText.setText(mTask.getDescription());
            dateText.setText(mTask.getDueDate());
            timeText.setText(mTask.getDueTime());
        }
    }
    @NonNull
    @Override
    public TaskListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task current = getItem(position);
        holder.bind(current.getTask());
    }

    static class taskDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTask().equals(newItem.getTask());
        }
    }
}

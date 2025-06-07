package com.ixuea.courses.mymusic.animator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class PublishedTaskListAdapter extends RecyclerView.Adapter<PublishedTaskListAdapter.TaskViewHolder> {

    private Context context;
    private List<String> taskList;

    public PublishedTaskListAdapter(Context context) {
        this.context = context;
        this.taskList = new ArrayList<>();
    }

    public void setData(List<String> taskList) {
        this.taskList.clear();
        this.taskList.addAll(taskList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublishedTaskListAdapter.TaskViewHolder holder, int position) {
        String st = taskList.get(position);
        holder.textView.setText(st);
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(android.R.id.text1);
        }
    }
}

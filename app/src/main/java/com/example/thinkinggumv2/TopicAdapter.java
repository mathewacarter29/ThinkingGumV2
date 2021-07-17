package com.example.thinkinggumv2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    List<String> topics;
    ViewHolder.onTopicListener listener;

    public TopicAdapter(List<String> topics, ViewHolder.onTopicListener onTopicListener) {
        this.topics = topics;
        this.listener = onTopicListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View topicView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);

        return new ViewHolder(topicView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(topics.get(position));
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView topicTv;
        onTopicListener listener;

        public ViewHolder(View topicItem, onTopicListener onTopicListener) {
            super(topicItem);

            topicTv = topicItem.findViewById(R.id.topicTv);
            listener = onTopicListener;

            topicItem.setOnClickListener(this);
        }

        public void bind(String topic) {
            topicTv.setText(topic);
        }

        @Override
        public void onClick(View v) {
            listener.onTopicClick(getAdapterPosition());
        }

        public interface onTopicListener {
            void onTopicClick(int position);
        }
    }
}


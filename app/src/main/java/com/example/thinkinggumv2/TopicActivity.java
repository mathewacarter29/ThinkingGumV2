package com.example.thinkinggumv2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class TopicActivity extends AppCompatActivity implements TopicAdapter.ViewHolder.onTopicListener {

    public final static String TAG = "TopicActivity";

    ArrayList<String> topics;
    TopicAdapter adapter;
    RecyclerView topicsRv;

    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        topicsRv = (RecyclerView) findViewById(R.id.topicsRv);
        topics = new ArrayList<>();

        adapter = new TopicAdapter(topics, this);
        topicsRv.setAdapter(adapter);
        topicsRv.setLayoutManager(new LinearLayoutManager(this));

        ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thinking-gum-default-rtdb.firebaseio.com/");


        readTopics();

    }

    private void readTopics() {
        ValueEventListener queryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    String topic = (String) next.getValue();
                    Log.i(TAG, "Value = " + topic);
                    topics.add(topic);

                }
                Log.d(TAG, topics.get(0));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Query query = ref.orderByKey();
        query.addListenerForSingleValueEvent(queryValueListener);
    }


    @Override
    public void onTopicClick(int position) {
        //Where the pop up is made
    }
}
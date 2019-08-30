package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FavouritesActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mdatabaseRef;
    FirebaseRecyclerAdapter<Model, LikeViewHolder> firebaseRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        //RecycleView
        mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setHasFixedSize(true);
        //set layout as linear Layout
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mdatabaseRef=mFirebaseDatabase.getReference("uploads").child("categoryType");
        getEventData();
    }
    @Override
    protected void onStart() {
        super.onStart();

        try {
            firebaseRecyclerAdapter.startListening();
        }catch (Exception e){

        }

    }

    public void getEventData(){

        final Query query = FirebaseDatabase.getInstance().getReference("uploads").child("categoryType");
        FirebaseApp.initializeApp(this);

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(query, Model.class)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model,LikeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull LikeViewHolder holder, int position, @NonNull final Model model) {

                holder.mLikes.setText(model.getId());
                holder.mTitle.setText(model.getTitle());





            }

            @NonNull
            @Override
            public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.likesrow, viewGroup, false);
//
                return new LikeViewHolder(view);
            }
        };


        //set adapter to recycle view
        mRecycleView.setAdapter(firebaseRecyclerAdapter);
    }



}

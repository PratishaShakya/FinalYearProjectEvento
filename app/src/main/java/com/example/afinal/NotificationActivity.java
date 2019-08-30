package com.example.afinal;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.appcompat.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NotificationActivity extends AppCompatActivity {
    RecyclerView mRecycleView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mdatabaseRef;
    String categoryType;
    FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter;
    DataAdapterClass adapterClass1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


//RecycleView
        mRecycleView = findViewById(R.id.recycleview);
        mRecycleView.setHasFixedSize(true);
        //set layout as linear Layout



        categoryType = getIntent().getExtras().getString("categoryType");
//        Toast.makeText(this, "Category : " + categoryType, Toast.LENGTH_SHORT).show();

        //send query to firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef = mFirebaseDatabase.getReference("uploads");

        getEventData();
        getSupportActionBar().setTitle(categoryType);

    }

//    searh events
    private void firebaseSearch(String searchText) {
        Query firebaseSearchQuery = mdatabaseRef.child(categoryType).orderByChild("title").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseApp.initializeApp(this);

//        FirebaseRecyclerOptions<Model> options =
//                new FirebaseRecyclerOptions.Builder<Model>()
//                        .setQuery(firebaseSearchQuery, Model.class)
//                        .build();

        FirebaseRecyclerOptions< Model > options =  new FirebaseRecyclerOptions.Builder< Model >().setQuery(firebaseSearchQuery, new SnapshotParser< Model >() {
                    @NonNull
                    @Override
                    public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                        Model model = snapshot.getValue(Model.class);
                        model.setId(snapshot.getKey());
                        System.out.println("Model : "+snapshot.getKey());
                        return model;
                    } }).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final Model model) {

                holder.mTextView.setText(model.getTitle());
                Picasso.get().load(model.getImgUrl()).into(holder.mImageTv);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),EventsDetails.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("image",model.getImgUrl());
                        intent.putExtra("desc",model.getEventDesc());
                        intent.putExtra("date",model.getEventDateTime());
                        intent.putExtra("location",model.getEventLocation());
                        intent.putExtra("createdBy",model.getCreatedBy());
                        intent.putExtra("category",categoryType);
                        intent.putExtra("id",model.getId());
                        intent.putExtra("rate",model.getRate());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
//
                return new ViewHolder(view);
            }
        };


        //set adapter to recycle view
        mRecycleView.setAdapter(firebaseRecyclerAdapter);


    }
//    load data on recycle view


    @Override
    protected void onStart() {
        super.onStart();

        try{
            firebaseRecyclerAdapter.startListening();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getEventData(){

        final DatabaseReference query = FirebaseDatabase.getInstance().getReference("uploads").child(categoryType);
        FirebaseApp.initializeApp(this);
//
//        FirebaseRecyclerOptions<Model> options =
//                new FirebaseRecyclerOptions.Builder<Model>()
//                        .setQuery(query, Model.class)
//                        .build();

//        FirebaseRecyclerOptions< Model > options1 =  new FirebaseRecyclerOptions.Builder< Model >().setQuery(query, new SnapshotParser< Model >() {
//            @NonNull
//            @Override
//            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
//                Model model = snapshot.getValue(Model.class);
//                model.setId(snapshot.getKey());
//                System.out.println("Model : "+snapshot.getKey());
//                return model;
//            }
//
////            @NonNull
////            @Override
////            public ModelClass parseSnapshot(@NonNull DocumentSnapshot snapshot) {
////                Model modelClass = snapshot.toObject(ModelClass.class);
////                // so i wanted to add the key of the node as a field in the node object.
////                modelClass.setId(snapshot.getId());
////                return modelClass;
////            }
//        }).build();
//
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options1) {
//            @Override
//            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final Model model) {
//
//                holder.mTextView.setText(model.getTitle());
//                Picasso.get().load(model.getImgUrl()).into(holder.mImageTv);
//
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(),EventsDetails.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("title",model.getTitle());
//                        intent.putExtra("image",model.getImgUrl());
//                        intent.putExtra("desc",model.getEventDesc());
//                        intent.putExtra("date",model.getEventDateTime());
//                        intent.putExtra("location",model.getEventLocation());
//                        intent.putExtra("createdBy",model.getCreatedBy());
//                        intent.putExtra("category",categoryType);
//                        intent.putExtra("id",model.getId());
//                        startActivity(intent);
//                    }
//                });
//
//            }
//
//            @NonNull
//            @Override
//            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
////
//                return new ViewHolder(view);
//            }
//
//            @Override
//            public Model getItem(int position) {
//                return super.getItem(getItemCount() - 1 - position);
//            }
//        };

//
//
//        Query queryRef = myRef.orderByChild("score").limitToFirst(100);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               List<Model> models = new ArrayList<>();
               List<Integer> like = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Model model = postSnapshot.getValue(Model.class);
                    model.setId(postSnapshot.getKey());
                    Toast.makeText(NotificationActivity.this, "Value : "+model.getCreatedBy(), Toast.LENGTH_SHORT).show();
//                    model.setCreatedBy();
                        models.add(model);


                    like.add(model.getLike());

//                      Log.d("test"," values is " + score.getName()  + " " + score.getScore());
                }

//                Collections.sort(models, new Comparator<Model>() {
//                    @Override
//                    public int compare(Model lhs, Model rhs) {
//                        return lhs.getLike().compareTo(rhs.getName());
//                    }
//                });

                Collections.reverse(models);

                DataAdapterClass adapterClass  = new DataAdapterClass(getApplicationContext(),models);
                adapterClass1 = new DataAdapterClass(getApplicationContext(),models);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                mRecycleView.setLayoutManager(layoutManager);

                mRecycleView.setAdapter(adapterClass);
                mRecycleView.setAdapter(adapterClass1);

                adapterClass.notifyDataSetChanged();

                adapterClass1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this add items to the action bar if it present
        getMenuInflater().inflate(R.menu.commonmenu, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                firebaseSearch(query);
                (adapterClass1).getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filter as you type
//                firebaseSearch(newText);
                (adapterClass1).getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //handle other action bar item click here
        if (id == R.id.app_bar_search) {
            //TODO
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class DataAdapterClass extends RecyclerView.Adapter<DataAdapterClass.ViewHolder> implements Filterable {
        Context context;
        List<Model> modelList;
        private List<Model> filterModelList;

        public DataAdapterClass(Context context, List<Model> modelList) {
            this.context = context;
            this.modelList = modelList;
            filterModelList = new ArrayList<>(modelList);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

            final Model model = modelList.get(i);

            holder.mTextView.setText(model.getTitle());
            Picasso.get().load(model.getImgUrl()).into(holder.mImageTv);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context,EventsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("image",model.getImgUrl());
                intent.putExtra("desc",model.getEventDesc());
                intent.putExtra("date",model.getEventDateTime());
                intent.putExtra("location",model.getEventLocation());
                intent.putExtra("createdBy",model.getCreatedBy());
                intent.putExtra("category",categoryType);
                intent.putExtra("id",model.getId());
                intent.putExtra("likes",String.valueOf(model.getLike()));
                intent.putExtra("rate",model.getRate());
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    List<Model> filteredResults = new ArrayList<>();
                    if (constraint.length() == 0) {
                        filteredResults.addAll(filteredResults);
                    } else {
                        filteredResults = getFilteredResults(constraint.toString().toLowerCase().trim());
                    }

                    FilterResults results = new FilterResults();
                    results.values = filteredResults;


                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    modelList.clear();
                    modelList.addAll((List)results.values);

                    notifyDataSetChanged();
                }
            };
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTextView;
            ImageView mImageTv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                mTextView=itemView.findViewById(R.id.rTitleTv);
                mImageTv=itemView.findViewById(R.id.rImageView);

            }

        }
        private List<Model> getFilteredResults(String constraint) {
            List<Model> results = new ArrayList<>();
            for (Model item : filterModelList) {
                if (item.getTitle().toLowerCase().contains(constraint)) {
                    results.add(item);
                }
            }
            return results;
        }
    }


}

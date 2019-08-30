package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapterClass extends RecyclerView.Adapter<DataAdapterClass.ViewHolder> {
    Context context;
    List<Model> modelList;

    public DataAdapterClass(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        final Model model = modelList.get(i);

        holder.mTextView.setText(model.getTitle());
        Picasso.get().load(model.getImgUrl()).into(holder.mImageTv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EventsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("image",model.getImgUrl());
                intent.putExtra("desc",model.getEventDesc());
                intent.putExtra("date",model.getEventDateTime());
                intent.putExtra("location",model.getEventLocation());
                intent.putExtra("createdBy",model.getCreatedBy());
                intent.putExtra("rate","120");
//                intent.putExtra("category",categoryType);
                intent.putExtra("id",model.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
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
}

package com.example.afinal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;
    ImageView mImageTv;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

         mTextView=itemView.findViewById(R.id.rTitleTv);
         mImageTv=itemView.findViewById(R.id.rImageView);

    }



}

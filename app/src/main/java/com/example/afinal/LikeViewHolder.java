package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LikeViewHolder extends RecyclerView.ViewHolder {
    TextView mLikes,mTitle;

    public LikeViewHolder(@NonNull View itemView) {
        super(itemView);

        mLikes=itemView.findViewById(R.id.rlikes);


    }

}

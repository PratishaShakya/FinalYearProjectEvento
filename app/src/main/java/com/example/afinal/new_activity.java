package com.example.afinal;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

public class new_activity extends AppCompatActivity {
    SliderLayout sliderLayout;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        sliderLayout = findViewById(R.id.imageslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(1);


        setSliderViews();

    }
    public void setSliderViews()
    {
        for( int i =0 ; i<=1 ; i++)
        {
            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i)
            {
                case 0 :
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/events-b9343.appspot.com/o/uploads%2F1565493919882.jpg?alt=media&token=5112ae50-1acb-4e9f-8581-d81e047de932");

                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/events-b9343.appspot.com/o/uploads%2F1565496305087.jpg?alt=media&token=c79af582-9a36-4ceb-af49-31ed93cc8287");
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("set description" +(i+1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(new_activity.this,"this is slider"+(finalI+1), Toast.LENGTH_SHORT).show();
                }
            });
            sliderLayout.addSliderView(sliderView);
        }
    }
}

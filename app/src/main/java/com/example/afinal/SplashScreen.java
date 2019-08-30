package com.example.afinal;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private int SLEEP_TIMER=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }
    private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000*SLEEP_TIMER);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent=new Intent(SplashScreen.this, SignIn.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }
    }
}

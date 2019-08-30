package com.example.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity {

    ProgressDialog dialog;
    TextView changePwd;
    TextView deactivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        changePwd = findViewById(R.id.changePass);
        deactivate = findViewById(R.id.deactivate);
        dialog = new ProgressDialog(this);
        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent i = new Intent(SettingActivity.this , ChangePassword.class);
//                startActivity(i);
                startActivity(new Intent(SettingActivity.this,activity_changepassword.class));
            }
        });
        deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null){
                    dialog.setMessage("Deactivating....Please Wait!!");
                    dialog.show();
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Account Deactivated!!",Toast.LENGTH_LONG).show();
                                finish();
                                Intent i = new Intent(SettingActivity.this, SignIn.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Account Couldn't Be Deactivated!!",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
    }
}

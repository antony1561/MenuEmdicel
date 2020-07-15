package com.example.menuemdicel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Intent o = new Intent(this, Login2.class);
        //////////////////////////////////////////////////////////////
        try{
            TimeUnit.SECONDS.sleep( 3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(o);
        finish();
    }
}

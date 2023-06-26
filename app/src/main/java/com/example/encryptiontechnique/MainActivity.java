package com.example.encryptiontechnique;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ceaserPage(View v){
        Intent intent=new Intent(MainActivity.this,ceaserpage.class);
        startActivity(intent);
    }
    public void playfairPage(View v){
        Intent intent=new Intent(MainActivity.this,playfairpage.class);
        startActivity(intent);
    }
    public void exit(View v){
        finishAffinity ();
    }
}
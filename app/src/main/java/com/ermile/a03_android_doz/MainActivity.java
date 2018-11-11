package com.ermile.a03_android_doz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onclick(View view){
        ImageView img = (ImageView) view;
        img.setImageResource(R.drawable.pone);
    }
}

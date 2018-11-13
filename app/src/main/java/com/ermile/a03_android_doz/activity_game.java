package com.ermile.a03_android_doz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class activity_game extends AppCompatActivity {

    public static final int PLAYER_ONE = 0;
    public static final int PLAYER_TWO = 1;
    public static final int NOT_PLAYED = 2;

    int[] status = {NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
                    NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
                    NOT_PLAYED,NOT_PLAYED,NOT_PLAYED};

    int trun_player= PLAYER_ONE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    public void onclick(View view){
        ImageView img = (ImageView) view;

        int tag = Integer.parseInt ( (String)  view.getTag () );

        if (status[tag] !=NOT_PLAYED){
            return;
        }

        if (trun_player == PLAYER_ONE){
            img.setImageResource ( R.drawable.pone );
            status[tag] = PLAYER_ONE;
            trun_player=PLAYER_TWO;
        }else if (trun_player == PLAYER_TWO){
            img.setImageResource ( R.drawable.ptwo );
            status[tag] = PLAYER_TWO;
            trun_player=PLAYER_ONE;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

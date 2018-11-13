package com.ermile.a03_android_doz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class activity_game extends AppCompatActivity {

    public static final int NO_WINER   = -1;
    public static final int PLAYER_ONE = 0;
    public static final int PLAYER_TWO = 1;
    public static final int NOT_PLAYED = 2;

    int winer = NO_WINER;

    int[] status = {NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
                    NOT_PLAYED,NOT_PLAYED,NOT_PLAYED,
                    NOT_PLAYED,NOT_PLAYED,NOT_PLAYED};

    int trun_player= PLAYER_ONE;

    int[][] winer_position = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

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

        if (winer !=NO_WINER || status[tag] !=NOT_PLAYED){
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
        
        //check winer
        winer = chek_wener ();
        if (winer !=NO_WINER){
            Toast.makeText ( this , "Winner :" + ((winer == PLAYER_ONE) ? "one": "two") , Toast.LENGTH_LONG ).show ( );
        }
        
        
    }


    public int chek_wener(){

        for (int[] position : winer_position){
            if (    status[position[0]] == status[position[1]] &&
                    status[position[1]] == status[position[2]] &&
                    status[position[0]] !=NOT_PLAYED){
                return status[position[0]];
            }

        }
        return NO_WINER;

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

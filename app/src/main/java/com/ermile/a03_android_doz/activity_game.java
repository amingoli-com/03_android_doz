package com.ermile.a03_android_doz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        // menu back
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
        if (winer !=NO_WINER || game_over ()){
            String winner_name = (winer ==PLAYER_ONE)? "ONE" :
                    (winer == PLAYER_TWO) ? "TWO" : "NO Winner";
            Toast.makeText ( this , "Winner :" + winner_name , Toast.LENGTH_LONG ).show ( );
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


    // no winner and full home
    public boolean game_over(){
        for (int i = 0; i <status.length; i++){
            if (status[i] == NOT_PLAYED){
                return false;
            }
        }

        return true;
    }


    // Restart
    public void reset(){
        // trun_player
        trun_player= PLAYER_ONE;
        // winner
        winer = NO_WINER;
        // status
        for (int i = 0; i < status.length; i++){
            status[i] =NOT_PLAYED;
        }
        // find image view > linear fader in > xml
        LinearLayout fader_xml= findViewById ( R.id.fader_xml );
        for (int i = 0 ; i < fader_xml.getChildCount (); i++){
            LinearLayout row = (fader_xml.getChildAt ( i ) instanceof LinearLayout) ?
                    (LinearLayout) fader_xml.getChildAt ( i ) : null;
            if (row == null) return;
            for (int j = 0; j< row.getChildCount (); j++){
                ImageView iv = (row.getChildAt ( j ) instanceof ImageView) ?
                        (ImageView) row.getChildAt ( j ) : null;
                if (iv == null) return;
                iv.setImageResource ( 0 );
            }
        }

    }

    // menu reset
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuItem menu_reset = menu.add ( "RESET" );
            menu_reset.setShowAsAction ( MenuItem.SHOW_AS_ACTION_ALWAYS );
            menu_reset.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ( ) {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    LinearLayout rel = findViewById ( R.id.fader_xml );
                    rel.animate ().rotationBy ( 360f ).setDuration ( 500 );
                    reset ();
                    return false;
                }
            } );
            return super.onCreateOptionsMenu ( menu );
        }



 // menu back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

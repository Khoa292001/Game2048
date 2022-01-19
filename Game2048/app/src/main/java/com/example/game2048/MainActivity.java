package com.example.game2048;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridView gvGame;
    private NumItemAdapter numItemAdapter;
    private View.OnTouchListener touchListener;
    private DataGame score;
    private float x,y;
    private Button newGame;
    private TextView nowScore;
    private Button Backmain;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK){
            try{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Back to Menu!!").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent backIntent = new Intent(MainActivity.this, Menu.class);
                        backIntent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(backIntent);
                        finish();
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }catch (Exception e){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        anhXa();
        khoitao();
        setData();

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        Backmain = (Button) findViewById(R.id.Backmain);

        Backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anhXa(){
        gvGame = (GridView) findViewById(R.id.gvGame);
        newGame = (Button) findViewById(R.id.btnnewGame);
        nowScore = (TextView) findViewById(R.id.tvScores);

    }
    private void khoitao(){
        DataGame.getDataGame().init(MainActivity.this);
        numItemAdapter = new NumItemAdapter(MainActivity.this,0,DataGame.getDataGame().getArrNum());

        touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if ( Math.abs(event.getX() - x) > Math.abs(event.getY() - y)){
                            if (event.getX() < x){
                                DataGame.getDataGame().slideLeft();
                                numItemAdapter.notifyDataSetChanged();
                            }else{
                                DataGame.getDataGame().slideRight();
                                numItemAdapter.notifyDataSetChanged();
                            }
                        }else{
                            if (event.getY() < y){
                                DataGame.getDataGame().slideDown();
                                numItemAdapter.notifyDataSetChanged();
                            }else{
                                DataGame.getDataGame().slideUp();
                                numItemAdapter.notifyDataSetChanged();
                            }
                        }
                }
                return true;
            }
        };
    }

    private void setData(){
        gvGame.setAdapter(numItemAdapter);
        gvGame.setOnTouchListener(touchListener);
    }

    public void newGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want NewGame!").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                anhXa();
                khoitao();
                setData();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

}
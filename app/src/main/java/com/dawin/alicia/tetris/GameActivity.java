package com.dawin.alicia.tetris;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends Activity {
    public ArrayList<Bitmap> mBitmap;
    public GridView gridview;
    public int[][] matrixMain;
    public Piece current;
    public GridAdapter gridApdater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.initBitmap();
        this.initGrid();

        this.matrixMain = new int[16][10];
        this.current = randomPiece();
        this.addPieceToMatrix();
        this.matrixToBitmap();
        gridApdater.notifyDataSetChanged();

        final Handler handler = new Handler();

        final Runnable run = new Runnable() {
            public void run() {
                loop();
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(run, 1000);

    }

    public void initBitmap(){
        this.mBitmap = new ArrayList<>();

        for(int i =0; i<160;i++){

            int c = Color.rgb(100,100,100);
            Bitmap b = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            b.eraseColor(c);
            this.mBitmap.add(b);
        }
    }

    public void initGrid(){
        this.gridApdater = new GridAdapter(this, this.mBitmap);
        this.gridview = findViewById(R.id.myGrid);
        this.gridview.setAdapter(gridApdater);
    }

    public Piece randomPiece(){
      String tab[] = {"I", "J", "L", "O", "S", "T", "Z"};
      Random r = new Random();
      String letter = tab[r.nextInt(6)];
      Log.i("piece", letter);
      Piece p;
      switch (letter) {
          case "I" :
              p = new I();
              break;
          case "J":
              p=new J();
              break;
          case "L":
              p= new L();
              break;
          case "O":
              p= new O();
              break;
          case "S":
              p = new S();
              break;
          case "T":
              p=new T();
              break;
          case "Z":
              p=new Z();
              break;
          default:
              p= new I();
              break;
      }

      return p;
    }

    public void addPieceToMatrix(){
        int piece_x;
        int piece_y=0;
        int max_height = this.current.pos_i + this.current.height;
        int max_width = this.current.pos_j + this.current.width;
        for(int i = this.current.pos_i; i<max_height; i++){
            piece_x =0;
            for(int j = this.current.pos_j; j<max_width; j++){
                if(this.current.matrix[piece_y][piece_x] == 1){
                    this.matrixMain[i][j] = 1;
                }
                piece_x++;
            }
            piece_y++;
        }
    }

    public void matrixToBitmap (){
        for(int i = 0; i<16; i++){
            for(int j=0; j<10; j++){
                if(this.matrixMain[i][j] == 1){
                    Log.i("bitmappos", ""+i+","+j+","+(i*10+j));
                    Bitmap b = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
                    b.eraseColor(this.current.color);
                    mBitmap.set((i*10+j),b);
                }
            }
        }
    }

    public void erasePiece(){
        int piece_x;
        int piece_y=0;
        int max_height = this.current.pos_i + this.current.height;
        int max_width = this.current.pos_j + this.current.width;
        for(int i = this.current.pos_i; i<max_height; i++){
            piece_x =0;
            for(int j = this.current.pos_j; j<max_width; j++){
                if(this.current.matrix[piece_y][piece_x] == 1){
                    Log.i("matrixpos", ""+i+","+j);
                    this.matrixMain[i][j] = 0;
                    int c = Color.rgb(100,100,100);
                    Bitmap b = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
                    b.eraseColor(c);
                    mBitmap.set((i*10+j),b);
                }
                piece_x++;
            }
            piece_y++;
        }
    }

    public void loop() {
        if(this.current.canMove(this.matrixMain, "down")){
            this.erasePiece();
            this.current.down();
            this.addPieceToMatrix();
            this.matrixToBitmap();
        }
        else {
            this.current = this.randomPiece();
            this.addPieceToMatrix();
            this.matrixToBitmap();
        }
        gridApdater.notifyDataSetChanged();

    }
}

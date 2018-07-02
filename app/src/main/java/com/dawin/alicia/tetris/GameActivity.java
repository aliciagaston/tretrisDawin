package com.dawin.alicia.tetris;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {
    public ArrayList<Bitmap> mBitmap;
    public GridView gridview;
    public int[][] matrixMain;
    public Piece current;
    public GridAdapter gridApdater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button onright = findViewById(R.id.right_button);
        onright.setOnClickListener(this);
        Button onleft = findViewById(R.id.left_button);
        onleft.setOnClickListener(this);
        Button ondown = findViewById(R.id.down_button);
        ondown.setOnClickListener(this);
        Button onrotate = findViewById(R.id.rotate_button);
        onrotate.setOnClickListener(this);

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
            this.eraseLine();
        }
        gridApdater.notifyDataSetChanged();

    }

    private void eraseLine() {
        for(int l=0; l<16; l++){
            boolean completeLine = true;
            for(int c=0; c<10; c++){
                if(this.matrixMain[l][c] == 0){
                    completeLine = false;
                }

            }
            if(completeLine){
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                text = "line completed !";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                this.eraseLineOnView(l);
            }
        }
    }

    private void eraseLineOnView(int l) {
        for(int col=0; col<10; col++){
            this.matrixMain[l][col] = 0;
            int c = Color.rgb(100,0,0);
            Bitmap b = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
            b.eraseColor(c);
            mBitmap.set((l*10+col),b);
        }
        for(int line = l-1; line<=0; line--){
            for(int col=0; col<10; col++){

                Log.i("erase", line + " " + col);
                Bitmap b = this.mBitmap.get(line*10+col);
                this.mBitmap.set((line+1)*10+col, b);

                int value = this.matrixMain[line][col];
                this.matrixMain[line+1][col] = value;
                gridApdater.notifyDataSetChanged();
            }
            gridApdater.notifyDataSetChanged();
        }
        gridApdater.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        Toast toast;

        switch(v.getId()){
            case R.id.right_button:
                text = "right";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                if(this.current.canMove(this.matrixMain, "right")) {
                    this.erasePiece();
                    this.current.right();
                    this.addPieceToMatrix();
                    this.matrixToBitmap();
                }
                break;
            case R.id.left_button:
                text = "left !";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                if(this.current.canMove(this.matrixMain, "left")) {
                    this.erasePiece();
                    this.current.left();
                    this.addPieceToMatrix();
                    this.matrixToBitmap();
                }
                break;
            case R.id.down_button:
                text = "down !";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                if(this.current.canMove(this.matrixMain, "down")) {
                    this.erasePiece();
                    this.current.down();
                    this.addPieceToMatrix();
                    this.matrixToBitmap();
                }
                break;
            case R.id.rotate_button:
                text = "rotate !";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                if(this.current.canMove(this.matrixMain, "rotate")) {
                    this.erasePiece();
                    this.current.rotate();
                    this.addPieceToMatrix();
                    this.matrixToBitmap();
                }
                break;
        }

    }
}

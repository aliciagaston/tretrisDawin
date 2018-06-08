package com.dawin.alicia.tetris;

import android.graphics.Color;

public class T extends Piece implements Movement, PossibleMovement{
    public T() {
        super(2, 3, new int[][]{
                {0,1,0},
                {1,1,1}
        }, 0,0, Color.BLACK);
    }

    @Override
    public void rotate() {
        if(this.height == 2){
            this.height = 3;
            this.width = 2;
            this.matrix = new int[][]{
                    {1,0},
                    {1,1},
                    {1,0}
            };
        }
        else {
            this.height = 2;
            this.width = 3;
            this.matrix = new int[][]{
                    {0,1,0},
                    {1,1,1}
            };
        }
    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void down() {
        this.pos_i = this.pos_i+1;
    }
}

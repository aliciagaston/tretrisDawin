package com.dawin.alicia.tetris;

import android.graphics.Color;

import com.dawin.alicia.tetris.Piece;

public class I extends Piece implements Movement, PossibleMovement{

    public I() {
        super(1, 4,
                new int[][]{
                {1,1,1,1}
        }, 0, 0, Color.BLACK);
    }

    @Override
    public void rotate() {
        if(this.height == 4){
            this.height = 1;
            this.width=4;
            this.matrix = new int[][] {
                    {1,1,1,1}
            };
        }
        else {
            this.height = 4;
            this.width = 1;
            this.matrix = new int[][] {
                    {1},
                    {1},
                    {1},
                    {1}
            };
        }
    }

    @Override
    public void left() {
        this.pos_j = this.pos_j-1;

    }

    @Override
    public void right() {
        this.pos_j = this.pos_j+1;

    }

    @Override
    public void down() {
        this.pos_i = this.pos_i+1;
    }
}

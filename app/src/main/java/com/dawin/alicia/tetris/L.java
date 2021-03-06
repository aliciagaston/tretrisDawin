package com.dawin.alicia.tetris;

import android.graphics.Color;

public class L extends Piece implements Movement, PossibleMovement{
    public L() {
        super(2, 3, new int[][]{
                {0,0,1},
                {1,1,1}
        }, 0, 0, Color.BLACK);
    }

    @Override
    public void rotate() {
        if(this.height == 2){
            this.height = 3;
            this.width = 2;
            if(this.matrix[0][2] == 1){
                this.matrix = new int[][]{
                        {1,0},
                        {1,0},
                        {1,1}
                };
            }
            else {
                this.matrix = new int[][]{
                        {1,1},
                        {0,1},
                        {0,1}
                };
            }
        }
        else {
            this.height = 2;
            this.width = 3;
            if(this.matrix[2][1] == 0){
                this.matrix = new int[][]{
                        {1,1,1},
                        {1,0,0}
                };
            }
            else {
                this.matrix = new int[][]{
                        {0,0,1},
                        {1,1,1}
                };
            }
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

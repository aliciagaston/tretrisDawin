package com.dawin.alicia.tetris;

import android.graphics.Color;

public class J extends Piece implements Movement, PossibleMovement{
    public J() {
        super(2, 3, new int[][]{
                {1,0,0},
                {1,1,1}
        }, 0, 0, Color.BLACK);
    }

    @Override
    public void rotate() {
        if(this.height == 2){
            this.width = 2;
            this.height = 3;
            if(this.matrix[1][2] == 1){
                this.matrix = new int[][]{
                        {1,1},
                        {1,0},
                        {1,0}
                };
            }
            else {
                this.matrix = new int[][]{
                        {0,1},
                        {0,1},
                        {1,1}
                };
            }
        }
        else {
            this.width = 3;
            this.height = 2;
            if(this.matrix[0][1] == 0){
                this.matrix = new int[][] {
                        {1,1,1},
                        {0,0,1}
                };
            }
            else {
                this.matrix = new int[][]{
                        {1,0,0},
                        {1,1,1}
                };
            }
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

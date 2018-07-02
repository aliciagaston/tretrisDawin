package com.dawin.alicia.tetris;

import android.graphics.Color;

public class O extends Piece implements Movement, PossibleMovement{
    public O() {
        super(2, 2, new int[][]{
                {1,1},
                {1,1}
        }, 0, 0, Color.BLACK);
    }

    @Override
    public void rotate() {

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

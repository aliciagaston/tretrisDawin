package com.dawin.alicia.tetris;

import android.content.Context;
import android.widget.Toast;

public abstract class Piece implements Movement, PossibleMovement {
    protected int height;
    protected int width;
    protected int[][] matrix;
    protected int pos_i;
    protected int pos_j;
    protected int color;

    public Piece(int height, int width, int[][] matrix, int pos_i, int pos_j, int color) {
        this.height = height;
        this.width = width;
        this.matrix = matrix;
        this.pos_i = pos_i;
        this.pos_j = pos_j;
        this.color = color;
    }


    @Override
    public boolean canMove(int[][] matrixMain, String direction) {
        switch(direction){
            case "down":
                int pos_i_after = this.pos_i+1;
                int matrixHeight = 16;
                if((pos_i_after + this.height-1) >= matrixHeight){
                    return false;
                }
                else {
                    int l = this.pos_i + (this.height -1);
                    for(int c = this.pos_j; c<(this.pos_j + this.width); c++){

                        if(matrixMain[l][c] == 1){
                            if(matrixMain[l+1][c] == 1){
                                return false;
                            }
                        }
                    }
                    return true;
                }
            case "right":
                int pos_j_after = this.pos_j+1;
                int matrixWidth = 10;
                if((pos_j_after + this.width-1)>=matrixWidth){
                    return false;
                }
                else {
                    int c = this.pos_j + (this.width -1);
                    for (int l = this.pos_i; l < (this.pos_i + this.height); l++) {
                        if (matrixMain[l][c] == 1) {
                            if (matrixMain[l][c + 1] == 1) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            case "left" :
                pos_j_after = this.pos_j-1;
                if(pos_j_after<0){
                    return false;
                }
                else {
                    int c = this.pos_j;
                    for(int l = this.pos_i; l< (this.pos_i + this.height); l++){
                        if(matrixMain[l][c]==1){
                            if(matrixMain[l][c-1] == 1) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            case "rotate":


        }
        return false;
    }
}

package com.example.pingpong.entities;

import android.graphics.Paint;
import android.graphics.RectF;

public class Player {

    public int paddleWidth;
    public int paddleHeight;
    public  Paint paint;
    public int score;
    public RectF bounds;
    public int collision;

    public  Player(int paddleWidth, int paddleHeight, Paint paint) {
        this.paddleWidth = paddleWidth;
        this.paddleHeight = paddleHeight;
        this.paint = paint;
        this.score = 0;
        this.bounds = new RectF(0, 0, paddleWidth, paddleHeight);
        this.collision = 0;
    }


}

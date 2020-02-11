package com.example.pingpong.entities;

import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Ball {
    public float cx;
    public float cy;
    public float dx;
    public float dy;
    public int radius;
    public Paint paint;

    public Ball(int radius, Paint paint) {
        this.radius = radius;
        this.paint = paint;
    }
}

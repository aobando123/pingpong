package com.example.pingpong.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.core.view.MotionEventCompat;

import com.example.pingpong.MainActivity;
import com.example.pingpong.WinnerScreen;
import com.example.pingpong.entities.Ball;
import com.example.pingpong.entities.Player;
import com.example.pingpong.threads.PongThread;

import org.w3c.dom.Text;

import java.io.IOException;

public class PongView extends SurfaceView implements SurfaceHolder.Callback {

    private PongThread mGameThread;

    private TextView mStatusView;

    private TextView mScoreView;

    private boolean isTwoPlayer;
    private int ballSpeed;
    private float computerProb;

    private TextView mTimer;

    private Context mContext;

    public PongView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        mGameThread = new PongThread(holder, context,
                new Handler() {
                    @Override
                    public void handleMessage(Message m) {
                        mStatusView.setVisibility(m.getData().getInt("vis"));
                        mStatusView.setText(m.getData().getString("text"));
                    }
                },
                new Handler() {
                    @Override
                    public void handleMessage(Message m) {
                        String text = m.getData().getString("text");
                        mScoreView.setText(text);

                    }
                },
                new Handler() {
                    @Override
                    public void handleMessage(Message m) {
                        goToWinScreen("Score");
                    }
                },
                attributeSet
        );

        setFocusable(true);
    }

    public void setStatusView(TextView textView) {
        mStatusView = textView;
    }

    public void setScoreView(TextView textView) {
        mScoreView = textView;
    }

    public void setTimeView(TextView textView) {
        mTimer = textView;
    }

    public  void setReplayValues (int ballSpeed, float computerProb, boolean isTwoPlayer) {
        this.ballSpeed = ballSpeed;
        this.computerProb = computerProb;
        this.isTwoPlayer = isTwoPlayer;
    }

    long MillisecondTime, TimeBuff, UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds;

    public void startTimer() {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

                MillisecondTime = millisUntilFinished;

                UpdateTime = TimeBuff + MillisecondTime;

                Seconds = (int) (UpdateTime / 1000);

                Minutes = Seconds / 60;

                Seconds = Seconds % 60;

                MilliSeconds = (int) (UpdateTime % 1000);

                mTimer.setText("0" + Minutes + ":"
                        + String.format("%02d", Seconds));
            }

            public void onFinish() {
                goToWinScreen("Time");

            }
        }.start();


    }

    private  void goToWinScreen (String mode) {
        Intent nextPage = new Intent(mContext, WinnerScreen.class);
        nextPage.putExtra("ballSpeed", ballSpeed);
        nextPage.putExtra("computerProbability", computerProb);
        nextPage.putExtra("isTwoPlayer", isTwoPlayer);
        nextPage.putExtra("mode", mode);
        nextPage.putExtra("winner", mGameThread.getWinner());
        mContext.startActivity(nextPage);
    }
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) {
            mGameThread.pause();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mGameThread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mGameThread.setRunning(true);
        mGameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mGameThread.setRunning(false);
        while (retry) {
            try {
                mGameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // don't care
            }
        }
    }

    private boolean moving;
    private float   mLastTouchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for(int i = 0; i < event.getPointerCount(); i++){

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    if (mGameThread.isBetweenRounds()) {
                        // resume game
                        mGameThread.setState(PongThread.STATE_RUNNING);
                    } else {
                        if (mGameThread.isTouchOnHumanPaddle(event)) {
                            moving = true;
                            mLastTouchY = event.getY(i);
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (moving) {
                        float y = event.getY(i);
                        float dy = y - mLastTouchY;
                        mLastTouchY = y;
                        Player mPlayer =  mGameThread.getPlayerToMove(event);
                        if (mGameThread.isTouchOnHumanPaddle(event)) {
                            mGameThread.moveHumanPaddle(dy, mPlayer);
                        }

                    }
                    break;
                case MotionEvent.ACTION_UP:
                    moving = false;
                    break;
            }

        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mGameThread.isBetweenRounds()) {
                    // resume game
                    mGameThread.setState(PongThread.STATE_RUNNING);
                } else {
                    if (mGameThread.isTouchOnHumanPaddle(event)) {
                        moving = true;
                        mLastTouchY = event.getY();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    float y = event.getY();
                    float dy = y - mLastTouchY;
                    mLastTouchY = y;
                    Player mPlayer =  mGameThread.getPlayerToMove(event);
                    if (mGameThread.isTouchOnHumanPaddle(event)) {
                        mGameThread.moveHumanPaddle(dy, mPlayer);
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }

    public PongThread getGameThread() {
        return mGameThread;
    }

}

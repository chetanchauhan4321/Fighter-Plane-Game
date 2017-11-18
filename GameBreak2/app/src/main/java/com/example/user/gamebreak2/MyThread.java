package com.example.user.gamebreak2;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by Rahul on 11/9/2017.
 */
public class MyThread extends Thread{
    boolean flag=false;
    GameView gameView;

    public MyThread(GameView gv)
    {
        gameView=gv;

    }

    void isrunning(boolean flag)
    {
        this.flag=flag;
    }
    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (flag){
            Canvas c=null;
            try {
                Thread.sleep(200);
                synchronized (gameView.getHolder()){
                    c = gameView.getHolder().lockCanvas();
                    gameView.onDraw(c);
                }
            } catch (Exception e) {
            }
            finally {
                gameView.getHolder().unlockCanvasAndPost(c);
            }
        }
    }
}


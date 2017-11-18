package com.example.user.gamebreak2;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Rahul on 11/9/2017.
 */
public class GameView extends SurfaceView {
    SurfaceHolder sh;

    //Paint p;
    Player ply;
    MyThread mt;
    Boom boom;
    Rect r,r1,r2,r3;
    Enemy en1,en2,en3;
    int btmy;



    @SuppressLint("WrongCall")
    public GameView(Context ct, int x, int y) {
        super(ct);
        ply=new Player(ct,x,y);
        boom =new Boom(ct);
        sh=getHolder();
        btmy=y;
        en1=new Enemy(ct,x,y);
        en2=new Enemy(ct,x,y);
        en3=new Enemy(ct,x,y);

        mt = new MyThread(this);

        sh.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                mt.isrunning(true);
                mt.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
@Override
    protected void onDraw(Canvas canvas){
        int score=ply.getScore();

        Paint ps=new Paint();
         ps.setColor(Color.BLUE);
         ps.setTextSize(50f);


        int btm=ply.getY();
        canvas.drawColor(Color.BLACK);

        r=new Rect(ply.getX(),ply.getY(),ply.getX()+ply.getBp().getWidth(),ply.getY()+ply.getBp().getHeight());
        canvas.drawText("Score:"+score,10,50,ps);
        canvas.drawBitmap(ply.getBp(),ply.getX(),ply.getY(),null);

        canvas.drawBitmap(en1.getBitmap(),en1.getX(),en1.getY(),null);
        canvas.drawBitmap(en2.getBitmap(),en2.getX(),en2.getY(),null);
        canvas.drawBitmap(en3.getBitmap(),en3.getX(),en3.getY(),null);

    r1=new Rect(en1.getX(),en1.getY(),en1.getX()+en1.getBitmap().getWidth(),en1.getY()+en1.getBitmap().getHeight());
    r2=new Rect(en2.getX(),en2.getY(),en2.getX()+en2.getBitmap().getWidth(),en2.getY()+en2.getBitmap().getHeight());
    r3=new Rect(en3.getX(),en3.getY(),en3.getX()+en3.getBitmap().getWidth(),en3.getY()+en3.getBitmap().getHeight());

    if(Rect.intersects(r,r1)|| Rect.intersects(r,r2)||Rect.intersects(r,r3))
    {
        boom.setX(ply.getX()+ply.getBp().getWidth()-200);
        boom.setY(ply.getY()-20);

        canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),null);
        mt.isrunning(false);

        Paint p=new Paint();
        Paint p1=new Paint();
        p.setColor(Color.RED);
        p1.setColor(Color.BLUE);
        p.setTextSize(100f);
        p1.setTextSize(50f);

        canvas.drawText("Game Over",350,350,p);
        canvas.drawText("Score:"+score,520,400,p1);
    }
    else if(btm>=btmy-200)
    {

        boom.setX(ply.getX());
        boom.setY(ply.getY()+20);
        canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),null);
        mt.isrunning(false);

        Paint p=new Paint();
        Paint p1=new Paint();
        p.setColor(Color.RED);
        p1.setColor(Color.BLUE);
        p.setTextSize(100f);
        p1.setTextSize(50f);

        canvas.drawText("Game Over",350,350,p);
        canvas.drawText("Score"+score,520,400,p1);
    }
    en1.change();
    en2.change();
    en3.change();


        ply.change();

    }
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch(motionEvent.getAction() & motionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                ply.stopboosting();

                break;
            case MotionEvent.ACTION_DOWN:
                ply.startboosting();
               // ply.setY(ply.getY()-10);
                break;

        }
        return true;
    }

}

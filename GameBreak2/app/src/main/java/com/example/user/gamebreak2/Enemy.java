package com.example.user.gamebreak2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by user on 10-11-2017.
 */

public class Enemy {
    private Bitmap bitmap;
    private int x,x1;
    private int y;
    //int [] arrx=new int[3];
    //int [] arry=new int[3];

    public Enemy(Context context, int screenx, int screeny){
     bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        x=screenx-bitmap.getWidth();
        x1=x;

        Random r=new Random();
        y=1+r.nextInt(screeny);

    }

    public void change()
    {
        Random r=new Random();
        int k=1+r.nextInt(30);
        x=x-k;

        if(x<=0)
        {
            x=x1;
        }
    }

    public Bitmap getBitmap(){ return bitmap;}
    public int getX(){return x;}
    public int getY(){return y;}

}

package com.example.user.gamebreak2;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Player {
    Bitmap bp;
    int x1,x,y,y1,score=0,btmy;
    boolean boosting;

    int gravity;
    int maxy;
    int miny=0;
    // int minspeed=1;
    // int maxspeed=20;
    Player(Context ct,int screenx,int screeny){
        bp= BitmapFactory.decodeResource(ct.getResources(),R.drawable.player);
        x=0;
        x1=screenx-bp.getWidth(); // -bp.getWidth
        y=screeny/2-bp.getHeight()/2;
        y1=y;
        btmy=screeny;
        boosting=false;
       // gravity=10;
        gravity=5;
    }
    void stopboosting(){boosting=false;}
    void startboosting(){
        boosting=true;
    }
    void change(){
        y=y+gravity;
        if(boosting ){

            x=x+25;
            y=y-30; //
        }

        else{
            x=x+15;
        }

        if(x>=x1){
            x=0;
            //y=y1;
            score=score+5;
        }


    }

    public Bitmap getBp() {
        return bp;
    }
    public int getScore(){return score;}

    public void setBp(Bitmap bp) {
        this.bp = bp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
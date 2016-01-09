package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Explode {
	private Bitmap explode1;
	private Bitmap explode2;
	private Bitmap explode3;
	private Bitmap explode4;
	private Bitmap explode5;
	private Bitmap explode6;
	private Paint paint;
	private int x,y;
	private int count;
	private int judgepicture;
	private boolean judgefinish;
	
	public Explode(int _x,int _y,Bitmap _explode1,Bitmap _explode2,Bitmap _explode3,Bitmap _explode4, Bitmap _explode5,Bitmap _explode6){
		explode1=_explode1;
		explode2=_explode2;
		explode3=_explode3;
		explode4=_explode4;
		explode5=_explode5;
		explode6=_explode6;
		x=_x;
		y=_y;
		paint= new Paint();
		count=0;
		judgepicture=1;
		judgefinish=true;
	}
	public void draw(Canvas canvas){
		switch(judgepicture){
		case 1:
			canvas.drawBitmap(explode1, x, y, paint);break;
		case 2:
			canvas.drawBitmap(explode2, x, y, paint);break;
		case 3:
			canvas.drawBitmap(explode3, x, y, paint);break;
		case 4:
			canvas.drawBitmap(explode4, x, y, paint);break;
		case 5:
			canvas.drawBitmap(explode5, x, y, paint);break;
		case 6:
			canvas.drawBitmap(explode6, x, y, paint);break;
		}
	}
	
	public void nextStep(){
		judge();
		count++;
	}
	
	public void judge(){
		if(count>=2){
			count=0;
			judgepicture++;
		}
		if(judgepicture>6){
			judgefinish=false;
		}
	}
	
	public boolean getjudgefinish(){
		return judgefinish;
	}
	
}

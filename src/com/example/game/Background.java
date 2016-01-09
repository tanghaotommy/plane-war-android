package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Background {
	private float x;
	private float y;
	private Bitmap bitmap;
	private int width;
	private int height;
	private Paint paint;
	
	public Background(float _x, float _y,Bitmap _bitmap,int _width, int _height){
		x=_x;
		y=_y;
		bitmap=_bitmap;
		width=_width;
		height=_height;
		paint=new Paint();
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, paint);
	}
	
	public void move(){
		y+=1;
	}
	
	public void setx(float _x){
		x=_x;
	}
	
	public void sety(float _y){
		y=_y;
	}
	
	public float getx(){
		return x;
	}
	
	public float gety(){
		return y;
	}

}

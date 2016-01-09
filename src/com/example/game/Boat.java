package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Boat {		
	    float x,y;
		boolean judge;
		Canvas canvas;
		Bitmap boatImg;
		int width,height;
		Paint paint;
		
		public Boat(float _x, float _y, Bitmap _boat,int _width,int _height)
		{
		
			judge=true;
			boatImg=_boat;
			width=_width;
			height=_height;
			paint = new Paint();	
			x=_x-width;
			y=_y-height;
		}
		public void setx(float _x) 
		{
			x=_x-width/2;
		}
		public void sety(float _y)
		{
			y=_y-height/2;
		}
		public float getx()
		{
			return x;
		}
		public float gety()
		{
			return y;
		}
		public void setjudge(boolean i)
		{
			judge=i;
		}	
		public boolean getjudge()
		{
			return judge;
		}
		void draw(Canvas canvas)
		{
			canvas.drawBitmap(boatImg, x, y, paint);
		}
		int getwidth(){
			return width;
		}
		int getheight(){
			return height;
		}
	    void explode()
		{
		}


}

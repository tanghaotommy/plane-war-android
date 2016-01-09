package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy {
	public	Enemy(int _x, int _y,Bitmap _bitmap, int _width, int _height,int _life,int _speed)
		{
			y=_y;
			x=_x;
			judge=true;
			width=_width;
			height=_height;
			life=_life;
			bitmap=_bitmap;
			paint = new Paint();
			speed=_speed;
		}
	public void move()
		{
		y+=speed;
		}
	public	void stay()
		{
		}
	public	void explode()
		{
		}
	public	void setspeed(int i)
		{
			speed=i;
		}
	public void setjudge(boolean i)
		{
			judge=i;
		}
	public	boolean getjudge()
		{
			return judge;
		}
	public   int getx()
		{
			return x;
		}
	public int gety()
		{
			return y;
		}
	public	int getwidth()
		{
			return width;
		}
	public	int getheight()
		{
			return height;
		}
	public	int getlife()
		{
			return life;
		}
	public	void minuslife()
		{
			life=life-1;
			if(life<=0){
				judge=false;
			}
		}
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, paint);
	}
	
	Bitmap bitmap;
	boolean judge;
	int y,x;
	int speed;
	int width,height;
	int life;
	Paint paint;
}

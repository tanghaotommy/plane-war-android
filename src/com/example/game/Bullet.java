package com.example.game;

public class Bullet {	
		float x,y;
		boolean judge;
		
		public Bullet(float _x,float _y)
		{
			judge=true;
			x=_x;
			y=_y;
		}
		public void move()
		{
			y-=8;
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
}

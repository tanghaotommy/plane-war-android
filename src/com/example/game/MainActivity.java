package com.example.game;

import java.util.Random;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View; 
import android.widget.TextView;

public class MainActivity extends Activity {
	private int id=0;
	private float mX=0,mY=0;
	Boat MyBoat;
	Bitmap BoatImg;
	Bitmap Enemy1Img;
	Bitmap Enemy2Img;
	Bitmap Enemy3Img;
	Bitmap OnButton;
	Bitmap PauseButton;
	Bitmap Background1Img;
	Bitmap Background2Img;
	Bitmap Explode1Img;
	Bitmap Explode2Img;
	Bitmap Explode3Img;
	Bitmap Explode4Img;
	Bitmap Explode5Img;
	Bitmap Explode6Img;
	int highestScore=0;
	int currentScore=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=1;           
        setContentView(R.layout.activity_main);      
    }
    
    	
    class MyView extends SurfaceView implements SurfaceHolder.Callback{
    	int screenWidth,screenHeight;
    	int Enemy1Width,Enemy1Height;
    	int Enemy2Width,Enemy2Height;
    	int Enemy3Width,Enemy3Height;
    	int BoatWidth,BoatHeight;
    	float xRatio,yRatio;
    	int bulletappearrate=5;
    	int enemy1appearrate;
    	int enemy2appearrate;
    	int enemy3appearrate;
    	int enemy1speed;
    	int enemy2speed;
    	int enemy3speed;
    	int enemy1life=1;
    	int enemy2life=5;
    	int enemy3life=10;
		int countbullet;
		int countenemy1;
		int countenemy2;
		int countenemy3;
		int enemy1Score=100;
		int enemy2Score=200;
		int enemy3Score=500;
		int Level;
		boolean OnorPause;
		Background background1;
		Background background2;
    	//继承类
    	public class Enemy1 extends Enemy{
    		public Enemy1(int x,int y,int speed){
    			super(x,y,Enemy1Img,Enemy1Width,Enemy1Height,enemy1life,speed);
    		}
    	}
    	public class Enemy2 extends Enemy{
    		public Enemy2(int x,int y,int speed){
    			super(x,y,Enemy2Img,Enemy2Width,Enemy2Height,enemy2life,speed);
    		}
    	}
    	public class Enemy3 extends Enemy{
    		public Enemy3(int x,int y,int speed){
    			super(x,y,Enemy3Img,Enemy3Width,Enemy3Height,enemy3life,speed);
    		}
    	}
    	Vector <Bullet> bullet = new Vector<Bullet>();  
    	Vector <Enemy1> enemy1 = new Vector<Enemy1>();
    	Vector <Enemy2> enemy2 = new Vector<Enemy2>();
    	Vector <Enemy3> enemy3 = new Vector<Enemy3>();
    	Vector <Explode> explode = new Vector<Explode>();
    	boolean IsRunning=false;
    	private SurfaceHolder holder;
    	private Paint paint;


    	public MyView(Context context){
    		super(context);
    		holder = this.getHolder();
    		holder.addCallback(this);
    		paint = new Paint();
    	}


		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			// TODO Auto-generated method stub	
			//设定参数
			Level=1;
			currentScore=0;
			OnorPause=true;
			countbullet=bulletappearrate;
			countenemy1=enemy1appearrate;
			countenemy2=0;
			countenemy3=0;
			screenWidth=this.getWidth();
			screenHeight=this.getHeight();
			xRatio=(float) (screenWidth/540.0);
			yRatio=(float) (screenHeight/788.0);
			//图片加载
			Bitmap _BoatImg=BitmapFactory.decodeResource(getResources(),R.drawable.boat);
			Bitmap _Enemy1Img=BitmapFactory.decodeResource(getResources(),R.drawable.enemy1);
			Bitmap _Enemy2Img=BitmapFactory.decodeResource(getResources(),R.drawable.enemy2);
			Bitmap _Enemy3Img=BitmapFactory.decodeResource(getResources(),R.drawable.enemy3);
			Bitmap _OnButton=BitmapFactory.decodeResource(getResources(), R.drawable.play2);
			Bitmap _PauseButton=BitmapFactory.decodeResource(getResources(), R.drawable.play1);
			Bitmap _Background1Img=BitmapFactory.decodeResource(getResources(), R.drawable.background1);
			Bitmap _Background2Img=BitmapFactory.decodeResource(getResources(), R.drawable.background2);
			Explode1Img=BitmapFactory.decodeResource(getResources(), R.drawable.bomb_enemy_0);
			Explode2Img=BitmapFactory.decodeResource(getResources(), R.drawable.bomb_enemy_1);
			Explode3Img=BitmapFactory.decodeResource(getResources(), R.drawable.bomb_enemy_2);
			Explode4Img=BitmapFactory.decodeResource(getResources(), R.drawable.bomb_enemy_3);
			Explode5Img=BitmapFactory.decodeResource(getResources(), R.drawable.bomb_enemy_4);
			Explode6Img=BitmapFactory.decodeResource(getResources(), R.drawable.bomb_enemy_5);
			//按钮图片重新设置
			Matrix Buttonmatrix = new Matrix();
			float OnButtonxRatio = (float) (50.0/_OnButton.getWidth());
			float OnButtonyRatio = (float) (50.0/_OnButton.getHeight());
			float PauseButtonxRatio = (float) (50.0/_PauseButton.getWidth());
			float PauseButtonyRatio = (float) (50.0/_PauseButton.getHeight());
			Buttonmatrix.postScale(OnButtonxRatio, OnButtonyRatio);
			OnButton=Bitmap.createBitmap(_OnButton, 0, 0, _OnButton.getWidth(), _OnButton.getHeight(),Buttonmatrix, true);
			Buttonmatrix.postScale(PauseButtonxRatio, PauseButtonyRatio);
			PauseButton=Bitmap.createBitmap(_PauseButton, 0, 0, _PauseButton.getWidth(), _PauseButton.getHeight(),Buttonmatrix, true);
			//背景图片重新设置
			Matrix Backgroundmatrix = new Matrix();
			float BackgroundxRatio = (float)screenWidth/_Background1Img.getWidth();
			float BackgroundyRatio = (float)screenHeight/_Background1Img.getHeight();
			Backgroundmatrix.postScale(BackgroundxRatio, BackgroundyRatio);
			Background1Img=Bitmap.createBitmap(_Background1Img, 0, 0, _Background1Img.getWidth(),_Background1Img.getHeight(),Backgroundmatrix,true);
			Background2Img=Bitmap.createBitmap(_Background2Img, 0, 0, _Background2Img.getWidth(),_Background2Img.getHeight(),Backgroundmatrix,true);
			background1 = new Background(0,0,Background1Img,screenWidth,screenHeight);
			background2 = new Background(0,-screenHeight,Background2Img,screenWidth,screenWidth);
			//敌军图片重新设置
			Matrix matrix = new Matrix();
			matrix.postScale(xRatio*0.8f, yRatio*0.8f);// 缩放原图
			Enemy1Width=(int) (_Enemy1Img.getWidth()*xRatio*0.8);Enemy1Height=(int) (_Enemy1Img.getHeight()*yRatio*0.8);
			Enemy2Width=(int) (_Enemy2Img.getWidth()*xRatio*0.8);Enemy2Height=(int) (_Enemy2Img.getHeight()*yRatio*0.8);
			Enemy3Width=(int) (_Enemy3Img.getWidth()*xRatio*0.8);Enemy3Height=(int) (_Enemy3Img.getHeight()*yRatio*0.8);
     		BoatWidth=(int) (_BoatImg.getWidth()*xRatio*0.8);BoatHeight=(int) (_BoatImg.getHeight()*yRatio*0.8);
			Enemy1Img=Bitmap.createBitmap(_Enemy1Img, 0, 0, _Enemy1Img.getWidth(), _Enemy1Img.getHeight(),matrix, true);
			Enemy2Img=Bitmap.createBitmap(_Enemy2Img, 0, 0, _Enemy2Img.getWidth(), _Enemy2Img.getHeight(),matrix, true);
			Enemy3Img=Bitmap.createBitmap(_Enemy3Img, 0, 0, _Enemy3Img.getWidth(), _Enemy3Img.getHeight(),matrix, true);
			BoatImg=Bitmap.createBitmap(_BoatImg, 0, 0, _BoatImg.getWidth(),_BoatImg.getHeight(),matrix,true);
			MyBoat = new Boat(250,600,BoatImg,BoatWidth,BoatHeight);
			IsRunning=true;
	        new Thread(new MyThread()).start();
		}
		
		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			IsRunning=false;
			bullet.removeAllElements();
			enemy1.removeAllElements();
			enemy2.removeAllElements();
			enemy3.removeAllElements();
			explode.removeAllElements();
			// TODO Auto-generated method stub
		}
		
		public boolean onTouchEvent(MotionEvent event) { 
			boolean judge=OnorPause;
			if(!(event.getX()>50 && event.getX()<100 && event.getY()>50 && event.getY()<100)) {
				if(OnorPause==true && event.getX()>MyBoat.getx() && event.getX()<MyBoat.getx()+MyBoat.getwidth() && event.getY()>MyBoat.gety() && event.getY()<MyBoat.gety()+MyBoat.getheight()){
		            mX = event.getX(); // 获得坐标
		            mY = event.getY(); 
					MyBoat.setx(mX);   
			        MyBoat.sety(mY);
				}
			}else{
				if(MotionEvent.ACTION_DOWN==event.getAction()){
					if(judge==true){
						OnorPause=false;
				    }else{
				    	OnorPause=true;			
				    }
				}
				
			}
	        return true; 
	    }
		
		public void drawbackground(Canvas canvas){
			background1.draw(canvas);
			background2.draw(canvas);
		}
		
		public void drawMyBoat(Canvas canvas){
			MyBoat.draw(canvas);
			
		}
		
		public void drawbullet(Canvas canvas){
			paint.setColor(Color.WHITE);
			for(int i=0;i<bullet.size();i++){
				canvas.drawCircle(bullet.get(i).getx(), bullet.get(i).gety(), 5, paint);
			}
		}
		
		public void judgedied(Enemy enemy){
			for(int i=0;i<bullet.size();i++){
				if(bullet.get(i).gety()<0){
					bullet.removeElementAt(i);continue;
				}
				if(bullet.get(i).getx()>enemy.getx() && bullet.get(i).getx()<enemy.getx()+enemy.getwidth() && bullet.get(i).gety()>enemy.gety() && bullet.get(i).gety()<enemy.gety()+enemy.getheight()){
					enemy.minuslife();
					bullet.removeElementAt(i);
				}
			}
		}
		
		public void judgecrash(Enemy enemy){
			float x1=MyBoat.getx()+MyBoat.getwidth()/2,y1=MyBoat.gety();
			float x2=x1-MyBoat.getwidth()/2,y2=y1+MyBoat.getheight()/2;
			float x3=x2+MyBoat.getwidth(),y3=y2;
			int left=enemy.getx(),right=enemy.getx()+enemy.getwidth(),top=enemy.gety(),bottom=enemy.gety()+enemy.getheight();
			if(x1>left && x1<right && y1>top && y1<bottom){
				MyBoat.setjudge(false);
			}
			if(x2>left && x2<right && y2>top && y2<bottom){
				MyBoat.setjudge(false);
			}
			if(x3>left && x3<right && y3>top && y3<bottom){
				MyBoat.setjudge(false);
			}
		}
		
		public void drawEnemy(Canvas canvas){
			for(int i=0;i<enemy3.size();i++){
				enemy3.get(i).draw(canvas);
				if(enemy3.get(i).gety()>screenHeight){
					enemy3.removeElementAt(i);
				}
			}
			for(int i=0;i<enemy2.size();i++){
				enemy2.get(i).draw(canvas);
				if(enemy2.get(i).gety()>screenHeight){
					enemy2.removeElementAt(i);
				}
			}
			for(int i=0;i<enemy1.size();i++){
				enemy1.get(i).draw(canvas);
				if(enemy1.get(i).gety()>screenHeight){
					enemy1.removeElementAt(i);
				}
			}
		}
		
		public void drawappendix(Canvas canvas){
			if(OnorPause==true){
				canvas.drawBitmap(PauseButton, 50, 50, paint);
			}else{
				canvas.drawBitmap(OnButton, 50, 50, paint);
			}
			paint.setColor(Color.WHITE);
			paint.setTextSize(25);
			paint.setTypeface(Typeface.SANS_SERIF);
			canvas.drawText("Level:"+Level, screenWidth/2, 75, paint);
			canvas.drawText("Score:"+currentScore, 425, 75, paint);
		}
		
		public void Update(){
			background1.move();
			background2.move();
			if(background1.gety()>screenHeight){
				background1.sety(-screenHeight);
			}
			if(background2.gety()>screenHeight){
				background2.sety(-screenHeight);
			}
			for(int i=0;i<explode.size();i++){
				explode.get(i).nextStep();
				if(explode.get(i).getjudgefinish()==false){
					explode.removeElementAt(i);
				}
			}
			for(int i=0;i<bullet.size();i++){
				bullet.get(i).move();
			}
			for(int i=0;i<enemy1.size();i++){
				enemy1.get(i).move();
				judgecrash(enemy1.get(i));
				judgedied(enemy1.get(i));
				if(enemy1.get(i).getjudge()==false){
					explode.add(new Explode(enemy1.get(i).getx(),enemy1.get(i).gety(),Explode1Img,Explode2Img,Explode3Img,Explode4Img,Explode5Img,Explode6Img));
					enemy1.removeElementAt(i);
					currentScore+=enemy1Score;
				}
			}
			for(int i=0;i<enemy2.size();i++){
				enemy2.get(i).move();
				judgecrash(enemy2.get(i));
				judgedied(enemy2.get(i));
				if(enemy2.get(i).getjudge()==false){
					explode.add(new Explode(enemy2.get(i).getx(),enemy2.get(i).gety(),Explode1Img,Explode2Img,Explode3Img,Explode4Img,Explode5Img,Explode6Img));
					enemy2.removeElementAt(i);
					currentScore+=enemy2Score;
				}
			}
			for(int i=0;i<enemy3.size();i++){
				enemy3.get(i).move();
				judgedied(enemy3.get(i));	
				judgecrash(enemy3.get(i));
				if(enemy3.get(i).getjudge()==false){
					explode.add(new Explode(enemy3.get(i).getx(),enemy3.get(i).gety(),Explode1Img,Explode2Img,Explode3Img,Explode4Img,Explode5Img,Explode6Img));
					enemy3.removeElementAt(i);
					currentScore+=enemy3Score;
				}
			}
		}
		
		public void drawexplode(Canvas canvas){
			for(int i=0;i<explode.size();i++){
				explode.get(i).draw(canvas);
			}
		}
		
		public void drawactivity(Canvas canvas){
			Random random = new Random();
			if(countbullet==bulletappearrate){
				bullet.add(new Bullet(MyBoat.getx()+MyBoat.getwidth()/2,MyBoat.gety()));
			}
			if(countenemy1==enemy1appearrate){
				int x=random.nextInt(1000)%(screenWidth-Enemy1Width)+1;
				int y=0-Enemy1Height;
				int speed=random.nextInt(enemy1speed)+2;
				enemy1.add(new Enemy1(x,y,speed));
			}
			if(countenemy2==enemy2appearrate){
				int x=random.nextInt(1000)%(screenWidth-Enemy2Width)+1;
				int y=0-Enemy2Height;
				int speed=random.nextInt(enemy2speed)+1;
				enemy2.add(new Enemy2(x,y,speed));
			}
			if(countenemy3==enemy3appearrate){
				int x=random.nextInt(1000)%(screenWidth-Enemy3Width)+1;
				int y=0-Enemy3Height;
				int speed=random.nextInt(enemy3speed)+1;
				enemy3.add(new Enemy3(x,y,speed));
			}			
            drawbackground(canvas);
			drawbullet(canvas);
			drawEnemy(canvas);
			drawMyBoat(canvas);
			drawexplode(canvas);
			drawappendix(canvas);
			Update();
			countbullet++;
			countenemy1++;
			countenemy2++;
			countenemy3++;
			if(countbullet>bulletappearrate){
				countbullet=0;
			}
			if(countenemy1>enemy1appearrate){
				countenemy1=0;
			}
			if(countenemy2>enemy2appearrate){
				countenemy2=0;
			}
			if(countenemy3>enemy3appearrate){
				countenemy3=0;
			}
			if(MyBoat.getjudge()==false){
				runOnUiThread(new Runnable()    
			    {    
					public void run()    
			        {
			            id=1;			           
			            setContentView(R.layout.activity_main);//返回开始界面
			        }
			     });
		     }
		}
		
		public void drawpause(Canvas canvas){
			drawbackground(canvas);
			drawMyBoat(canvas);
			drawbullet(canvas);
			drawEnemy(canvas);
			drawexplode(canvas);
			drawappendix(canvas);
		}
		
		public void judgeLevel(){
			if(currentScore>=3000){
				Level=2;
			}
			if(currentScore>=10000){
				Level=3;
			}
			if(currentScore>=50000){
				Level=4;
			}
			if(currentScore>=100000){
				Level=5;
			}
			switch(Level){
			case 2:
		    	enemy1appearrate=15;
		    	enemy2appearrate=60;
		    	enemy3appearrate=150;
		    	enemy1speed=8;
		    	enemy2speed=5;
		    	enemy3speed=3;break;
			case 3:
		    	enemy1appearrate=10;
		    	enemy2appearrate=45;
		    	enemy3appearrate=100;
		    	enemy1speed=8;
		    	enemy2speed=6;
		    	enemy3speed=4;break;
			case 4:
		    	enemy1appearrate=8;
		    	enemy2appearrate=40;
		    	enemy3appearrate=90;
		    	enemy1speed=10;
		    	enemy2speed=7;
		    	enemy3speed=5;break;
			case 5:
		    	enemy1appearrate=5;
		    	enemy2appearrate=30;
		    	enemy3appearrate=70;
		    	enemy1speed=12;
		    	enemy2speed=9;
		    	enemy3speed=7;break;
			}
		}
		
		class MyThread implements Runnable{
			@Override
			public void run(){
				id=2;
		    	enemy1appearrate=15;
		    	enemy2appearrate=80;
		    	enemy3appearrate=200;
		    	enemy1speed=6;
		    	enemy2speed=4;
		    	enemy3speed=3;
		    	enemy1life=1;
		    	enemy2life=5;
		    	enemy3life=10;
				while(IsRunning){
					Canvas canvas=holder.lockCanvas();
					judgeLevel();
					if(OnorPause==true){
						drawactivity(canvas);
					}else{
						drawpause(canvas);
					}
					holder.unlockCanvasAndPost(canvas);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					  }
				}
			}
		}
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {         

    	if(keyCode == KeyEvent.KEYCODE_BACK && id==2){
    		id=1;
         	setContentView(R.layout.activity_main);
         	return true;
    	 }
    	if(keyCode == KeyEvent.KEYCODE_BACK && id==1){
    		java.lang.System.exit(0);
         	return true;
    	 }
    	return super.onKeyDown(keyCode, event);
     }

    
    public void Click(View v){
    	setContentView(new MyView(this));
    }
    
    public void Click1(View v){
    	this.finish();
    }
    
}

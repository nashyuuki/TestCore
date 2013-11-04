package com.coco.marumaru.model;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.coco.core.Consts;
import com.coco.core.CoreController;
import com.coco.core.CoreModel;
import com.coco.marumaru.Sprite.BoxerSprite;
import com.coco.marumaru.Sprite.FingerSprite;
import com.coco.marumaru.Sprite.FunctionSprite;
import com.coco.marumaru.Sprite.LineSimple;
import com.coco.marumaru.Sprite.MaruSprite;
import com.coco.marumaru.Sprite.PointSprite;
import com.coco.marumaru.config.MusicConfig;
import com.coco.marumaru.consts.GameConsts;

public class GameModel extends CoreModel
{
	private static final int INIT=0;
	private static final int PLAY=1;
	private static final int END=2;
	
	private static final int SPEED_NORMAL=0;
	private static final int SPEED_UP=1;
	private static final int SPEED_DOWN=2;
	private Hashtable<String,MaruSprite> marus;
	private int bornTime=0;
	private Random random;
	private LineSimple[] line;
	private PointSprite pointSprite;
	private FingerSprite fingerSprite;
	private BoxerSprite boxerSprite;
	private FunctionSprite functionSprite;
	private int speedTime=-1;
	private int speedType=0;
	private int scaleTime=-1;
	private int[][] barrierScript;
	public GameModel(CoreController coreController)
	{
		super(coreController);
	}
	@Override
	public void init()
	{
		fingerSprite =new FingerSprite(this.getImageConfig());
		fingerSprite.setCollisionArea(GameConsts.FINGER_COLLISION);
		boxerSprite= new BoxerSprite(this.getImageConfig());
		boxerSprite.setCollisionArea(GameConsts.BOXER_COLLISION);
		functionSprite=new FunctionSprite(this.getImageConfig());
		functionSprite.setCollisionArea(GameConsts.FUNCTION_COLLISION);
		functionSprite.setPosition(GameConsts.FUNCTION_POSITION);
		functionSprite.setState(FunctionSprite.FINGER);
		barrierScript=GameConsts.BARRIER_SCRIPT01;
		line=new LineSimple[barrierScript.length];
		for(int i=0;i<line.length;i++)
		{
			int[] lineScript=barrierScript[i];
			line[i]=new LineSimple(lineScript[0],lineScript[1],GameConsts.CRUNODE_NORMAL);
		}
		pointSprite=new PointSprite(this.getImageConfig());
		pointSprite.setNumber(123);
		pointSprite.setPosition(GameConsts.POINT_POSITION);
		pointSprite.setState(PointSprite.POINT);
		marus=new Hashtable<String,MaruSprite>();
		random=new Random();
		subState=this.PLAY;
	}
	
	
	@Override
	public void restart()
	{
//		Log.v(Consts.TAG, "restart!");
//		line.update(gameBean.getRotate(),gameBean.getWave(),gameBean.getCrunode());
//		Enumeration<MaruSprite> emaru=marus.elements();
//		while(emaru.hasMoreElements())
//		{
//			MaruSprite maruSprite=emaru.nextElement();
//			maruSprite.setXyVector(line.getXyVector());
//		}
		
	}
	private int[] getRandomPosition(int[] array)
	{//x,y,x1,y1
		int x=array[0];
		int y=array[1];
		int x1=array[2];
		int y1=array[3];
		int[] randomPosition=new int[2];
		int xlength=x1-x;
		int ylength=y1-y;
		randomPosition[0]=this.getRandomPosition(x, xlength);
		randomPosition[1]=this.getRandomPosition(y, ylength);
		return randomPosition;
	}
	private int[] getScreenPosition(int[] array,int length)
	{
		int[] screenPosition=new int[2];
		if(array[0]>Consts.SCREEN_WIDTH-length)
		{
			screenPosition[0]=Consts.SCREEN_WIDTH-length;
		}
		else
		{
			screenPosition[0]=array[0];
		}
		if(array[1]>Consts.SCREEN_HEIGHT-length)
		{
			screenPosition[1]=Consts.SCREEN_HEIGHT-length;
		}
		else
		{
			screenPosition[1]=array[1];
		}
		
		return screenPosition;
	}
	private int getRandomPosition(int position,int length)
	{
		if(length==0)
		{
			return position;
		}
		else if(length>0)
		{
			return position+Math.abs(random.nextInt()%length);
		}
		else
		{
			return position-Math.abs(random.nextInt()%length);
		}
	}
	private int getRandomKind(int[] kindRange)
	{
		int randomKind=-1;
		int randomRange=Math.abs(random.nextInt()%100);
		int value=0;
		for(int i=0;i<kindRange.length;i++)
		{
			value=value+kindRange[i];
			if(randomRange<value)
			{
				return i;
			}
		}
		return randomKind;
	}
	private int getRandomPoint(int[] pointScript)
	{
		int index=Math.abs(random.nextInt()%pointScript.length);
		return pointScript[index];
	}
	@Override
	public void update()
	{
		if(subState==this.PLAY)
		{
			bornTime++;
			
			for(int i=0;i<barrierScript.length;i++)
			{
				int lineType=i;
				int[] lineScript=barrierScript[lineType];
				int boreRate=lineScript[2];
				int[] maruKindRange=GameConsts.MARU_KIND_RANGE[lineScript[4]];
				int randomKind=getRandomKind(maruKindRange);
				if(bornTime%boreRate==0&&randomKind!=-1)
				{
					int[] bornPosition=GameConsts.BORN_POSITION[lineScript[3]];
					MaruSprite maruSprite=new MaruSprite(this.getImageConfig());
					maruSprite.setLineType(lineType);
					if(randomKind==MaruSprite.TYPE_PINK)
					{//正分
						int randomPoint=this.getRandomPoint(GameConsts.POINT_UP_SCRIPT[lineScript[5]]);
						maruSprite.setPoint(randomPoint);
					}
					else if(randomKind==MaruSprite.TYPE_GREEN)
					{//負分
						int randomPoint=this.getRandomPoint(GameConsts.POINT_DOWN_SCRIPT[lineScript[6]]);
						maruSprite.setPoint(randomPoint);
					}
					maruSprite.setMaruType(randomKind);
					int no=getHashNo(marus);
					maruSprite.setCollisionArea(GameConsts.MARU_COLLISION);
					maruSprite.setState(MaruSprite.BORN);
					maruSprite.setNo(no);
					maruSprite.setXyVector(line[lineType].getXyVector());
					maruSprite.setScale(gameBean.getScale());
					maruSprite.setSpeedType(speedType);
					maruSprite.setPosition(this.getScreenPosition(this.getRandomPosition(bornPosition),maruSprite.getLength()));
					marus.put(""+no, maruSprite);
				}
			}
			
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maruSprite=emaru.nextElement();
				if(maruSprite.getState()==MaruSprite.MOVE)
				{
					
				}
				else if(maruSprite.getState()==MaruSprite.DESTROY)
				{
					marus .remove(""+maruSprite.getNo());
				}
				if(this.isOutScreen(maruSprite)&&maruSprite.getState()!=MaruSprite.DESTROY)
				{
					maruSprite.setState(MaruSprite.DESTROY);
				}
				maruSprite.update();
			}
			if(scaleTime>0)
			{//放大縮小中
				scaleTime--;
			}
			else if(scaleTime==0)
			{//恢復
				gameBean.setScale(GameConsts.SCALE_NORMAL);
				emaru=marus.elements();
				while(emaru.hasMoreElements())
				{
					MaruSprite maru=emaru.nextElement();
					maru.setScale(gameBean.getScale());
				}
				scaleTime=-1;
			}
			if(speedTime>0)
			{//加快減慢中
				speedTime--;
			}
			else if(speedTime==0)
			{//恢復
				speedType=SPEED_NORMAL;
				while(emaru.hasMoreElements())
				{
					MaruSprite maru=emaru.nextElement();
					maru.setSpeedType(speedType);
				}
				speedTime=-1;
			}
			if(fingerSprite.getState()==FingerSprite.EFFECT)
			{
				emaru=marus.elements();
				while(emaru.hasMoreElements())
				{
					MaruSprite maruSprite=emaru.nextElement();
					if(maruSprite.getState()==MaruSprite.MOVE)
					{
						if(maruSprite.isCollision(fingerSprite))
						{
							this.playMusic(MusicConfig.BURST);
							maruSprite.setState(MaruSprite.BURST);
							this.maruEffect(maruSprite);
						}
					}
				}
			}
			fingerSprite.update();
			if(boxerSprite.getState()==BoxerSprite.EFFECT)
			{
				emaru=marus.elements();
				while(emaru.hasMoreElements())
				{
					MaruSprite maruSprite=emaru.nextElement();
					if(maruSprite.getState()==MaruSprite.MOVE)
					{
						if(maruSprite.isCollision(boxerSprite))
						{
							this.playMusic(MusicConfig.BURST);
							maruSprite.setState(MaruSprite.BURST);
							this.maruEffect(maruSprite);
						}
					}
				}
			}
			boxerSprite.update();
		}
		else if(subState==this.END)
		{
			//this.setNextState(ModelConfig.LOGO);
		}
	}
	private boolean isOutScreen(MaruSprite maruSprite)
	{
		int x=maruSprite.getX();
		int y=maruSprite.getY();
		int length=maruSprite.getLength();
		if(x<-length)
		{
			return true;
		}
		else if(x>Consts.SCREEN_WIDTH)
		{
			return true;
		}
		else if(y<-length)
		{
			return true;
		}
		else if(y>Consts.SCREEN_HEIGHT)
		{
			return true;
		}
		return false;
	}
	public static int getHashNo(Map hash)
	{
		int no=0;
		while(no>=0)
		{
			if(hash.get("" + no) == null)
			{
				break;
			}
			no++;
		}
		return no;
	}
	@Override
	public void drawView(Canvas canvas)
	{
		if(subState==this.PLAY)
		{
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maruSprite=emaru.nextElement();
				maruSprite.drawView(canvas);
			}
			pointSprite.drawView(canvas);
			fingerSprite.drawView(canvas);
			boxerSprite.drawView(canvas);
			functionSprite.drawView(canvas);
		}
	}
	public void maruEffect(MaruSprite maruSprite)
	{
		int maruType=maruSprite.getMaruType();
		if(maruType==MaruSprite.TYPE_PINK)
		{//加分
			int point=pointSprite.getNumber();
			point=point+maruSprite.getPoint();
			pointSprite.setNumber(point);
		}
		else if(maruType==MaruSprite.TYPE_GREEN)
		{//扣分
			int point=pointSprite.getNumber();
			point=point-maruSprite.getPoint();
			if(point==0)
			{
				point=0;
			}
			pointSprite.setNumber(point);
		}
		else if(maruType==MaruSprite.TYPE_ORANGE)
		{//全消
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maru=emaru.nextElement();
				if(maru.getMaruType()==MaruSprite.TYPE_PINK)
				{//只計算正分
					int point=pointSprite.getNumber();
					point=point+maruSprite.getPoint();
					pointSprite.setNumber(point);
				}
				maru.setState(MaruSprite.BURST);
			}
		}
		else if(maruType==MaruSprite.TYPE_BLUE_UP)
		{//放大
			Log.v(Consts.TAG, "SCALE BIG");
			gameBean.setScale(GameConsts.SCALE_BIG);
			scaleTime=GameConsts.SCALE_TIME;
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maru=emaru.nextElement();
				maru.setScale(gameBean.getScale());
			}
		}
		else if(maruType==MaruSprite.TYPE_BLUE_DOWN)
		{//縮小
			gameBean.setScale(GameConsts.SCALE_SMALL);
			scaleTime=GameConsts.SCALE_TIME;
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maru=emaru.nextElement();
				maru.setScale(gameBean.getScale());
			}
		}
		else if(maruType==MaruSprite.TYPE_BLACK_UP)
		{//加速
			speedTime=GameConsts.SPEED_TIME;
			speedType=SPEED_UP;
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maru=emaru.nextElement();
				maru.setSpeedType(speedType);
			}
		}
		else if(maruType==MaruSprite.TYPE_BLACK_DOWN)
		{//減速
			speedTime=GameConsts.SPEED_TIME;
			speedType=SPEED_DOWN;
			Enumeration<MaruSprite> emaru=marus.elements();
			while(emaru.hasMoreElements())
			{
				MaruSprite maru=emaru.nextElement();
				maru.setSpeedType(speedType);
			}
		}
	}
	@Override
	public void onKeyDown(int keyCode)
	{
		if(subState==this.PLAY)
		{
			if(keyCode==KeyEvent.KEYCODE_DPAD_CENTER)
			{
				//subState=this.END;
				Log.v(Consts.TAG, "MenuModel start");
				Intent intent=new Intent();
				intent.setClass(context,MenuModel.class);
				context.startActivity(intent);
			}
			else if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT)
			{
				this.exitGame();
			}
			else if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT)
			{
				
			}
		}
	}
	@Override
	public void onKeyUp(int keyCode)
	{
		
	}
	public void onTouchEvent(int x,int y,MotionEvent event)
	{
		if(subState==this.PLAY)
		{
			if(functionSprite.isCollision(x, y))
			{
				if(functionSprite.getState()==FunctionSprite.BOXER)
				{
					functionSprite.setState(FunctionSprite.FINGER);
				}
				else if(functionSprite.getState()==FunctionSprite.FINGER)
				{
					functionSprite.setState(FunctionSprite.BOXER);
				}
			}
			else
			{
				if(functionSprite.getState()==FunctionSprite.FINGER)
				{
					if(fingerSprite.getState()==FingerSprite.DISABLE)
					{
						fingerSprite.setPosition(x+GameConsts.FINGER_POSITION[0], y+GameConsts.FINGER_POSITION[1]);
						fingerSprite.setState(FingerSprite.EFFECT);
					}
				}
				else if(functionSprite.getState()==FunctionSprite.BOXER)
				{
					if(boxerSprite.getState()==BoxerSprite.DISABLE)
					{
						boxerSprite.setPosition(x+GameConsts.BOXER_POSITION[0], y+GameConsts.BOXER_POSITION[1]);
						boxerSprite.setState(BoxerSprite.PICK);
					}
				}
			}

		}
	}
	
}

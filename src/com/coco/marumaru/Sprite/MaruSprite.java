package com.coco.marumaru.Sprite;

import android.graphics.Canvas;
import android.util.Log;

import com.coco.core.Consts;
import com.coco.core.utils.NumberUtil;
import com.coco.marumaru.config.ImageConfig;
import com.coco.marumaru.consts.GameConsts;

public class MaruSprite extends Sprite
{

	public static final int BORN=1;
	public static final int MOVE=2;
	public static final int BURST=3;
	public static final int DESTROY=4;
	
	public static final int TYPE_BLACK_UP=0;
	public static final int TYPE_BLACK_DOWN=1;
	public static final int TYPE_ORANGE=2;
	public static final int TYPE_BLUE_UP=3;
	public static final int TYPE_BLUE_DOWN=4;
	public static final int TYPE_GREEN=5;
	public static final int TYPE_PINK=6;
	
	public static final int SPEED_NORMAL=0;
	public static final int SPEED_UP=1;
	public static final int SPEED_DOWN=2;
	
	
	private int speedType;
	private int no;
	private int maruType=0;
	private int point=0;
	private int[] pointArray;
	private double[][] xyVector;
	private int indexX;
	private int indexY;
	private int lineType;
	private long moveLastTime;
	
	
	public MaruSprite(ImageConfig imageConfig)
	{
		super(imageConfig);
	}
	
	public int getMaruType()
	{
		return maruType;
	}

	public void setMaruType(int maruType)
	{
		this.maruType = maruType;
	}

	public void update()
	{
		if(state==BORN)
		{
			if(this.nextTime(timeSpeed))
			{
				if(this.nextScriptInt(GameConsts.MARU_BORN_SCRIPT.length))
				{
					this.setState(this.MOVE);
				}
			}
		}
		else if(state==MOVE)
		{
			long moveNowTime=System.currentTimeMillis();
			int speedTime=GameConsts.SPEED_NORMAL_TIME;
			if(speedType==this.SPEED_NORMAL)
			{
				speedTime=GameConsts.SPEED_NORMAL_TIME;
			}
			else if(speedType==this.SPEED_DOWN)
			{
				speedTime=GameConsts.SPEED_DOWN_TIME;
			}
			else if(speedType==this.SPEED_UP)
			{
				speedTime=GameConsts.SPEED_UP_TIME;
			}
			if(moveLastTime==0)
			{
				moveLastTime=System.currentTimeMillis();
			}
			else if(moveNowTime-moveLastTime>speedTime)
			{
				
				int time =(int)((moveNowTime-moveLastTime)/speedTime);
				for(int i=0;i<time;i++)
				{
					indexX=indexX<xyVector[0].length-1?indexX+1:0;
					x=x+(int)(xyVector[0][indexX]);
					indexY=indexY<xyVector[1].length-1?indexY+1:0;
					y=y+(int)(xyVector[1][indexY]);
				}
				moveLastTime=moveNowTime;
			}
			if(this.nextTime(timeSpeed))
			{
				this.nextScriptInt(GameConsts.MARU_MOVE_SCRIPT.length);
			}
		}
		else if(state==BURST)
		{
			if(this.nextTime(GameConsts.MARU_BURST_TIME))
			{
				if(this.nextScriptInt(GameConsts.MARU_BURST_SCRIPT01.length))
				{
					this.setState(this.DESTROY);
				}
			}
		}
	}
	public void drawView(Canvas canvas)
	{
		if(state==BORN)
		{
			int width=this.getWidth(GameConsts.MARU_BORN[maruType]);
			int height=this.getHeight(GameConsts.MARU_BORN[maruType])/2;
			this.drawImage(canvas, GameConsts.MARU_BORN[maruType], x, y, width, height, scriptInt,scale,scale);
		}
		else if(state==MOVE)
		{
			int width=this.getWidth(GameConsts.MARU[maruType]);
			int height=this.getHeight(GameConsts.MARU[maruType])/2;
			this.drawImage(canvas, GameConsts.MARU[maruType], x, y, width, height, scriptInt,scale,scale);
			if(maruType==this.TYPE_PINK||maruType==this.TYPE_GREEN)
			{
				if(scriptInt==0)
				{
					int pointWidth=this.getWidth(ImageConfig.UI_POINT01)/10;
					int pointHeight=this.getHeight(ImageConfig.UI_POINT01);
					for(int i=0;i<pointArray.length;i++)
					{
						this.drawImage(canvas, ImageConfig.UI_POINT01, 
								x+(int)((GameConsts.MARU_POINT01_POSITION[0]-i*pointWidth)*scale), 
								y+(int)(GameConsts.MARU_POINT01_POSITION[1]*scale), pointWidth, pointHeight, pointArray[i],scale,scale);
					}
				}
				else if(scriptInt==1)
				{
					int pointWidth=this.getWidth(ImageConfig.UI_POINT)/10;
					int pointHeight=this.getHeight(ImageConfig.UI_POINT);
					for(int i=0;i<pointArray.length;i++)
					{
						this.drawImage(canvas, ImageConfig.UI_POINT, 
								x+(int)((GameConsts.MARU_POINT_POSITION[0]-i*pointWidth)*scale), 
								y+(int)(GameConsts.MARU_POINT_POSITION[1]*scale), pointWidth, pointHeight, pointArray[i],scale,scale);
					}
				}
			}
			else if(maruType==this.TYPE_BLACK_UP||maruType==this.TYPE_BLUE_UP)
			{
				int arrowWidth=this.getWidth(ImageConfig.UI_ARROW)/2;
				int arrowHeight=this.getHeight(ImageConfig.UI_ARROW);
				for(int i=0;i<2;i++)
				{
					this.drawImage(canvas, ImageConfig.UI_ARROW, 
							x+(int)((GameConsts.MARU_ARROW_POSITION[0]-i*arrowWidth)*scale), 
							y+(int)(GameConsts.MARU_ARROW_POSITION[1]*scale), arrowWidth, arrowHeight, 1,scale,scale);
				}
			}
			else if(maruType==this.TYPE_BLACK_DOWN||maruType==this.TYPE_BLUE_DOWN)
			{
				int arrowWidth=this.getWidth(ImageConfig.UI_ARROW)/2;
				int arrowHeight=this.getHeight(ImageConfig.UI_ARROW);
				for(int i=0;i<2;i++)
				{
					this.drawImage(canvas, ImageConfig.UI_ARROW, 
							x+(int)((GameConsts.MARU_ARROW_POSITION[0]-i*arrowWidth)*scale), 
							y+(int)(GameConsts.MARU_ARROW_POSITION[1]*scale), arrowWidth, arrowHeight, 0,scale,scale);
				}
			}
		}
		else if(state==this.BURST)
		{
			int width=this.getWidth(GameConsts.MARU_BURST[maruType])/5;
			int height=this.getHeight(GameConsts.MARU_BURST[maruType])/2;
			this.drawImage(canvas, GameConsts.MARU_BURST[maruType], 
					x+(int)(GameConsts.MARU_BURST_POSITION[0]*scale), 
					y+(int)(GameConsts.MARU_BURST_POSITION[1]*scale), width, height, GameConsts.MARU_BURST_SCRIPT01[scriptInt],scale,scale);
			this.drawImage(canvas, GameConsts.MARU_BURST[maruType], 
					x+(int)(GameConsts.MARU_BURST_POSITION[0]*scale), 
					y+(int)(GameConsts.MARU_BURST_POSITION[1]*scale), width, height, GameConsts.MARU_BURST_SCRIPT02[scriptInt],scale,scale);
		}
	}
	public int getLength()
	{
		return (int)(this.getWidth(GameConsts.MARU[maruType])*scale);
	}
	public int getNo()
	{
		return no;
	}

	public void setNo(int no)
	{
		this.no = no;
	}
	

	public void setXyVector(double[][] xyVector)
	{
		this.xyVector = xyVector;
	}

	public int getPoint()
	{
		return point;
	}

	public void setPoint(int point)
	{
		this.point = point;
		this.pointArray=NumberUtil.transformArrary(point, 2);
	}

	public int getLineType()
	{
		return lineType;
	}

	public void setLineType(int lineType)
	{
		this.lineType = lineType;
	}

	public int getSpeedType()
	{
		return speedType;
	}

	public void setSpeedType(int speedType)
	{
		this.speedType = speedType;
	}
	
}

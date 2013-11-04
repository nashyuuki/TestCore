package com.coco.marumaru.Sprite;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.coco.core.DrawUtil;
import com.coco.marumaru.config.ImageConfig;

public class Sprite
{
	public static final int DISABLE = 0;
	public int x;
	public int y;
	public int state;
	public int time;
	public int scriptInt;
	public long timeSpeed=200;
	private ImageConfig imageConfig;
	private int[] collisionArea = {};
	private int[] scaleArea;
	public float scale=1;
	private long lastTime;
	public void update(){};
	public void drawView(Canvas canvas){};
	public Sprite(ImageConfig imageConfig)
	{
		this.imageConfig=imageConfig;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getX()
	{
		return x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getY()
	{
		return y;
	}
	public void setPosition(int[] position)
	{
		this.x=position[0];
		this.y=position[1];
	}
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void setState(int state)
	{
		lastTime=0;
		time = 0;
		scriptInt = 0;
		this.state = state;
	}
	public int getState()
	{
		return state;
	}
	public boolean nextScriptInt(int scriptLength)
	{
		boolean scriptEnd = false;
		if (scriptInt < (scriptLength - 1))
		{
			scriptInt++;
		} else
		{
			scriptInt = 0;
			scriptEnd = true;
		}
		return scriptEnd;
	}
	public boolean nextScriptInt(int scriptInt, int scriptLength)
	{
		boolean scriptEnd = true;
		if (scriptInt < (scriptLength - 1))
		{
			scriptInt++;
		} else
		{
			scriptEnd = false;
		}
		return scriptEnd;
	}
	public boolean nextTime(long time)
	{
		long nowTime=System.currentTimeMillis();
		if(lastTime==0)
		{
			lastTime=System.currentTimeMillis();		
		}
		else if(nowTime-lastTime>time)
		{
			lastTime=nowTime;
			return true;
		}
		return false;
	}
//	public boolean nextTime(int timeSpeed)
//	{
//		boolean work = false;
//		if (time == 0)
//		{
//			work = false;
//		} 
//		else if (time % timeSpeed == 0)
//		{
//			work = true;
//		}
//		time++;
//		return work;
//	}
	public boolean isCollision(Sprite s1)
	{
		int x0, y0, x1, y1, ox0, oy0, ox1, oy1;
		int[] s1Area = s1.getCollisionArea();
		x0 = this.scaleArea[0] + this.getX();
		y0 = this.scaleArea[1] + this.getY();
		x1 = this.scaleArea[2] + this.getX();
		y1 = this.scaleArea[3] + this.getY();
		ox0 = s1Area[0] + s1.getX();
		oy0 = s1Area[1] + s1.getY();
		ox1 = s1Area[2] + s1.getX();
		oy1 = s1Area[3] + s1.getY();
		if (ox0 < x1 && x0 < ox1 && oy0 < y1 && y0 < oy1)
		{
			return true;
		}
		return false;
	}
	public boolean isCollision(int x,int y)
	{
		int x0,y0,x1,y1;
		x0 = this.scaleArea[0] + this.getX();
		y0 = this.scaleArea[1] + this.getY();
		x1 = this.scaleArea[2] + this.getX();
		y1 = this.scaleArea[3] + this.getY();
		if(x>x0&&x<x1&&y>y0&&y<y1)
		{
			return true;
		}
		return false;
	}
	public Drawable getDrawable(int image)
	{
		return imageConfig.getDrawable(image);
	}
	public int getWidth(int image)
	{
		return this.getDrawable(image).getIntrinsicWidth();
	}
	public int getHeight(int image)
	{
		return this.getDrawable(image).getIntrinsicHeight();
	}
	public void drawImage(Canvas canvas, int image, int x, int y, int width,int height,int frameInt,float scaleX,float scaleY)
	{
		Drawable drawable=this.getDrawable(image);
		DrawUtil.drawImage(canvas, drawable, x, y, width, height, frameInt,scaleX,scaleY);
	}
	public void drawImage(Canvas canvas,int image,int x,int y, int width,int height,int frameInt)
	{
		Drawable drawable=this.getDrawable(image);
		DrawUtil.drawImage(canvas, drawable, x, y, width, height, frameInt);
	}
	public void drawImage(Canvas canvas,int image,int x,int y)
	{
		Drawable drawable=this.getDrawable(image);
		DrawUtil.drawImage(canvas, drawable, x, y);
	}
	public int[] getCollisionArea()
	{
		return collisionArea;
	}
	public void setCollisionArea(int[] collisionArea)
	{
		this.collisionArea = collisionArea;
		this.setScale(scale);
	}
	public double getScale()
	{
		return scale;
	}
	public void setScale(float scale)
	{
		this.scale = scale;
		this.scaleArea=new int[collisionArea.length];
		for(int i=0;i<this.scaleArea.length;i++)
		{
			this.scaleArea[i]=(int)(collisionArea[i]*scale);
		}
		
	}
}

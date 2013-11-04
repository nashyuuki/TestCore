package com.coco.marumaru.config;

import java.util.HashMap;
import java.util.Map;

import com.coco.core.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ImageConfig
{
	public static final int LOGO=0;
	public static final int MARU01_1=1;
	public static final int MARU02_1=2;
	public static final int MARU03_1=3;
	public static final int MARU04_1=4;
	public static final int MARU05_1=5;
	public static final int MARU01=6;
	public static final int MARU02=7;
	public static final int MARU03=8;
	public static final int MARU04=9;
	public static final int MARU05=10;
	public static final int MARU01_2=11;
	public static final int MARU02_2=12;
	public static final int MARU03_2=13;
	public static final int MARU04_2=14;
	public static final int MARU05_2=15;
	public static final int UI_NUMBER=16;
	public static final int UI_POINT=17;
	public static final int UI_POINT01=18;
	public static final int UI_ARROW=19;
	public static final int FINGER=20;
	public static final int BOXER01=21;
	public static final int BOXER02=22;
	public static final int UI_FINGER=23;
	public static final int UI_BOXER=24;
	
	
	private Map<String,Drawable> drawables;
	private Context context;
	public ImageConfig(Context context)
	{
		this.context=context;
		drawables=new HashMap<String,Drawable>();
	}
	public Drawable getDrawable(int image)
	{
		Drawable drawable=drawables.get(""+image);
		if(drawable==null)
		{
			switch(image)
			{
				case LOGO:
					drawable=context.getResources().getDrawable(R.raw.cocohouse);
					break;
				case MARU01_1:
					drawable=context.getResources().getDrawable(R.raw.maru01_1);
					break;
				case MARU02_1:
					drawable=context.getResources().getDrawable(R.raw.maru02_1);
					break;
				case MARU03_1:
					drawable=context.getResources().getDrawable(R.raw.maru03_1);
					break;
				case MARU04_1:
					drawable=context.getResources().getDrawable(R.raw.maru04_1);
					break;
				case MARU05_1:
					drawable=context.getResources().getDrawable(R.raw.maru05_1);
					break;
				case MARU01:
					drawable=context.getResources().getDrawable(R.raw.maru01);
					break;
				case MARU02:
					drawable=context.getResources().getDrawable(R.raw.maru02);
					break;
				case MARU03:
					drawable=context.getResources().getDrawable(R.raw.maru03);
					break;
				case MARU04:
					drawable=context.getResources().getDrawable(R.raw.maru04);
					break;
				case MARU05:
					drawable=context.getResources().getDrawable(R.raw.maru05);
					break;
				case MARU01_2:
					drawable=context.getResources().getDrawable(R.raw.maru01_2);
					break;
				case MARU02_2:
					drawable=context.getResources().getDrawable(R.raw.maru02_2);
					break;
				case MARU03_2:
					drawable=context.getResources().getDrawable(R.raw.maru03_2);
					break;
				case MARU04_2:
					drawable=context.getResources().getDrawable(R.raw.maru04_2);
					break;
				case MARU05_2:
					drawable=context.getResources().getDrawable(R.raw.maru05_2);
					break;
				case UI_NUMBER:
					drawable=context.getResources().getDrawable(R.raw.ui_numbers);
					break;
				case UI_POINT:
					drawable=context.getResources().getDrawable(R.raw.ui_point);
					break;
				case UI_POINT01:
					drawable=context.getResources().getDrawable(R.raw.ui_point01);
					break;
				case UI_ARROW:
					drawable=context.getResources().getDrawable(R.raw.arrow);
					break;
				case FINGER:
					drawable=context.getResources().getDrawable(R.raw.finger);
					break;
				case BOXER01:
					drawable=context.getResources().getDrawable(R.raw.boxing01);
					break;
				case BOXER02:
					drawable=context.getResources().getDrawable(R.raw.boxing02);
					break;
				case UI_FINGER:
					drawable=context.getResources().getDrawable(R.raw.ui_finger);
					break;
				case UI_BOXER:
					drawable=context.getResources().getDrawable(R.raw.ui_boxer);
					break;
				default:
					drawable=context.getResources().getDrawable(R.raw.cocohouse);
					break;
			}
			drawables.put(""+image, drawable);
		}
		
		return drawable;
	}
	public void resetDrawables()
	{
		drawables.clear();
	}
}

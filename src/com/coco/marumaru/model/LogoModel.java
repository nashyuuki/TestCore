package com.coco.marumaru.model;

import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;

import com.coco.core.Consts;
import com.coco.core.CoreController;
import com.coco.core.CoreModel;
import com.coco.core.DrawUtil;
import com.coco.marumaru.config.ImageConfig;
import com.coco.marumaru.config.ModelConfig;
import com.coco.marumaru.consts.GameConsts;

public class LogoModel extends CoreModel
{
	private static final int INIT=0;
	private static final int PLAY=1;
	private static final int END=2;
	private int secInt;
	public LogoModel(CoreController coreController)
	{
		super(coreController);
	}
	public void init()
	{
		secInt=0;
		subState=this.PLAY;
	}
	@Override
	public void onKeyDown(int keyCode)
	{
		if(subState==PLAY)
		{	
			if(keyCode==KeyEvent.KEYCODE_DPAD_CENTER)
			{
				subState=this.END;
			}
		}
	}
	@Override
	public void update()
	{
		if(subState==this.PLAY)
		{
			if(secInt<GameConsts.LOGO_SEC)
			{
				secInt++;
			}
			else
			{
				subState=this.END;
			}
		}
		else if(subState==this.END)
		{
			this.setNextState(ModelConfig.GAME);
		}
		
	}
	@Override
	public void drawView(Canvas canvas)
	{
			int logoWidth=this.getDrawable(ImageConfig.LOGO).getIntrinsicWidth();
			int logoHeight=this.getDrawable(ImageConfig.LOGO).getIntrinsicHeight();
			int logoX=(Consts.SCREEN_WIDTH-logoWidth)/2;
			int logoY=(Consts.SCREEN_HEIGHT-logoHeight)/2;
			DrawUtil.drawImage(canvas,this.getDrawable(ImageConfig.LOGO),
					logoX,logoY);	
	}
	@Override
	public void onKeyUp(int keyCode)
	{
		// TODO Auto-generated method stub
		
	}

}

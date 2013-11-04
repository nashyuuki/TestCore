package com.coco.core;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.coco.marumaru.been.GameBean;
import com.coco.marumaru.config.ImageConfig;
import com.coco.marumaru.config.ModelConfig;
import com.coco.marumaru.config.MusicConfig;

public class CoreController extends SurfaceView implements SurfaceHolder.Callback ,Runnable
{
	private SurfaceHolder holder;
	private Context context;
	private Thread thread;
	private Canvas canvas;
	private boolean running;
	private int state;
	private int nextState;
	private ModelConfig modelConfig;
	private ImageConfig imageConfig;
	private MusicConfig musciConfig;
	private GameBean gameBean;
	private int translateX;
	private int translateY;
	public CoreController(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		Log.v(Consts.TAG, "init");
		gameBean=new GameBean(context);
		gameBean.restoreGameBean();
		holder = getHolder();
		holder.addCallback(this);
		this.context=context;
		modelConfig=new ModelConfig(this);
		imageConfig=new ImageConfig(this.context);
		musciConfig=new MusicConfig(this.context);
		state=-1;//初始狀態
		running=false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2, int arg3)
	{
		Log.v(Consts.TAG,"surfaceChanged");

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		Log.v(Consts.TAG,"surfaceCreated");
		this.resume();
		setFocusable(true); // make sure we get key events
	}
	public void pause()
	{
		//存資料
		gameBean.storeGameBean();
		if(running)
		{
			running=false;
			try
			{
				thread.join();
			}
			catch(Exception e)
			{
				Log.v(Consts.TAG, "pause e:"+e);
			}
		}
	}
	public void resume()
	{
		//讀資料
		gameBean.restoreGameBean();
		CoreModel coreModel=modelConfig.getModel(state);
		coreModel.restart();
		if(!running)
		{
			running=true;
			thread=new Thread(this);
			thread.start();
		}
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		Log.v(Consts.TAG,"surfaceDestroyed");
		this.pause();
	}

	@Override
	public void run()
	{
		while(running)
		{
			CoreModel coreModel=null;
			//取得Model
			if(state==-1)
			{//程式初始化
				state=ModelConfig.INIT_STATE;
				nextState=ModelConfig.INIT_STATE;
				coreModel=modelConfig.getModel(state);
				coreModel.init();
			}
			else if(state!=nextState)
			{//狀態切換
				//清除Models資料
				modelConfig.resetModels();
				//清除image資料
				imageConfig.resetDrawables();
				state=nextState;
				coreModel=modelConfig.getModel(state);
				coreModel.init();
			}
			else
			{
				coreModel=modelConfig.getModel(state);
			}
			coreModel.update();
			//繪圖
			try 
			{
				canvas=null;
				//繪圖
				canvas=holder.lockCanvas();
				Log.v("TEST", "width:"+canvas.getWidth()+" height:"+canvas.getHeight());
				Consts.SCREEN_WIDTH=canvas.getWidth();
				Consts.SCREEN_HEIGHT=canvas.getHeight();
				translateX=(canvas.getWidth()-Consts.SCREEN_WIDTH)/2;
				translateY=(canvas.getHeight()-Consts.SCREEN_HEIGHT)/2;
				//置中
				canvas.translate(translateX, translateY);
				//畫預設背景
				DrawUtil.drawRect(canvas,0, 0, 
						Consts.SCREEN_WIDTH, 
						Consts.SCREEN_HEIGHT, 
						Consts.BACKGROUND_COLOR[0], 
						Consts.BACKGROUND_COLOR[1],
						Consts.BACKGROUND_COLOR[2], 
						Consts.BACKGROUND_COLOR[3]);
				
				coreModel.drawView(canvas);
			}
			catch(Exception e)
			{
				Log.v(Consts.TAG, "run e:"+e);
			}
			finally
			{
				if(canvas!=null)
				{
					holder.unlockCanvasAndPost(canvas);
				}
				
			}
			
		}
	}
	public ImageConfig getImageConfig()
	{
		return imageConfig;
	}

	public MusicConfig getMusciConfig()
	{
		return musciConfig;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		Log.v(Consts.TAG, "onKeyDown keyCode"+keyCode+" KeyEvent"+event);
		CoreModel coreModel=modelConfig.getModel(state);
		coreModel.onKeyDown(keyCode);
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		Log.v(Consts.TAG, "onKeyUp keyCode"+keyCode+" KeyEvent"+event);
		CoreModel coreModel=modelConfig.getModel(state);
		coreModel.onKeyUp(keyCode);
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		CoreModel coreModel=modelConfig.getModel(state);
		int x=(int)event.getX()-translateX;
		int y=(int)event.getY()-translateY;
		Log.v(Consts.TAG, "onTouchEvent:"+event+" x:"+x+" y:"+y);
		coreModel.onTouchEvent(x,y, event);
		return super.onTouchEvent(event);
	}

	public int getNextState()
	{
		return nextState;
	}

	public void setNextState(int nextState)
	{
		this.nextState = nextState;
	}

	public GameBean getGameBean()
	{
		return gameBean;
	}
	
}

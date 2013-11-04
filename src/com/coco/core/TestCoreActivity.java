package com.coco.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.coco.marumaru.model.MenuModel;

public class TestCoreActivity extends Activity
{
//	private CoreController coreController;
	private static TestCoreActivity instance;
	/** Called when the activity is first created. */
	public TestCoreActivity()
	{
		instance=this;
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		Log.v(Consts.TAG, "onCreate");
		super.onCreate(savedInstanceState);
		//關閉視窗標題
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_test_core);
		//取得主要螢幕配置FrameLayout靠上左
//		coreController=(CoreController)findViewById(R.id.core);
		Log.v(Consts.TAG, "onCreate finish!");
	}
	
	public static TestCoreActivity getInstance()
	{
		return instance;
	}
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_core, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.action_settings:
			Log.v(Consts.TAG, "MenuModel start");
			Intent intent=new Intent();
			intent.setClass(this,MenuModel.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(Consts.TAG, "onPause");
	}
	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();
	}
}
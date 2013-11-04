package com.coco.core;

import com.coco.marumaru.model.MenuModel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TestCore extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_core);
	}

	@Override
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
	

}

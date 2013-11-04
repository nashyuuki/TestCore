package com.coco.marumaru.model;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.coco.core.Consts;
import com.coco.core.R;
import com.coco.marumaru.been.GameBean;

public class MenuModel extends Activity implements OnClickListener ,SeekBar.OnSeekBarChangeListener,RatingBar.OnRatingBarChangeListener
{
	private Button backButton;
	private GameBean gameBean;
	private SeekBar rotateBar;
	private TextView rotateText;
	private SeekBar waveBar;
	private TextView waveText;
	private SeekBar crunodeBar;
	private TextView crunodeText;
	private SeekBar xBar;
	private TextView xText;
	private SeekBar yBar;
	private TextView yText;
	private RatingBar scaleBar;
	private TextView scaleText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.v(Consts.TAG, "MenuModel start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		gameBean=new GameBean(this.getBaseContext());
		gameBean.restoreGameBean();
		
		backButton=(Button)findViewById(R.id.button);
		backButton.setOnClickListener(this);
		
		
		rotateBar=(SeekBar)findViewById(R.id.rotateSeek);
		waveBar=(SeekBar)findViewById(R.id.waveSeek);
		crunodeBar=(SeekBar)findViewById(R.id.crunodeSeek);
		xBar=(SeekBar)findViewById(R.id.xSeek);
		yBar=(SeekBar)findViewById(R.id.ySeek);
		rotateText=(TextView)findViewById(R.id.rotateText);
		waveText=(TextView)findViewById(R.id.waveText);
		crunodeText=(TextView)findViewById(R.id.crunodeText);
		xText=(TextView)findViewById(R.id.xText);
		yText=(TextView)findViewById(R.id.yText);
		scaleBar=(RatingBar)findViewById(R.id.scaleBar);
		scaleText=(TextView)findViewById(R.id.scaleText);
		
		int rotate=gameBean.getRotate();
		int wave=gameBean.getWave();
		int crunode=gameBean.getCrunode();
		int x=gameBean.getMaru_x();
		int y=gameBean.getMaru_y();
		float scale=gameBean.getScale();
		rotateBar.setProgress(rotate);
		waveBar.setProgress(wave);
		crunodeBar.setProgress(crunode);
		xBar.setProgress(x);
		yBar.setProgress(y);
		scaleBar.setRating(scale);
		
		rotateText.setText(getString(R.string.rotate)+rotate);
		waveText.setText(getString(R.string.wave)+wave);
		crunodeText.setText(getString(R.string.crunode)+crunode);
		xText.setText(getString(R.string.xposition)+x);
		yText.setText(getString(R.string.yposition)+y);
		scaleText.setText(getString(R.string.scale)+scale);
		
		rotateBar.setOnSeekBarChangeListener(this);
		waveBar.setOnSeekBarChangeListener(this);
		crunodeBar.setOnSeekBarChangeListener(this);
		xBar.setOnSeekBarChangeListener(this);
		yBar.setOnSeekBarChangeListener(this);
		scaleBar.setOnRatingBarChangeListener(this);
	
	}
	
	@Override
	public void onClick(View v)
	{
		MenuModel.this.finish();
	}
	@Override
	protected void onPause()
	{
		super.onPause();
		
		//¦s­È
		int rotate=rotateBar.getProgress();
		int wave=waveBar.getProgress();
		int crunode=crunodeBar.getProgress();
		int x=xBar.getProgress();
		int y=yBar.getProgress();
		float scale=scaleBar.getRating();
		
		gameBean.setRotate(rotate);
		gameBean.setWave(wave);
		gameBean.setCrunode(crunode);
		gameBean.setMaru_x(x);
		gameBean.setMaru_y(y);
		gameBean.setScale(scale);
		gameBean.storeGameBean();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser)
	{
		int rotate=rotateBar.getProgress();
		int wave=waveBar.getProgress();
		int crunode=crunodeBar.getProgress();
		int x=xBar.getProgress();
		int y=yBar.getProgress();
		rotateText.setText(getString(R.string.rotate)+rotate);
		waveText.setText(getString(R.string.wave)+wave);
		crunodeText.setText(getString(R.string.crunode)+crunode);
		xText.setText(getString(R.string.xposition)+x);
		yText.setText(getString(R.string.yposition)+y);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser)
	{
		
		scaleText.setText(getString(R.string.scale)+rating);
		scaleBar.setRating(rating);
	}
	
}

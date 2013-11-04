package com.coco.marumaru.config;

import java.util.HashMap;
import java.util.Map;

import com.coco.core.CoreController;
import com.coco.core.CoreModel;
import com.coco.marumaru.model.GameModel;
import com.coco.marumaru.model.LogoModel;

public class ModelConfig
{
	public static final int LOGO=0;
	public static final int GAME=1;
	public static final int INIT_STATE=LOGO;
	private Map<String,CoreModel> models;
	private CoreController coreController;
	public ModelConfig(CoreController coreController)
	{
		this.coreController=coreController;
		models=new HashMap<String, CoreModel>();
	}
	public CoreModel getModel(int state)
	{
		CoreModel model= models.get(""+state);
		if(model==null)
		{
			switch (state)
			{
				case LOGO: 
					model=new LogoModel(coreController);
					break;
				case GAME:
					model=new GameModel(coreController);
					break;
				default:
					model=new LogoModel(coreController);
					break;
			}
			models.put(""+state, model);
		}
		return model;
	}
	public void resetModels()
	{
		models.clear();
	}
}

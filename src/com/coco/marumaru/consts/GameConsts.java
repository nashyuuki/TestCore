package com.coco.marumaru.consts;

import com.coco.core.Consts;
import com.coco.marumaru.config.ImageConfig;

public class GameConsts
{
	public static final int LOGO_SEC=30;
	public static final int[] FINGER_POSITION={-13,-30};
	public static final int[] FINGER_COLLISION={9,22,19,30};
	public static final int[] BOXER_POSITION={-19,-23};
	public static final int[] BOXER_COLLISION={5,9,35,38};
	public static final int[] FUNCTION_POSITION={Consts.SCREEN_WIDTH-120,Consts.SCREEN_HEIGHT-120};//240,295
	public static final int[] FUNCTION_COLLISION={0,0,18,22};
	public static final int[] BOXER_SCRIPT=
	{
		ImageConfig.BOXER01,
		ImageConfig.BOXER02
	};
	public static final int[] MARU=
	{ImageConfig.MARU01,
	 ImageConfig.MARU01,
	 ImageConfig.MARU02,
	 ImageConfig.MARU03,
	 ImageConfig.MARU03,
	 ImageConfig.MARU04,
	 ImageConfig.MARU05};
	public static final int[] MARU_BORN=
	{
		ImageConfig.MARU01_1,
		ImageConfig.MARU01_1,
		ImageConfig.MARU02_1,
		ImageConfig.MARU03_1,
		ImageConfig.MARU03_1,
		ImageConfig.MARU04_1,
		ImageConfig.MARU05_1,
	};
	public static final int[] MARU_BURST=
	{
		ImageConfig.MARU01_2,
		ImageConfig.MARU01_2,
		ImageConfig.MARU02_2,
		ImageConfig.MARU03_2,
		ImageConfig.MARU03_2,
		ImageConfig.MARU04_2,
		ImageConfig.MARU05_2,
	};
	public static final long MARU_BURST_TIME=100;
	public static final int[] MARU_BORN_SCRIPT={0,1};
	public static final int[] MARU_MOVE_SCRIPT={0,1};
	public static final int[] MARU_BURST_SCRIPT01={0,1,2,3,4};
	public static final int[] MARU_BURST_SCRIPT02={5,6,7,8,9};
	public static final int[] MARU_COLLISION={0,0,24,24};
	public static final int[] MARU_BURST_POSITION={-20,-20};
	public static final int[] POINT_POSITION={Consts.SCREEN_WIDTH-12,0};
	public static final int[] MARU_POINT_POSITION={12,5};//置中
	public static final int[] MARU_POINT01_POSITION={12,7};//置中
	public static final int[] MARU_ARROW_POSITION={12,6};//置中
	public static final int SPEED_TIME=50;
	public static final int SCALE_TIME=50;
	public static final float SCALE_BIG=(float)1.5;
	public static final float SCALE_SMALL=(float)0.5;
	public static final float SCALE_NORMAL=(float)1;
	public static final int CRUNODE_NORMAL=16;//曲線結點
	public static final int SPEED_UP_TIME=50;
	public static final int SPEED_NORMAL_TIME=100;
	public static final int SPEED_DOWN_TIME=150;
	
	public static final int[] BORN_POSITION01={0,0,Consts.SCREEN_WIDTH/2,0};
	public static final int[] BORN_POSITION02={Consts.SCREEN_WIDTH/2,0,Consts.SCREEN_WIDTH,0};
	public static final int[] BORN_POSITION03={0,0,0,Consts.SCREEN_HEIGHT/2};
	public static final int[] BORN_POSITION04={0,Consts.SCREEN_HEIGHT/2,0,Consts.SCREEN_HEIGHT};
	public static final int[] BORN_POSITION05={Consts.SCREEN_WIDTH,0,Consts.SCREEN_WIDTH,Consts.SCREEN_HEIGHT/2};
	public static final int[] BORN_POSITION06={Consts.SCREEN_WIDTH,Consts.SCREEN_HEIGHT/2,Consts.SCREEN_WIDTH,Consts.SCREEN_HEIGHT};
	public static final int[] BORN_POSITION07={0,Consts.SCREEN_HEIGHT,Consts.SCREEN_WIDTH/2,Consts.SCREEN_HEIGHT};
	public static final int[] BORN_POSITION08={Consts.SCREEN_WIDTH/2,Consts.SCREEN_HEIGHT,Consts.SCREEN_WIDTH,Consts.SCREEN_HEIGHT};
	public static final int[] POINT_UP_SCRIPT01={10,20,30,40};
	
	public static final int[] POINT_DOWN_SCRIPT01={5,15,25,35,45};
	
	public static final int[] MARU_KIND_RANGE01={10,10,10,10,10,10,40};
	public static final int[] MARU_KIND_RANGE02={0,0,0,0,0,0,100};
	
	public static final int[][] BORN_POSITION={
		BORN_POSITION01,//45
		BORN_POSITION02,//135
		BORN_POSITION03,//45
		BORN_POSITION04,//315
		BORN_POSITION05,//135
		BORN_POSITION06,//225
		BORN_POSITION07,//315
		BORN_POSITION08,};//225
	public static final int[][] MARU_KIND_RANGE={MARU_KIND_RANGE01,MARU_KIND_RANGE02};
	public static final int[][] POINT_UP_SCRIPT={POINT_UP_SCRIPT01};
	public static final int[][] POINT_DOWN_SCRIPT={POINT_DOWN_SCRIPT01};
	//0=旋轉角度  1=波形放大  2=出現頻率 3=BORN_POSITION 4=MARU_KIND_RANGE 5=POINT_UP 6=POINT_DOWN 
	public static final int[] LINE_SCRIPT01={45,10,20,0,0,0,0};
	public static final int[] LINE_SCRIPT02={135,10,10,1,0,0,0};
	public static final int[] LINE_SCRIPT03={315,10,5,3,1,0,0};
	public static final int[] LINE_SCRIPT04={135,10,5,1,1,0,0};
	public static final int[][] BARRIER_SCRIPT01={LINE_SCRIPT01,LINE_SCRIPT02};
}

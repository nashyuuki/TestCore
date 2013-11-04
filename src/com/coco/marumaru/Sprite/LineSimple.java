package com.coco.marumaru.Sprite;

public class LineSimple
{
	private int crunode=8;//�@��²�ӹB�ʭn�]�X�I
	private int rotate=120;//�X��
	private int wave=20;//�X��
	private double[][] xyVector=new double[2][];
	public LineSimple(int rotate,int wave,int crunode)
	{
		this.rotate=rotate;
		this.wave=wave;
		this.crunode=crunode;
		update();
	}
	public void update()
	{
		double[] xArray=new double[crunode];
		double[] yArray=new double[crunode];
		double add=2*Math.PI/crunode;
		double x=0;
		//��Xx,y�b²�ӹB�ʤ�����
		for(int i=0;i<crunode;i++)
		{
			x=x+add;
			double y=Math.sin(x);
			xArray[i]=x;
			yArray[i]=y;
		}
		double[] xVector=new double[crunode];
		double[] yVector=new double[crunode];
		//��X�V�q
		for(int i=0;i<crunode;i++)
		{
			if(i>0)
			{
				xVector[i]=xArray[i]-xArray[i-1];
				yVector[i]=yArray[i]-yArray[i-1];
			}
			else
			{
				xVector[i]=xArray[i];
				yVector[i]=yArray[i];
			}
		}
		//����
		xyVector[0]=new double[crunode];
		xyVector[1]=new double[crunode];
		double rad=rotate*Math.PI/180;//�|
		for(int i=0;i<crunode;i++)
		{
			double x1=xVector[i];
			double y1=yVector[i];
			xyVector[0][i]=(x1*Math.cos(rad)-y1*Math.sin(rad))*wave;
			xyVector[1][i]=(y1*Math.cos(rad)+x1*Math.sin(rad))*wave;
		}
	}
	public double[][] getXyVector()
	{
		return xyVector;
	}
	public void setXyVector(double[][] xyVector)
	{
		this.xyVector = xyVector;
	}
	
}

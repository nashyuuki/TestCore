package com.coco.core.utils;

public class NumberUtil
{
	public static int[] transformPoint(int point)
	{
		int length = 1;
		int s = point;
		while (s >= 10)
		{
			s = s / 10;
			length++;
		}
		int[] number = new int[length];
		int n = 0;
		int p = point;
		if (p > 0)
		{
			n = p % 10;
			number[0] = n;
			for (int i = 1; i < length; i++)
			{
				if (p >= 10)
				{
					p = (p - n) / 10;
					n = p % 10;
					number[i] = n;
				} else
				{
					number[i] = 0;
				}
			}
		} else
		{
			for (int i = 0; i < length; i++)
			{
				number[i] = 0;
			}
		}
		return number;
	}

	public static int[] transformArrary(int point, int length)
	{ // 分數圖檔設定
		int n = 0;
		int p = point;
		int[] number = new int[length];
		if (p > 0)
		{
			n = p % 10;
			number[0] = n;
			for (int i = 1; i < length; i++)
			{
				if (p >= 10)
				{
					p = (p - n) / 10;
					n = p % 10;
					number[i] = n;
				} else
				{
					number[i] = 0;
				}
			}
		} else
		{
			for (int i = 0; i < length; i++)
			{
				number[i] = 0;
			}
		}
		return number;
	}
}

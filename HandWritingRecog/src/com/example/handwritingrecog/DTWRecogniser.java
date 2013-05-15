package com.example.handwritingrecog;
import android.util.FloatMath;

public class DTWRecogniser {
	
	//modified logistic weight function params
	static float w_max = 1.0f;
	static float g = 0.25f;
	static int mc;

	public static double DTWDistance(float[] input,float[] meanVector)
	{
		int n=(input.length/2);
		int m=(meanVector.length/2);
		float[][] Dtw=new float[n+1][m+1];
		for(int i=1;i<=m;i++)
		{
			Dtw[0][i]=Float.MAX_VALUE;
		}
		for(int i=1;i<=n;i++)
		{
			Dtw[i][0]=Float.MAX_VALUE;
		}
		Dtw[0][0]=0;
		for(int i=1;i<=n;i++)
			for(int j=1;j<=m;j++)
			{
				float cost=calculateDist(input[2*(i-1)],meanVector[2*(j-1)],input[2*(i-1)+1],meanVector[2*(j-1)+1]);
				Dtw[i][j]=cost + minimum(Dtw[i-1][j],Dtw[i][j-1],Dtw[i-1][j-1]);
			}
		
		return Dtw[n][m];
	}
	
	public static float WeightedDTWDistance(float[] input,float[] meanVector)
	{
		int n=(input.length/2);
		int m=(meanVector.length/2);
		
		mc = n/2;
		
		float[][] Dtw=new float[n+1][m+1];
		for(int i=1;i<=m;i++)
		{
			Dtw[0][i]=Float.MAX_VALUE;
		}
		for(int i=1;i<=n;i++)
		{
			Dtw[i][0]=Float.MAX_VALUE;
		}
		Dtw[0][0]=0;
		for(int i=1;i<=n;i++)
			for(int j=1;j<=m;j++)
			{
				float cost= weight(Math.abs(i-j)) * calculateDist(input[2*(i-1)],meanVector[2*(j-1)],input[2*(i-1)+1],meanVector[2*(j-1)+1]);
				Dtw[i][j]=cost + minimum(Dtw[i-1][j],Dtw[i][j-1],Dtw[i-1][j-1]);
			}
		
		return Dtw[n][m];
	}
	
	public static float calculateDist(float x1, float x2, float y1,float y2){
		return ((y2-y1)*(y2-y1))+((x2-x1)*(x2-x1));//calculate the euclidean distance between each pair of consecutive points
	}
	
	public static float minimum(float a,float b,float c)
	{
		float d=Math.min(a, b);
		return Math.min(d, c);
	}
	
	double exp_1(double x) {
		/**
		 * Fast approximation of exp(x)
		 *  exp(x) = lim n -> INF (1 + 1/n)^n
		 *  
		 */
		  x = 1d + x / 256d;
		  x *= x; x *= x; x *= x; x *= x;
		  x *= x; x *= x; x *= x; x *= x;
		  return x;
		}
	
	public static float weight(int i)
	{
		//FloatMath
		return (float) (w_max/(1 +  FloatMath.exp(-g*(i-mc))));
	}
}

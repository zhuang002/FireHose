import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static int h,k;
	static int[] houses;
	static int circ=1000000;
	static HashMap<Parameter, Integer> cache;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		h=Integer.parseInt(reader.readLine());
		houses = new int[h];
		for (int i=0;i<h;i++) {
			houses[i] = Integer.parseInt(reader.readLine());
		}
		k=Integer.parseInt(reader.readLine());
		
		Arrays.sort(houses);
		
		int hoseLow=0;
		int hoseHigh=circ/2;
		int hoseTry = (hoseHigh+hoseLow)/2;
		int success=0;
		while (true) {
			if (canCover(hoseTry)) {
				hoseHigh=hoseTry-1;
				success=hoseTry;
			} else {
				hoseLow=hoseTry+1;
				
			}
			if (hoseHigh-hoseLow<0) {
				break;
			} else {
				hoseTry=(hoseHigh+hoseLow)/2;
			}
		}
		System.out.println(success);
	}
	
	private static boolean canCover(int hoseTry) {
		// TODO Auto-generated method stub
		for (int start=0;start<h;start++) {
			int end = (start-1+h)%h;
			if (canCover(start,end,k,hoseTry))
				return true;
		}
		return false;
	}

	private static boolean canCover(int start, int end, int nHose, int hoseTry) {
		// TODO Auto-generated method stub
		int high=end;
		int low=start;
		if (high<low) high+=h;
		for (int i=0;i<nHose;i++) {
			int middle=findMiddle(low,high,hoseTry);
			low=middle+1;
			if (low>high) 
				return true;
		}
		return false;
	}

	private static int findMiddle(int low, int high, int hoseTry) {
		// TODO Auto-generated method stub
		
		int hoseCover = hoseTry*2;
		int sa=houses[low%h];
		int ea=houses[high%h];
		int distance = (ea-sa+circ)%circ;
		if (distance<=hoseCover) return high;

		
		while (true) {
			int middle = (high+low)/2;

			int ma=houses[middle%h];
			distance = (ma-sa+circ)%circ;

			if (distance==hoseCover) {
				return middle%h;
			} else if (distance>hoseCover) {
				high = middle-1;
				if (low>high) 
					return (low-1)%h;
			} else {
				low=middle+1;
				if (low>high) 
					return middle%h;
			}
		}
		
	}
	
}

class Parameter {
	int start,end,n;
	public Parameter(int s,int e, int n) {
		start=s;
		end=e;
		this.n=n;
	}
}

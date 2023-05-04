import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static int h,k;
	static int[] houses;
	static int circ=1000000;
	static HashMap<Parameter, Integer> cache=new HashMap<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		h=sc.nextInt();
		houses = new int[h];
		for (int i=0;i<h;i++) {
			houses[i] = sc.nextInt();
		}
		k=sc.nextInt();
		
		Arrays.sort(houses);
		
		int minHose = circ;
		for (int start=0;start<h;start++) {
			int hose = getMinHose(start, (start+h-1)%h, k);
			if (hose<minHose)
				minHose=hose;
		}
		
		System.out.println(minHose);
		
		
	}
	
	private static int getMinHose(int start, int end, int nHose) {
		// TODO Auto-generated method stub
		int minHose = circ;
		int sa = houses[start];
		int ea = houses[end];
		if (ea<sa) ea+=circ;
		
		int maxSeg = (ea-sa+1)/nHose;
		
		if (nHose==1) return (ea-sa+1)/2; 
		if (start==end) return 0;
		if ((end+h-start)%h+1<=nHose)
			return 0;

		
		Parameter key = new Parameter(start,end,nHose);
		if (cache.containsKey(key)) return cache.get(key);
		
		int distance = (end+h-start)%h;
		for (int i=0;i<distance;i++) {
			int middle = (start+i)%h;
			int ma = houses[middle];
			if (ma<sa) ma+=circ;
			int hose1 = (ma-sa+1)/2;
			if (hose1>maxSeg) break;
			if (hose1>=minHose) break;
			int hose2 = getMinHose((middle+1)%h,end,nHose-1);
			int hose;
			if (hose1>hose2) {
				hose=hose1;
			} else {
				hose=hose2;
			}
			if (hose<minHose) {
				minHose=hose;
			}
		}
		
		cache.put(key, minHose);
		return minHose;
	}

}

class Parameter {
	int start,end,nHose;
	public Parameter(int s,int e,int n) {
		start = s;
		end = e;
		nHose = n;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + nHose;
		result = prime * result + start;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameter other = (Parameter) obj;
		if (end != other.end)
			return false;
		if (nHose != other.nHose)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	
	
}

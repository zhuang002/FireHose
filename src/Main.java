import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static int h,k;
	static int[] houses;
	static int circ=1000000;
	static int[][][] cache;
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

		cache = new int[h][h][k+1];
		
		for (int distance=0;distance<h;distance++) {
			for (int start=0;start<h;start++) {
				int end = (start+distance)%h;
				for (int nHouse=1;nHouse<=k;nHouse++) {
					if (distance+1<=nHouse) {
						cache[start][end][nHouse]=0;
						continue;
					}
					
					int sa=houses[start];
					int ea=houses[end];
					if (ea<sa) ea+=circ;
					int maxSeg = (ea-sa+1)/k;
					if (nHouse==1) {
						cache[start][end][nHouse]=(ea-sa+1)/2;
						continue;
					}
					if (start==end) {
						cache[start][end][nHouse]=0;
						continue;
					}

					int tmpMinHose=circ;
					for (int i=0;i<distance;i++) {
						int middle=(start+i)%h;
						int ma=houses[middle];
						if (ma<sa) ma+=circ;
						int hose1 = (ma-sa+1)/2;
						if (hose1>maxSeg) break;
						if (hose1>tmpMinHose) break;
						int hose2 = cache[(middle+1)%h][end][nHouse-1];
						if (hose1<hose2) hose1=hose2;
						if (tmpMinHose>hose1) {
							tmpMinHose=hose1;
						}
					}
					cache[start][end][nHouse] = tmpMinHose;
				}

			}
		}
		int minHose=circ;
		for (int start=0;start<h;start++) {
			int end = (start-1+h)%h;
			if (minHose>cache[start][end][k]) {
				minHose = cache[start][end][k];
			}
		}
		System.out.println(minHose);
		
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

/*
 * 
6
0
156790
336070
410838
456789
700000
2
 */

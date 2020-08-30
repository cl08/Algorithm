package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DisjointSetTest {

	private static int[] parents;

	private static void makeSet() {
		Arrays.fill(parents, -1);
	}
	private static int findSet(int a) {
		if(parents[a]<0) return a;
		return parents[a] = findSet(parents[a]); // path compression
	}
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot!=bRoot) {
			parents[bRoot] = aRoot;
			return true; // 합치기 성공
		}
		return false; // 이미 같은 집합
		
	}
	public static void main(String[] args) throws NumberFormatException,IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int count = Integer.parseInt(br.readLine());
		parents = new int[N];
		makeSet();
		while (count-->0){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(union(a,b)) {
				System.out.println("union 성공 : 서로 다른집합을 하나로 만듦");
			}
			else {
				System.out.println("uniob 실패 : 이미 같은 집합");
			}
		}
		System.out.println(Arrays.toString(parents));
		System.out.println(findSet(5));
	}

}

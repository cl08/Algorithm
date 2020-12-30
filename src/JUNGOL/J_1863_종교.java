package JUNGOL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class J_1863_종교 {
	static int[] arr;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken()) + 1;
		arr = new int[n];
		int count = 0;
		int m = Integer.parseInt(st.nextToken());
		Arrays.fill(arr, -1);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		for (int i = 1; i < n; i++) {
			if (arr[i] < 0)
				count++;
		}
		System.out.println(count);
	}

	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		else {
			arr[bRoot] = aRoot;
			return true;
		}
	}

	public static int findSet(int a) {
		if (arr[a] < 0) {
			return a;
		}
		return arr[a] = findSet(arr[a]);
	}
}

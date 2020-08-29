package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G5_15686_치킨배달_김유창 {
	static int N, M, n, result;
	static int[] number, pick;
	static ArrayList<int[]> house;
	static ArrayList<int[]> chicken;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		house = new ArrayList<int[]>();
		chicken = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					house.add(new int[] { i, j });
				if (map[i][j] == 2)
					chicken.add(new int[] { i, j });
			}
		}
		result = Integer.MAX_VALUE;
		n = chicken.size();
		number = new int[n];
		for (int i = 0; i < n; i++) {
			number[i] = i;
		}
		pick = new int[M];
		combination(0, 0);
		System.out.println(result);
	}

	public static void combination(int index, int count) {
		if (count == M) {
			int size = house.size();
			int sum = 0;
			for (int i = 0; i < size; i++) {
				int min = Integer.MAX_VALUE;
				int[] temp1 = house.get(i);
				for (int j = 0; j < M; j++) {
					int[] temp2 = chicken.get(pick[j]);
					int dist = Math.abs(temp1[0] - temp2[0]) + Math.abs(temp1[1] - temp2[1]);
					if (dist < min)
						min = dist;
				}
				sum = sum + min;
				if (result < sum)
					return;
			}
			if (sum < result)
				result = sum;
			return;
		}
		for (int i = index; i < n; i++) {
			pick[count] = number[i];
			combination(i + 1, count + 1);
		}
	}
}

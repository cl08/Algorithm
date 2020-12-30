package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_G4_17140_이차원배열과연산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		int rlength = 3;
		int clength = 3;
		while (arr[r][c] != k && result <= 100) {
			result++;
			// 행의개수 >= 열의개수이면 r연산
			if (rlength >= clength) {
				int max = 0;
				int templength = clength;
				for (int i = 0; i < rlength; i++) {
					int[] count = new int[101];
					int j = 0;
					for (j = 0; j < templength; j++) {
						count[arr[i][j]]++;
					}
					PriorityQueue<Point> q = new PriorityQueue<Point>();
					// pq 에 넣음
					for (j = 1; j <= 100; j++) {
						if (count[j] != 0)
							q.offer(new Point(j, count[j]));
					}
					// 행 초기화
					Arrays.fill(arr[i], 0);
					// 열 크기 측정
					if (max < q.size() * 2) {
						clength = q.size() * 2;
						max = q.size() * 2;
					}
					// 꺼내면서 갱신
					j = 0;
					while (!q.isEmpty() && j < 100) {
						Point temp = q.poll();
						arr[i][j] = temp.x;
						arr[i][j + 1] = temp.y;
						j = j + 2;
					}
				}
			}
			// 행의개수 < 열의 개수이면 c연산
			else {
				int max = 0;
				int templength = rlength;
				for (int i = 0; i < clength; i++) {
					int[] count = new int[101];
					int j = 0;
					for (j = 0; j < templength; j++) {
						count[arr[j][i]]++;
					}
					PriorityQueue<Point> q = new PriorityQueue<Point>();
					// pq 에 넣음
					for (j = 1; j <= 100; j++) {
						if (count[j] != 0)
							q.offer(new Point(j, count[j]));
					}
					// 행 초기화
					for (j = 0; j < 100; j++) {
						arr[j][i] = 0;
					}
					// 행 크기 측정
					if (max < q.size() * 2) {
						rlength = q.size() * 2;
						max = q.size() * 2;
					}
					// 꺼내면서 갱신
					j = 0;
					while (!q.isEmpty() && j < 100) {
						Point temp = q.poll();
						arr[j][i] = temp.x;
						arr[j + 1][i] = temp.y;
						j = j + 2;
					}
				}
			}
		}
		if (result > 100)
			result = -1;
		System.out.println(result);
	}

	public static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.y == o.y)
				return this.x > o.x ? 1 : -1;
			return this.y > o.y ? 1 : -1;
		}
	}
}

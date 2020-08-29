package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_B14500_테트로미노_김유창 {
	static int[][] dx = { { 0, 1, 1 }, { 0, 0, 0 }, { 1, 2, 3 }, { 0, 1, 0 }, { 0, -1, 0 }, { -1, 0, 1 }, { -1, 0, 1 },
			{ -1, 0, 1 }, { -1, 0, 1 }, { 0, 1, 1 }, { 0, 1, 1 }, { 0, 0, 1 }, { 0, 0, 1 }, { -1, -2, 0 },
			{ -1, -2, 0 }, { -1, 0, 0 }, { -1, 0, 0 }, { 0, 1, 2 }, { 0, 1, 2 } };
	static int[][] dy = { { 1, 0, 1 }, { 1, 2, 3 }, { 0, 0, 0 }, { -1, 0, 1 }, { -1, 0, 1 }, { 0, 1, 0 }, { 0, -1, 0 },
			{ 0, -1, -1 }, { 0, 1, 1 }, { 1, 0, -1 }, { -1, 0, 1 }, { 1, 2, 0 }, { -1, -2, 0 }, { 0, 0, 1 },
			{ 0, 0, -1 }, { 0, -1, -2 }, { 0, 1, 2 }, { -1, 0, 0 }, { 1, 0, 0 } };
	static int N, M, max = 0;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tetro(i, j);
			}
		}
		System.out.println(max);
	}

	public static void tetro(int x, int y) {
		int tempX, tempY, sum;
		for (int i = 0; i < 19; i++) {
			sum = arr[x][y];
			for (int j = 0; j < 3; j++) {
				tempX = x + dx[i][j];
				tempY = y + dy[i][j];
				if (tempX >= 0 && tempX < N && tempY >= 0 && tempY < M)
					sum = sum + arr[tempX][tempY];
			}
			if (max < sum)
				max = sum;
		}
	}
}

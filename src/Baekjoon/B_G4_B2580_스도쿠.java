package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G4_B2580_스도쿠 {
	static int[][] arr;
	static boolean flag = false;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9][9];
		StringTokenizer st;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0)
					count++;
			}
		}
		dfs();
		print();
	}

	public static boolean insert(int x, int y, int k) {
		// 가로줄 검사
		for (int i = 0; i < 9; i++) {
			if (arr[x][i] == k)
				return false;
		}

		// 세로줄 검사
		for (int i = 0; i < 9; i++) {
			if (arr[i][y] == k)
				return false;
		}

		// 3x3 검사
		int startX, endX, startY, endY;
		if (0 <= x && x <= 2) {
			startX = 0;
			endX = 2;
		} else if (3 <= x && x <= 5) {
			startX = 3;
			endX = 5;
		} else {
			startX = 6;
			endX = 8;
		}
		if (0 <= y && y <= 2) {
			startY = 0;
			endY = 2;
		} else if (3 <= y && y <= 5) {
			startY = 3;
			endY = 5;
		} else {
			startY = 6;
			endY = 8;
		}
		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				if (arr[i][j] == k)
					return false;
			}
		}
		return true;
	}

	public static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void dfs() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arr[i][j] == 0) {
					for (int k = 1; k < 10; k++) {
						if (insert(i, j, k)) {
							arr[i][j] = k;
							count--;
							dfs();
							if (count == 0)
								return;
							arr[i][j] = 0;
							count++;
						}
					}
					return;
				}
			}
		}
	}
}
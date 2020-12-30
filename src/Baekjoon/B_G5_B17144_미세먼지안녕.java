package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G5_B17144_미세먼지안녕 {
	static int[][] arr1, arr2;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		arr1 = new int[R][C];
		arr2 = new int[R][C];
		int result;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				arr1[i][j] = Integer.parseInt(st.nextToken());
				arr2[i][j] = arr1[i][j];
			}
		}
		for (int i = 0; i < T; i++) {
			// 확산
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if (arr1[j][k] > 0) {
						for (int l = 0; l < 4; l++) {
							int x = j + dx[l];
							int y = k + dy[l];
							if (x >= 0 && x < R && y >= 0 && y < C && arr1[x][y] != -1) {
								arr2[x][y] = arr2[x][y] + arr1[j][k] / 5;
								arr2[j][k] = arr2[j][k] - arr1[j][k] / 5;
							}
						}
					}
				}
			}
			// 공기청정
			boolean flag = false;
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					// 위쪽
					if (arr2[j][k] == -1 && flag == false) {
						flag = true;
						int x = j;
						int y = k;
						while (x >= 0) {
							if (x == 0)
								arr2[x][y] = arr2[x][y + 1];
							else
								arr2[x][y] = arr2[x - 1][y];
							x--;
						}
						x++;
						while (y < C) {
							if (y == C - 1)
								arr2[x][y] = arr2[x + 1][y];
							else
								arr2[x][y] = arr2[x][y + 1];
							y++;
						}
						y--;
						while (x <= j) {
							if (x == j + 1)
								arr2[x][y] = arr2[x][y - 1];
							else
								arr2[x][y] = arr2[x + 1][y];
							x++;
						}
						x--;
						while (y >= 0) {
							if (y == 0)
								arr2[x][y] = -1;
							else
								arr2[x][y] = arr2[x][y - 1];
							y--;
						}
						y++;
						arr2[j][k + 1] = 0;
					}
					// 아래쪽
					else if (arr2[j][k] == -1 && flag == true) {
						int x = j;
						int y = k;
						while (x < R) {
							if (x == R - 1)
								arr2[x][y] = arr2[x][y + 1];
							else
								arr2[x][y] = arr2[x + 1][y];
							x++;
						}
						x--;
						while (y < C) {
							if (y == C - 1)
								arr2[x][y] = arr2[x - 1][y];
							else
								arr2[x][y] = arr2[x][y + 1];
							y++;
						}
						y--;
						while (x >= j) {
							if (x == j)
								arr2[x][y] = arr2[x][y - 1];
							else
								arr2[x][y] = arr2[x - 1][y];
							x--;
						}
						x++;

						while (y >= 0) {
							if (y == 0)
								arr2[x][y] = -1;
							else
								arr2[x][y] = arr2[x][y - 1];
							y--;
						}
						y++;
						arr2[j][k + 1] = 0;
					}
				}
			}
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					arr1[j][k] = arr2[j][k];
				}
			}
		}
		result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr2[i][j] > 0)
					result = result + arr1[i][j];
			}
		}
		System.out.println(result);
	}
}

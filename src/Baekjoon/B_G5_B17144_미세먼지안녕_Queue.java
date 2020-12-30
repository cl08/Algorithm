package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_G5_B17144_미세먼지안녕_Queue {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] arr = new int[R][C];
		Queue<int[]> q = new LinkedList<int[]>();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int x, y, result = 0;
		for (int t = 0; t < T; t++) {
			// 확산
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] > 0)
						q.offer(new int[] { i, j, arr[i][j] });
				}
			}
			while (!q.isEmpty()) {
				int[] temp = q.poll();
				for (int i = 0; i < 4; i++) {
					x = temp[0] + dx[i];
					y = temp[1] + dy[i];
					if (x >= 0 && x < R && y >= 0 && y < C && arr[x][y] != -1) {
						arr[x][y] += temp[2] / 5;
						arr[temp[0]][temp[1]] -= temp[2] / 5;
					}
				}
			}

			// 공기청정
			boolean flag = false;
			int k;
			for (int j = 0; j < R; j++) {
				// 위쪽
				k=0;
				if (arr[j][k] == -1 && flag == false) {
					flag = true;
					x = j;
					y = k;
					while (x >= 0) {
						if (x == 0)
							arr[x][y] = arr[x][y + 1];
						else
							arr[x][y] = arr[x - 1][y];
						x--;
					}
					x++;
					while (y < C) {
						if (y == C - 1)
							arr[x][y] = arr[x + 1][y];
						else
							arr[x][y] = arr[x][y + 1];
						y++;
					}
					y--;
					while (x <= j) {
						if (x == j + 1)
							arr[x][y] = arr[x][y - 1];
						else
							arr[x][y] = arr[x + 1][y];
						x++;
					}
					x--;
					while (y >= 0) {
						if (y == 0)
							arr[x][y] = -1;
						else
							arr[x][y] = arr[x][y - 1];
						y--;
					}
					y++;
					arr[j][k + 1] = 0;
				}
				// 아래쪽
				else if (arr[j][k] == -1 && flag == true) {
					x = j;
					y = k;
					while (x < R) {
						if (x == R - 1)
							arr[x][y] = arr[x][y + 1];
						else
							arr[x][y] = arr[x + 1][y];
						x++;
					}
					x--;
					while (y < C) {
						if (y == C - 1)
							arr[x][y] = arr[x - 1][y];
						else
							arr[x][y] = arr[x][y + 1];
						y++;
					}
					y--;
					while (x >= j) {
						if (x == j)
							arr[x][y] = arr[x][y - 1];
						else
							arr[x][y] = arr[x - 1][y];
						x--;
					}
					x++;

					while (y >= 0) {
						if (y == 0)
							arr[x][y] = -1;
						else
							arr[x][y] = arr[x][y - 1];
						y--;
					}
					y++;
					arr[j][k + 1] = 0;
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] > 0)
					result = result + arr[i][j];
			}
		}
		System.out.println(result);
	}
}

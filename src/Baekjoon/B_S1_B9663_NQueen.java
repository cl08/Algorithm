package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S1_B9663_NQueen {
	static int N, count, result = 0;
	static boolean[][] arr;
	static int[] dx = { -1, 1, 1, -1 };
	static int[] dy = { 1, 1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new boolean[N][N];
		count = N;
		dfs(0);
		System.out.println(result);
	}

	public static void dfs(int x) {
		for (int i = 0; i < N; i++) {
			if (check(x, i)) {
				arr[x][i] = true;
				count--;
				if (count == 0)
					result++;
				else
					dfs(x + 1);
				arr[x][i] = false;
				count++;
			}
		}
		return;
	}

	public static boolean check(int x, int y) {
		// 퀸을 놓을 수 있는지 확인
		if (arr[x][y])
			return false;

		// 가로
		for (int i = 0; i < N; i++) {
			if (arr[x][i])
				return false;
		}

		// 세로
		for (int i = 0; i < N; i++) {
			if (arr[i][y])
				return false;
		}

		// 대각선
		int curX, curY;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				curX = x + dx[j] * i;
				curY = y + dy[j] * i;
				if (curX >= 0 && curX < N && curY >= 0 && curY < N && arr[curX][curY])
					return false;
			}
		}
		return true;
	}
}

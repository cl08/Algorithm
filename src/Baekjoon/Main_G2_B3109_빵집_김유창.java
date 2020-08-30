package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G2_B3109_빵집_김유창 {
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	static char[][] arr;
	static boolean[][] visit;
	static int R = 0, C = 0, count = 0;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		String str;
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			arr[i] = str.toCharArray();
		}
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			dfs(i, 0);
			flag = false;
		}
		System.out.println(count);
	}
	public static void dfs(int x, int y) {
		if (flag)
			return;
		if (y == C - 1) {
			count++;
			flag = true;
		}
		visit[x][y] = true;
		int curX, curY;
		for (int i = 0; i < 3; i++) {
			curX = x + dx[i];
			curY = y + dy[i];
			if (curX >= 0 && curX < R && curY >= 0 && curY < C && !visit[curX][curY] && arr[curX][curY] == '.') {
				dfs(curX, curY);
			}
		}
	}
}

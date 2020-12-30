package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G5_B14503_로봇청소기 {
	static int R, C, curDir, curX, curY, count = 0;
	static int[] robot = new int[3];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean flag;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		st = new StringTokenizer(br.readLine(), " ");
		robot[0] = Integer.parseInt(st.nextToken());
		robot[1] = Integer.parseInt(st.nextToken());
		robot[2] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(robot[0], robot[1], robot[2]);
		System.out.println(count);
	}

	public static void dfs(int x, int y, int dir) {
		if (map[x][y] == 0)
			count++;
		flag = false;
		map[x][y] = 2;
		for (int i = 0; i < 4; i++) {
			curDir = (dir + 3 - i) % 4;
			curX = x + dx[curDir];
			curY = y + dy[curDir];
			if (map[curX][curY] == 0) {
				dfs(curX, curY, curDir);
				return;
			}
		}
		curDir = (dir + 2) % 4;
		curX = x + dx[curDir];
		curY = y + dy[curDir];
		if (map[curX][curY] == 1)
			return;
		else
			dfs(curX, curY, dir);
	}
}

package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G3_B1987_알파벳_김유창 {
	static int R, C, max = 0;
	static int curX, curY;
	static char[][] map;
	static boolean[] isContain = new boolean[91];
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static Queue<Character> q = new LinkedList<Character>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		String str;
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			map[i] = str.toCharArray();
		}
		dfs(0, 0, 1);
		System.out.println(max);
	}

	public static void dfs(int x, int y, int count) {
		isContain[map[x][y]] = true;
		for (int i = 0; i < 4; i++) {
			curX = x + dx[i];
			curY = y + dy[i];
			if (curX >= 0 && curX < R && curY >= 0 && curY < C && !isContain[map[curX][curY]]) {
				dfs(curX, curY, count + 1);
			}
		}
		if (max < count)
			max = count;
		isContain[map[x][y]] = false;
	}
}

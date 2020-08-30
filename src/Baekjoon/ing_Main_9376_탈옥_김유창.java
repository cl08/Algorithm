package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*	정보	
 *	1. 높이h, 너비w (2 ≤ h, w ≤ 100)
 *	2. .-빈공간, *-벽, #-문, $-죄수위치(2명)
 *	3. 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 최솟값 출력
 *
 *	1. 출구부터 dfs로 탐색
 *	2. 문을 만나면 문을 연 경우와 안 연 경우로 나누어서 탐색
 *	3. 두 죄수를 만나면 문을 연 갯수를 갱신하고 종료
 */

public class ing_Main_9376_탈옥_김유창 {

	static int TC, h, w, min;
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][][] visit;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			int count = 0;
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if ((i == 0 || i == h - 1 || j == 0 || j == w - 1) && map[i][j] != '*')
						q.offer(new int[] { i, j });
					if (map[i][j] == '#')
						count++;
				}
			}
			min = Integer.MAX_VALUE;
			visit = new boolean[h][w][count];
			while (!q.isEmpty()) {
				int[] temp = q.poll();
//				if (map[temp[0]][temp[1]] == '#')
//					dfs(temp[0], temp[1], 1, 0, 0);
//				else if (map[temp[0]][temp[1]] == '$')
//					dfs(temp[0], temp[1], 0, 1, 0);
			}
			sb.append(min).append('\n');
		}
		System.out.println(sb);
	}

//	public static void dfs(int x, int y, int door, int prisoner, int count) {
//		visit[x][y][count] = true;
//		if (prisoner == 2) {
//			if (door < min)
//				min = door;
//			return;
//		}
//		if (min <= door)
//			return;
//
//		boolean[][] visitCopy = new boolean[h][w];
//		for (int i = 0; i < h; i++) {
//			for (int j = 0; j < w; j++) {
//				visitCopy[i][j] = visit[i][j];
//			}
//		}
//		int tx, ty;
//		for (int i = 0; i < 4; i++) {
//			tx = x + dx[i];
//			ty = y + dy[i];
//			if (tx >= 0 && tx < h && ty >= 0 && ty < w && !visit[tx][ty] && map[tx][ty] != '*') {
//				if (map[tx][ty] == '.')
//					dfs(tx, ty, door, prisoner);
//				else if (map[tx][ty] == '$')
//					dfs(tx, ty, door, prisoner + 1);
//				else if (map[tx][ty] == '#') {
//					dfs(tx, ty, door + 1, prisoner);
//				}
//			}
//		}
//	}
}

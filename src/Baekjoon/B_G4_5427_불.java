package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 	정보
 *	1. @ - 상근이 위치, . - 빈공간, # - 벽, * - 불
 *	2. 불은 매초 상하좌우 옮겨 붙고, 상근이는 매초 상하좌우 한칸 이동 가능
 *	3. 상근이는 불이 붙어있는 칸이나 불이 붙으려는 칸으로 이동 불가능
 *	4. 상근이가 있는 칸에 불이 옮겨 옴과 동시에 다른 칸으로 이동 가능
 *
 *	1. 불이 번짐
 *	2. 상근이 이동. 탈출이 가능하면 종료
 *	3. 1~2반복
 */
public class B_G4_5427_불 {
	static int N, w, h;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][][] visit;
	static Queue<int[]> SK;
	static Queue<int[]> fire;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		L: for (int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			visit = new boolean[2][h][w];
			SK = new LinkedList<>();
			fire = new LinkedList<>();
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '@') {
						visit[1][i][j] = true;
						SK.offer(new int[] { i, j });
					}
					if (map[i][j] == '*') {
						visit[0][i][j] = true;
						fire.offer(new int[] { i, j });
					}
				}
			}
			int result = 0;
			while (!SK.isEmpty()) {
				result++;
				int size = fire.size();
				int[] temp;
				int tx, ty;

				// 불이 번짐
				while (size-- > 0) {
					temp = fire.poll();
					for (int i = 0; i < 4; i++) {
						tx = temp[0] + dx[i];
						ty = temp[1] + dy[i];
						if (tx >= 0 && tx < h && ty >= 0 && ty < w && !visit[0][tx][ty]) {
							if (map[tx][ty] == '.' || map[tx][ty] == '@') {
								map[tx][ty] = '*';
								visit[0][tx][ty] = true;
								fire.offer(new int[] { tx, ty });
							}
						}
					}
				}
				size = SK.size();

				// 상근이 이동
				while (size-- > 0) {
					temp = SK.poll();
					for (int i = 0; i < 4; i++) {
						tx = temp[0] + dx[i];
						ty = temp[1] + dy[i];
						// 건물 밖이면 탈출
						if (tx < 0 || tx >= h || ty < 0 || ty >= w) {
							sb.append(result).append('\n');
							continue L;
						} else if (!visit[0][tx][ty] && !visit[1][tx][ty] && map[tx][ty] == '.') {
							map[tx][ty] = '@';
							visit[1][tx][ty] = true;
							SK.offer(new int[] { tx, ty });
						}
					}
				}
			}
			sb.append("IMPOSSIBLE").append('\n');
		}
		System.out.println(sb);
	}
}

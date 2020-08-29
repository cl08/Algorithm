package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5650_핀볼게임_김유창 {
	static int N, count, max, startX, startY, x, y, dir;
	static int[][] map, wormhole;
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	static boolean[] whflag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			wormhole = new int[11][4];
			whflag = new boolean[11];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 5 && map[i][j] < 11) {
						if (!whflag[map[i][j]]) {
							wormhole[map[i][j]][0] = i;
							wormhole[map[i][j]][1] = j;
							whflag[map[i][j]] = true;
						} else {
							wormhole[map[i][j]][2] = i;
							wormhole[map[i][j]][3] = j;
						}

					}
				}
			}
			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							dir = k;
							x = startX = i;
							y = startY = j;
							count = 0;
							go();
							if (count > max) {
								max = count;
							}
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void go() {
		while (true) {
			x = x + dx[dir];
			y = y + dy[dir];

			// 벽
			if (x == -1 || x == N || y == -1 || y == N) {
				dir = (dir + 2) % 4;
				count++;
			}

			// 블랙홀이나 시작점
			else if (map[x][y] == -1 || (x == startX && y == startY)) {
				return;
			}

			// 웜홀
			else if (map[x][y] > 5 && map[x][y] < 11) {
				int temp = map[x][y];
				if (wormhole[temp][0] == x && wormhole[temp][1] == y) {
					x = wormhole[temp][2];
					y = wormhole[temp][3];
				} else {
					x = wormhole[temp][0];
					y = wormhole[temp][1];
				}
			}

			// 블록
			else if (map[x][y] > 0 && map[x][y] < 6) {
				switch (map[x][y]) {
				case 1:
					switch (dir) {
					case 0:
						dir = 2;
						break;
					case 1:
						dir = 0;
						break;
					case 2:
						dir = 3;
						break;
					case 3:
						dir = 1;
						break;
					}
					break;
				case 2:
					switch (dir) {
					case 0:
						dir = 2;
						break;
					case 1:
						dir = 3;
						break;
					case 2:
						dir = 1;
						break;
					case 3:
						dir = 0;
						break;
					}
					break;
				case 3:
					switch (dir) {
					case 0:
						dir = 1;
						break;
					case 1:
						dir = 3;
						break;
					case 2:
						dir = 0;
						break;
					case 3:
						dir = 2;
						break;
					}
					break;
				case 4:
					switch (dir) {
					case 0:
						dir = 3;
						break;
					case 1:
						dir = 2;
						break;
					case 2:
						dir = 0;
						break;
					case 3:
						dir = 1;
						break;
					}
					break;
				case 5:
					dir = (dir + 2) % 4;
					break;
				}
				count++;
			}
		}
	}
}

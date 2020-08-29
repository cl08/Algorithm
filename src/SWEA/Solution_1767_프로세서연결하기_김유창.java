package SWEA;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기_김유창 {
	static int N, result_core, result_length;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			list = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						list.add(new Point(i, j));
					}
				}
			}
			result_core = 0;
			result_length = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(result_length).append('\n');
		}
		System.out.println(sb);
	}

	// count:처리한 코어의 수, length:사용한 전선의 길이, core:전원이 연결된 코어의 수
	public static void dfs(int count, int length, int core) {
		if (core + list.size() - count < result_core)
			return;
		if (count == list.size()) {
			if (result_core < core) {
				result_core = core;
				result_length = length;
			} else if (result_core == core) {
				if (length < result_length) {
					result_length = length;
				}
			}
			return;
		}
		Point temp = list.get(count);
		if (temp.x == 0 || temp.y == 0 || temp.x == N - 1 || temp.y == N - 1) {
			dfs(count + 1, length, core + 1);
		} else {
			for (int i = 0; i < 4; i++) {
				int x = temp.x;
				int y = temp.y;

				// i방향으로 전원에 연결 할 수 있는 지 확인
				boolean flag = true;
				while (x > 0 && y > 0 && x < N - 1 && y < N - 1) {
					x = x + dx[i];
					y = y + dy[i];
					if (map[x][y] != 0) {
						flag = false;
						break;
					}
				}

				// i방향으로 전원 연결
				if (flag) {
					x = temp.x;
					y = temp.y;
					core++;
					while (x > 0 && y > 0 && x < N - 1 && y < N - 1) {
						x = x + dx[i];
						y = y + dy[i];
						map[x][y] = 2;
						length++;
					}
				}

				dfs(count + 1, length, core);

				// 백트래킹
				if (flag) {
					x = temp.x;
					y = temp.y;
					core--;
					while (x > 0 && y > 0 && x < N - 1 && y < N - 1) {
						x = x + dx[i];
						y = y + dy[i];
						map[x][y] = 0;
						length--;
					}
				}
			}
		}
	}
}

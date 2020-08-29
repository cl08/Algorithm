package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//	1.한 지점 클릭
//	2.지점을 8방탐색해서 지뢰 갯수 입력
//	3.입력한 지뢰 갯수가 0이면 8방방향 클릭
//	4.0이 아니면 8방향 지점을 각각 8방탐색하면서 0이 있나 확인
//	5.0이 있으면 클릭
//	6.1번으로 돌아가 다음 지점 클릭
//	변수 flag의 의미가 중요!

public class Solution_D4_1868_파핑파핑지뢰찾기_김유창 {
	static char[][] map;
	static int N, result;
	static boolean flag;

	static int[] dx = { -1, 0, 1, 1, 1, -1, 0, -1 };
	static int[] dy = { 1, 1, 1, 0, -1, -1, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						flag = true;
						// 1. 한 지점 클릭
						click(i, j);
						result++;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void click(int x, int y) {
		int tx, ty, count = 0;

		// 2.지점을 8방탐색해서 지뢰 갯수 입력
		for (int i = 0; i < 8; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] == '*')
				count++;
		}
		map[x][y] = (count + "").charAt(0);

		// 3.입력한 지뢰 갯수가 0이면 8방방향 클릭
		if (count == 0) {
			for (int i = 0; i < 8; i++) {
				tx = x + dx[i];
				ty = y + dy[i];
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] == '.')
					click(tx, ty);
			}
		}

		// 4. 0이 아니면 8방향 지점을 각각 8방탐색하면서 0이 있나 확인
		else if (flag) {
			flag = false;
			for (int i = 0; i < 8; i++) {
				tx = x + dx[i];
				ty = y + dy[i];
				if (tx >= 0 && tx < N && ty >= 0 && ty < N && map[tx][ty] == '.') {
					int tx2, ty2;
					count = 0;
					for (int j = 0; j < 8; j++) {
						tx2 = tx + dx[j];
						ty2 = ty + dy[j];
						if (tx2 >= 0 && tx2 < N && ty2 >= 0 && ty2 < N && map[tx2][ty2] == '*') {
							count++;
							break;
						}
					}
					// 5. 0이 있으면 클릭
					if (count == 0) {
						click(tx, ty);
						break;
					}
				}
			}
		}
	}
}

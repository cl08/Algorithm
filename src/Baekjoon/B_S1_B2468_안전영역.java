package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 최대 안전영역을 얻기 위해서 물의 높이 1~max 까지의 모든 안전영역을 구한 후 그중 max 값을 찾는다
 *
 * 1. map 과 visit 배열을 크기만큼 선언후 데이터 입력 받기 2. 영역의 max값 구하기 3. 물의 높이 1~ max-1 까지
 * 반복해서 안전영역을 dfs 또는 bfs 로 찾는다. 3.1 배열의 행, 열을 반복해서 물보다 높고, 방문하지 않은 영역을 검사 3.1.1
 * 안전영역 개수를 늘리고 3.1.2 dfs 또는 bfs 3.2 visit 배열 초기화 3.3 안전영역 max값 초기화 4. 안전영역 max값
 * 출력
 */
public class B_S1_B2468_안전영역 {
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[n][n];
		visit = new boolean[n][n];
		int max = 0, min = Integer.MAX_VALUE, count = 0, maxCount = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (max < map[i][j])
					max = map[i][j];
				if (min > map[i][j])
					min = map[i][j];
			}
		}
		for (int rain = min-1; rain < max; rain++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visit[i][j] && map[i][j]>rain) {
						dfs(i, j, rain);
						count++;
					}
				}
			}
			if (maxCount < count) {
				maxCount = count;
			}
			count = 0;
			for (int i = 0; i < n; i++) {
				Arrays.fill(visit[i], false);
			}
		}
		System.out.println(maxCount);
	}

	public static void dfs(int x, int y, int rain) {
		visit[x][y] = true;
		int curX, curY;
		for (int i = 0; i < 4; i++) {
			curX = x + dx[i];
			curY = y + dy[i];
			if (curX >= 0 && curX < n && curY >= 0 && curY < n && !visit[curX][curY] && map[curX][curY] > rain) {
				dfs(curX, curY, rain);
			}
		}
	}
}

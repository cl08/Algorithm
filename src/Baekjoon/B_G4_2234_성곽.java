package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 *	정보
 *	1. n*m 크기의 성(1≤n,m≤50)
 *	2. 성에는 최소 두개의 방이 존재
 *	3. 각 칸의 서쪽에 벽이 있으면 1, 북쪽에 벽이 있으면 2, 동쪽에 벽이 있으면 4, 남쪽에 벽이 있으면 8을 더함
 *	4. 방의 개수, 가장 넓은 방의 넓이, 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 순서대로 출력
 *
 *	1. 성을 차례대로 탐색하면서 방의 갯수, 가장 넓은 방의 넓이 구해서 출력
 *	2. 1번 탐색 도중 칸마다 방번호와 방사이즈를 붙여 놓음
 *	3. 방번호가 다르고 벽으로 막힌 인접한 두 방의 사이즈 합이 최대가 되는 값을 찾아서 출력
 *
 *	100ms 13628KB
 */
public class B_G4_2234_성곽 {

	static int n, m;
	static int count, area, max;
	static int[][] map;
	static int[][] size;
	static int[][] visit;
	static LinkedList<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		size = new int[m][n];
		visit = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = 0;
		area = 0;
		max = 0;

		// 성을 탐색하며 방의 갯수, 가장 넓은 방의 크기 구하기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j] == 0) {
					area = 1;
					count++;
					list = new LinkedList<>();
					dfs(i, j);
					// 각 방의 사이즈 저장해놓기
					for (int k = 0; k < list.size(); k++) {
						int[] temp = list.get(k);
						size[temp[0]][temp[1]] = area;
					}
				}
			}
		}
		System.out.println(count);
		System.out.println(max);

		// 방번호가 다르고 벽으로 막힌 인접한 두 방의 사이즈 합이 최대가 되는 값을 찾기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int temp = map[i][j];
				if (temp >= 8) {
					if (i + 1 < m && visit[i][j] != visit[i + 1][j] && max < size[i][j] + size[i + 1][j])
						max = size[i][j] + size[i + 1][j];
					temp = temp - 8;
				}
				if (temp >= 4) {
					if (j + 1 < n && visit[i][j] != visit[i][j + 1] && max < size[i][j] + size[i][j + 1])
						max = size[i][j] + size[i][j + 1];
					temp = temp - 4;
				}
				if (temp >= 2) {
					if (i - 1 >= 0 && visit[i][j] != visit[i - 1][j] && max < size[i][j] + size[i - 1][j])
						max = size[i][j] + size[i - 1][j];
					temp = temp - 2;
				}
				if (temp >= 1) {
					if (j - 1 >= 0 && visit[i][j] != visit[i][j - 1] && max < size[i][j] + size[i][j - 1])
						max = size[i][j] + size[i][j - 1];
					temp = temp - 1;
				}
			}
		}
		System.out.println(max);
	}

	public static void dfs(int x, int y) {
		if (max < area)
			max = area;
		visit[x][y] = count;
		list.add(new int[] { x, y });
		int temp = map[x][y];
		if (temp < 8) {
			if (x + 1 < m && visit[x + 1][y] == 0) {
				area++;
				dfs(x + 1, y);
			}
		} else
			temp = temp - 8;
		if (temp < 4) {
			if (y + 1 < n && visit[x][y + 1] == 0) {
				area++;
				dfs(x, y + 1);
			}
		} else
			temp = temp - 4;
		if (temp < 2) {
			if (x - 1 >= 0 && visit[x - 1][y] == 0) {
				area++;
				dfs(x - 1, y);
			}
		} else
			temp = temp - 2;
		if (temp < 1) {
			if (y - 1 >= 0 && visit[x][y - 1] == 0) {
				area++;
				dfs(x, y - 1);
			}
		} else
			temp = temp - 1;
	}
}

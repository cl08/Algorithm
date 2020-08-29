package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 *	정보
 *	1. 12*6맵
 *	2. R,G,B,P,Y : 색깔, . : 빈공간,
 *	3. 4개이상 상하좌우로 연결된 같은색 뿌요들은 한번에 사라짐
 *	4. 공중에 있는 뿌요들은 아래로 떨어짐
 *
 *	1. 탐색하면서 4개이상 같은색으로 붙어있는 뿌요들 제거
 *	2. 제거된 뿌요들이 있다면 count++
 *	3. 공중에 있는 뿌요들을 아래로 떨어뜨림
 *	4. 1~3 반복
 *	5. count 출력
 */
public class Main_11559_PuyoPuyo_김유창 {

	static final int R = 12;
	static final int C = 6;
	static int count;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean flag;
	static char[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		count = 0;
		flag = true;
		while (flag) {
			flag = false;

			// 4개이상 같은색 뿌요들이 붙어있으면 제거
			visit = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (!visit[i][j] && map[i][j] != '.') {
						bfs(i, j);
					}
				}
			}
			
			// 뿌요들을 밑으로 떨어뜨림
			if (flag) {
				count++;
				for (int i = 0; i < C; i++) {
					for (int j = R - 1; j > 0; j--) {
						if (map[j][i] == '.') {
							for (int k = j - 1; k >= 0; k--) {
								if (map[k][i] != '.') {
									map[j][i] = map[k][i];
									map[k][i] = '.';
									break;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(count);
	}

	public static void bfs(int x, int y) {
		char color = map[x][y];
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> al = new ArrayList<>();
		visit[x][y] = true;
		q.offer(new int[] { x, y });
		al.add(new int[] { x, y });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int tx, ty;
			for (int i = 0; i < 4; i++) {
				tx = temp[0] + dx[i];
				ty = temp[1] + dy[i];
				if (tx >= 0 && tx < R && ty >= 0 && ty < C && !visit[tx][ty] && map[tx][ty] == color) {
					visit[tx][ty] = true;
					q.offer(new int[] { tx, ty });
					al.add(new int[] { tx, ty });
				}
			}
		}
		if (al.size() >= 4) {
			int size = al.size();
			int[] temp;
			for (int i = 0; i < size; i++) {
				temp = al.get(i);
				map[temp[0]][temp[1]] = '.';
			}
			flag = true;
		}
	}
}

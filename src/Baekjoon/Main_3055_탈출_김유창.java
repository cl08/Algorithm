package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	정보
 *	1. R*C 맵
 *	2. *-물, .-빈칸,  X-돌, D-비버의굴, S-고슴도치
 *	3. 물이 있는 칸과 인접한 빈 칸은 물이 참
 *	4. 고슴도치가 인접한 빈 칸으로 이동
 *	
 *	1. 물이 있는 칸과 인접한 빈 칸을 물로 채움
 *	2. 고슴도치 이동. 이동한 위치가 비버의 굴이면 종료
 *	3. 1~2 반복
 *
 *	80ms 13240KB
 */
public class Main_3055_탈출_김유창 {
	static int R, C;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][][] visit;
	static Queue<int[]> water = new LinkedList<>();
	static Queue<int[]> hedgehog = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[2][R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					visit[1][i][j] = true;
					hedgehog.offer(new int[] { i, j });
				}
				if (map[i][j] == '*') {
					visit[0][i][j] = true;
					water.offer(new int[] { i, j });
				}
			}
		}
		int result = 0;
		while (!hedgehog.isEmpty()) {
			result++;
			int size = water.size();
			int[] temp;
			int tx, ty;
			while (size-- > 0) {
				temp = water.poll();
				for (int i = 0; i < 4; i++) {
					tx = temp[0] + dx[i];
					ty = temp[1] + dy[i];
					if (tx >= 0 && tx < R && ty >= 0 && ty < C && !visit[0][tx][ty]) {
						if (map[tx][ty] == '.' || map[tx][ty] == 'S') {
							map[tx][ty] = '*';
							visit[0][tx][ty] = true;
							water.offer(new int[] { tx, ty });
						}
					}
				}
			}
			size = hedgehog.size();
			while (size-- > 0) {
				temp = hedgehog.poll();
				for (int i = 0; i < 4; i++) {
					tx = temp[0] + dx[i];
					ty = temp[1] + dy[i];
					if (tx >= 0 && tx < R && ty >= 0 && ty < C && !visit[0][tx][ty] && !visit[1][tx][ty]) {
						if (map[tx][ty] == '.') {
							map[tx][ty] = 'S';
							visit[1][tx][ty] = true;
							hedgehog.offer(new int[] { tx, ty });
						} else if (map[tx][ty] == 'D') {
							System.out.println(result);
							return;
						}
					}
				}
			}
		}
		System.out.println("KAKTUS");
	}
}
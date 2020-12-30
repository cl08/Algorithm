package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_G3_1941_소문난칠공주 {

	static int pCount, result;
	static int[] pick;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][] visit;
	static boolean[][] princess;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		result = 0;

		// 0~24 중의 숫자중 7개 뽑기
		pick = new int[7];
		combination(0, 0, 0);
		System.out.println(result);
	}

	public static void combination(int index, int count, int Y) {
		// 임도연파가 3명보다 많으면 return
		if (Y > 3) {
			return;
		}
		
		// 임도연파가 3명보다 많지 않고 7명이 선택됐을 경우
		if (count == 7) {
			// 번호를 좌표로 변환하고 자리 표시하기
			princess = new boolean[5][5];
			for (int i = 0; i < 7; i++) {
				int x = pick[i] / 5;
				int y = pick[i] % 5;
				princess[x][y] = true;
			}

			// 표시된 자리들이 모두 연결 되어 있으면 result++
			pCount = 0;
			visit = new boolean[5][5];
			dfs(pick[0] / 5, pick[0] % 5);
			if (pCount == 7) {
				result++;
			}
			return;
		}
		for (int i = index; i < 25; i++) {
			// 임도연파인지 이다솜 파인지 확인
			if (map[i / 5][i % 5] == 'Y') {
				pick[count] = i;
				combination(i + 1, count + 1, Y + 1);
			} else {
				pick[count] = i;
				combination(i + 1, count + 1, Y);
			}
		}
	}

	public static void dfs(int x, int y) {
		visit[x][y] = true;
		pCount++;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < 5 && ty >= 0 && ty < 5 && princess[tx][ty] && !visit[tx][ty])
				dfs(tx, ty);
		}
	}
}

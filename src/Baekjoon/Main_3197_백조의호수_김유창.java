package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*	
 *	정보
 * 	1. .-물, X-빙판, L-백조
 *	2. 매일 물과 접촉한 빙판은 녹음 
 *	3. 두 백조가 만나기까지 며칠이 걸리는지 출력
 *
 *	1. 탐색으로 두 백조가 있는 공간을 구분하여 마크
 *	2. 날짜 카운트 +1
 *	3. 물과 접촉한 빙판 녹임
 *	4. 탐색으로 두 공간이 만나는지 체크
 *	5. 2~4반복
 *	> 시간 초과
 *	
 *	1. 백조 위치 저장
 *	2. 다음 날 녹을 얼음 위치 waterQueue에 저장
 *	3. 날짜 카운트 +1
 *	4. Queue에 저장된 위치의 얼음들을 녹이고 다음날 녹을 얼음 위치 Queue에 저장
 *	5. 백조끼리 만날 수 있나 확인
 *	6. 3~5 반복
 */

public class Main_3197_백조의호수_김유창 {

	static int R, C;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static char[][] map;
	static boolean[][] visit;
	static Queue<int[]> searchQueue;
	static Queue<int[]> waterQueue;
	static Queue<int[]> nextQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		int[] L = new int[2];
		waterQueue = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 'X') {
					waterQueue.offer(new int[] { i, j });
				}
				// 백조 위치 저장
				if (map[i][j] == 'L') {
					L[0] = i;
					L[1] = j;
				}
			}
		}
		searchQueue = new LinkedList<>();
		searchQueue.offer(L);
		visit[L[0]][L[1]] = true;

		int day = 0;
		L: while (true) {

			day++;
			
			// 얼음 녹이고 내일 녹을 얼음위치 저장
			int size = waterQueue.size();
			for (int i = 0; i < size; i++) {
				int[] temp = waterQueue.poll();
				for (int j = 0; j < 4; j++) {
					int tx = temp[0] + dx[j];
					int ty = temp[1] + dy[j];
					if (tx >= 0 && tx < R && ty >= 0 && ty < C && map[tx][ty] == 'X') {
						map[tx][ty] = '.';
						waterQueue.offer(new int[] { tx, ty });
					}
				}
			}
			
			nextQueue = new LinkedList<>();
			
			// 백조를 만날때까지 탐색
			while (!searchQueue.isEmpty()) {
				int[] temp = searchQueue.poll();
				for (int i = 0; i < 4; i++) {
					int tx = temp[0] + dx[i];
					int ty = temp[1] + dy[i];
					if (tx >= 0 && tx < R && ty >= 0 && ty < C && !visit[tx][ty]) {
						if (map[tx][ty] == 'X') {
							visit[tx][ty] = true;
							nextQueue.offer(new int[] { tx, ty });
						} else if (map[tx][ty] == '.') {
							visit[tx][ty] = true;
							searchQueue.offer(new int[] { tx, ty });
						} else
							break L;
					}
				}
			}
			searchQueue = nextQueue;

		}
		System.out.println(day);
	}
}
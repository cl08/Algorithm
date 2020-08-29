package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	정보
 *	1. 좌우 번갈아가면서 입력된 높이에서 막대기를 던짐(좌먼저)
 *	2. 막대기의 경로에 미네랄이 있으면 처음 만나는 미네랄을 파괴함
 *	3. 미네랄 클러스터는 모양을 유지한채 다른 미네랄 클러스터나 땅을 만날때 까지 떨어짐
 *	4. 모든 막대를 던진 후 미네랄 모양 출력
 * 	5. 1 ≤ R,C ≤ 100. 1 ≤ N ≤ 100
 *	
 *	1. 미네랄 파괴
 *	2. 파괴된 미네랄에 인접한 미네랄이 있으면 탐색으로 떨어지는 미네랄 클러스터인지 확인
 *	3. 떨어지는 클러스터인 경우 낙하
 *	4. 1~3반복
 */
public class Main_2933_미네랄_김유창 {

	static int R, C, N;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++)
			map[i] = br.readLine().toCharArray();
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = R - Integer.parseInt(st.nextToken());

			// 막대기 던지기
			int x = height;
			int y = -1;
			// 왼쪽에서
			if (i % 2 == 0) {
				for (int j = 0; j < C; j++) {
					if (map[height][j] == 'x') {
						map[height][j] = '.';
						y = j;
						break;
					}
				}
			}
			// 오른쪽에서
			else {
				for (int j = C - 1; j >= 0; j--) {
					if (map[height][j] == 'x') {
						map[height][j] = '.';
						y = j;
						break;
					}
				}
			}
			
			// 막대기를 던져서 미네랄이 파괴된 경우 파괴된 미네랄이 인접 미네랄 클러스터에 영향을 미치는지 확인
			if (y != -1) {
				int tx, ty;
				// 인접한 미네랄이 있는지 확인
				L: for (int j = 0; j < 4; j++) {
					tx = x + dx[j];
					ty = y + dy[j];
					if (tx >= 0 && tx < R && ty >= 0 && ty < C && map[tx][ty] == 'x') {
						// 미네랄 클러스터가 낙하하는지 확인
						boolean[][] visit = new boolean[R][C];
						Queue<int[]> q = new LinkedList<>();
						q.offer(new int[] { tx, ty });
						visit[tx][ty] = true;
						// 낙하할 때 클러스터의 위치 정보를 담을 ArrayList
						ArrayList<int[]> al = new ArrayList<int[]>();
						al.add(new int[] { tx, ty });
						while (!q.isEmpty()) {
							int[] temp = q.poll();
							int mx, my;
							for (int k = 0; k < 4; k++) {
								mx = temp[0] + dx[k];
								my = temp[1] + dy[k];
								if (mx >= 0 && mx < R && my >= 0 && my < C && !visit[mx][my] && map[mx][my] == 'x') {
									if (mx == R - 1)
										continue L;
									visit[mx][my] = true;
									q.offer(new int[] { mx, my });
									al.add(new int[] { mx, my });
								}
							}
						}

						// 낙하해야 하는 칸수 구하기
						int size = al.size();
						int min = Integer.MAX_VALUE;
						for (int k = 0; k < size; k++) {
							int[] temp = al.get(k);
							if (map[temp[0] + 1][temp[1]] == '.') {
								int count = 1;
								boolean flag = true;
								while (flag) {
									if (temp[0] + count == R - 1 || (!visit[temp[0] + count + 1][temp[1]]
											&& map[temp[0] + count + 1][temp[1]] == 'x')) {
										min = count < min ? count : min;
										flag = false;
									}
									count++;
								}
							}
						}
						
						// 미네랄 클러스터 낙하
						for (int k = 0; k < size; k++) {
							int[] temp = al.get(k);
							map[temp[0]][temp[1]] = '.';
						}
						for (int k = 0; k < size; k++) {
							int[] temp = al.get(k);
							map[temp[0] + min][temp[1]] = 'x';
						}
						break;
					}
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}

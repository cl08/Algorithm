package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 	정보
 *	1. N*N맵. 1:나무, 0:빈칸
 *	2. B로 주어지는 길이 3의 통나무를 E로 주어지는 칸에다 옮겨야함
 *	3. 회전, 상, 하, 좌, 우로 움직일 수 있고, 이동하려는 칸이나 회전반경에 나무가 있으면 안됨
 *	4. 통나무를 옮기는 단위 동작의 최소 횟수 출력. 이동이 불가능하면 0 출력
 *
 *	1. bfs 탐색(이동가능한 경우 4칸, 회전 가능한 경우 회전)
 *	2. 이동하려는 칸이나 회전하려는 칸에 dp값이 depth 보다 작으면 큐에 넣지 않음
 *	3. 목적지의 dp값 출력
 *
 *	108ms 15940KB
 */
public class B_G2_1938_통나무옮기기 {

	static final int[] dx = { -1, 0, 1, 0, 1, -1, -1, 1 };
	static final int[] dy = { 0, 1, 0, -1, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		int index1 = 0, index2 = 0;
		int[][] log = new int[3][2];
		int[][] des = new int[3][2];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			map[i] = temp.toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B') {
					log[index1][0] = i;
					log[index1][1] = j;
					index1++;
				}
				if (map[i][j] == 'E') {
					des[index2][0] = i;
					des[index2][1] = j;
					index2++;
				}
			}
		}
		int[][][] dp = new int[2][N][N];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		// shape - 0:가로모양, 1:세로모양
		int shape1, shape2;
		if (log[0][0] == log[2][0])
			shape1 = 0;
		else
			shape1 = 1;

		if (des[0][0] == des[2][0])
			shape2 = 0;
		else
			shape2 = 1;

		dp[shape1][log[1][0]][log[1][1]] = 0;
		int depth = 0;
		Queue<int[][]> q = new LinkedList<>();
		q.offer(log);
		L: while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			while (size-- > 0) {

				if (dp[shape2][des[1][0]][des[1][1]] <= depth)
					break L;

				int[][] t = q.poll();

				if (t[0][0] == t[2][0])
					shape1 = 0;
				else
					shape1 = 1;

				int tx, ty;
				boolean flag;
				int[][] temp = new int[3][2];
				for (int i = 0; i < 4; i++) {
					flag = true;
					for(int j=0; j<3; j++) {
						for(int k=0; k<2; k++) {
							temp[j][k] = t[j][k];
						}
					}
					// temp = t.clone(); < temp 값이 변경될 때 t 값도 같이 바뀜
					
					// 통나무가 이동할 장소가 범위 안에 있고 비어있으면 flag 값이 true로 유지됨
					for (int j = 0; j < 3; j++) {
						tx = temp[j][0] + dx[i];
						ty = temp[j][1] + dy[i];
						if (tx < 0 || tx >= N || ty < 0 || ty >= N || map[tx][ty] == '1') {
							flag = false;
							break;
						}
					}
					// flag 값이 true 로 유지되었고, 이동할 장소에 최소의 움직임으로 도착한다면 값 갱신
					if (flag && depth < dp[shape1][temp[1][0] + dx[i]][temp[1][1] + dy[i]]) {
						for (int j = 0; j < 3; j++) {
							temp[j][0] = temp[j][0] + dx[i];
							temp[j][1] = temp[j][1] + dy[i];
						}
						dp[shape1][temp[1][0]][temp[1][1]] = depth;
						if (shape1 == shape2 && temp[1][0] == des[1][0] && temp[1][1] == des[1][1])
							break L;
						q.offer(new int[][] {{temp[0][0],temp[0][1]},{temp[1][0],temp[1][1]},{temp[2][0],temp[2][1]}});
					}
				}
				// 통나무가 회전 가능한 지 확인
				flag = true;
				for(int j=0; j<3; j++) {
					for(int k=0; k<2; k++) {
						temp[j][k] = t[j][k];
					}
				}
				for (int i = 0; i < 8; i++) {
					tx = temp[1][0] + dx[i];
					ty = temp[1][1] + dy[i];
					if (tx < 0 || tx >= N || ty < 0 || ty >= N || map[tx][ty] == '1') {
						flag = false;
						break;
					}
				}
				// 회전
				if (flag) {
					if (temp[0][0] == temp[2][0]) {
						shape1 = (shape1 + 1) % 2;
						if (depth < dp[shape1][temp[1][0]][temp[1][1]]) {
							temp[0][0] = temp[0][0] - 1;
							temp[0][1] = temp[0][1] + 1;
							temp[2][0] = temp[2][0] + 1;
							temp[2][1] = temp[2][1] - 1;
							dp[shape1][temp[1][0]][temp[1][1]] = depth;
							if (shape1 == shape2 && temp[1][0] == des[1][0] && temp[1][1] == des[1][1])
								break L;
							q.offer(new int[][] {{temp[0][0],temp[0][1]},{temp[1][0],temp[1][1]},{temp[2][0],temp[2][1]}});
						}

					} else {
						shape1 = (shape1 + 1) % 2;
						if (depth < dp[shape1][temp[1][0]][temp[1][1]]) {
							temp[0][0] = temp[0][0] + 1;
							temp[0][1] = temp[0][1] - 1;
							temp[2][0] = temp[2][0] - 1;
							temp[2][1] = temp[2][1] + 1;
							dp[shape1][temp[1][0]][temp[1][1]] = depth;
							if (shape1 == shape2 && temp[1][0] == des[1][0] && temp[1][1] == des[1][1])
								break L;
							q.offer(new int[][] {{temp[0][0],temp[0][1]},{temp[1][0],temp[1][1]},{temp[2][0],temp[2][1]}});
						}
					}
				}
			}
		}

		if (dp[shape2][des[1][0]][des[1][1]] == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(dp[shape2][des[1][0]][des[1][1]]);
	}
}

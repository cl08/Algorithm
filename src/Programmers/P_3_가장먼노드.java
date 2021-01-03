package Programmers;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 1. 인접 행렬 생성
 * 2. bfs 탐색으로 한사이클마다 추가된 노드의 수를 저장
 * 3. 탐색을 마친 후 결과 출력
 */
public class P_3_가장먼노드 {

	public static void main(String[] args) {
		int n = 6;
		int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		System.out.println(solution(n, edge));

	}

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[][] link = new boolean[n][n];
		boolean[] visit = new boolean[n];
		for (int i = 0; i < edge.length; i++) {
			link[edge[i][0] - 1][edge[i][1] - 1] = true;
			link[edge[i][1] - 1][edge[i][0] - 1] = true;
		}
		visit[0] = true;
		for (int i = 0; i < n; i++) {
			if (link[0][i]) {
				visit[i] = true;
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int size = q.size();
			answer = size;
			System.out.println(answer);
			while (size-- > 0) {
				int node = q.poll();
				for (int i = 0; i < n; i++) {
					if (link[node][i] && !visit[i]) {
						visit[i] = true;
						q.offer(i);
					}
				}
			}
		}
		return answer;
	}
}

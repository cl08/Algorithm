package Programmers;

public class P_2_네트워크 {
	public int solution(int n, int[][] computers) {
		int answer = 0;
		boolean[] visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				dfs(i, visit, n, computers);
				answer++;
			}
		}
		return answer;
	}

	public static void dfs(int index, boolean[] visit, int n, int[][] computers) {
		visit[index] = true;
		for (int i = 0; i < n; i++) {
			if (index == i)
				continue;
			if (!visit[i] && computers[index][i] == 1)
				dfs(i, visit, n, computers);
		}
	}
}

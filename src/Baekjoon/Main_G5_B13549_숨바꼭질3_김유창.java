package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_B13549_숨바꼭질3_김유창 {
	public static void main(String[] args) throws IOException {
		class count {
			int loc;
			int cnt;

			count(int loc, int cnt) {
				this.loc = loc;
				this.cnt = cnt;
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int min = Integer.MAX_VALUE;
		int result = 0;
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<count> q = new LinkedList<count>();
		boolean[] visit = new boolean[100001];
		if (K <= N) {
			System.out.println(N - K);
			return;
		}
		visit[N] = true;
		q.offer(new count(N, 0));
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				count n = q.poll();
				if (n.loc == K && n.cnt < min)
					result = n.cnt;
				else {
					if (0 <= n.loc * 2 && n.loc * 2 <= 100000 && !visit[n.loc * 2]) {
						visit[n.loc * 2] = true;
						q.offer(new count(n.loc * 2, n.cnt));
					}
					if (0 <= n.loc - 1 && n.loc - 1 <= 100000 && !visit[n.loc - 1]) {
						visit[n.loc - 1] = true;
						q.offer(new count(n.loc - 1, n.cnt + 1));
					}
					if (0 <= n.loc + 1 && n.loc + 1 <= 100000 && !visit[n.loc + 1]) {
						visit[n.loc + 1] = true;
						q.offer(new count(n.loc + 1, n.cnt + 1));
					}

				}
			}
		}
		System.out.println(result);
	}
}
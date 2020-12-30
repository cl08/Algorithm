package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_G5_B12851_숨바꼭질2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int n, count = 0, minCount = 100000, result = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[100001];
		if (K <= N) {
			System.out.println(N - K);
			System.out.println(1);
			return;
		}
		visit[N] = true;
		q.offer(N);
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				n = q.poll();
				visit[n] = true;
				if (n == K) {
					if (minCount > count)
						minCount = count;
					if (minCount == count)
						result++;
				} else {
					if (0 <= n - 1 && n - 1 <= 100000 && !visit[n - 1]) {
						q.offer(n - 1);
					}
					if (0 <= n + 1 && n + 1 <= 100000 && !visit[n + 1]) {
						q.offer(n + 1);
					}
					if (0 <= n * 2 && n * 2 <= 100000 && !visit[n * 2]) {
						q.offer(n * 2);
					}
				}
			}
			count++;
		}
		System.out.println(minCount);
		System.out.println(result);
	}
}
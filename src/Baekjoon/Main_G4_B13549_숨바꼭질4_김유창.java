package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_B13549_숨바꼭질4_김유창 {
	public static void main(String[] args) throws IOException {
		class FootPrint {
			int loc;
			String fp;

			FootPrint(int loc, String fp) {
				this.loc = loc;
				this.fp = fp;
			}
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int count = 0;
		Queue<FootPrint> q = new LinkedList<FootPrint>();
		boolean[] visit = new boolean[100001];
		if (K <= N) {
			System.out.println(N - K);
			for(int i=N; i>=K; i--)
				System.out.print(i+" ");
			return;
		}
		visit[N] = true;
		q.offer(new FootPrint(N, N + ""));
		L: while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				FootPrint n = q.poll();
				if (n.loc == K) {
					System.out.println(count);
					System.out.println(n.fp);
					break L;
				} else {
					if (0 <= n.loc - 1 && n.loc - 1 <= 100000 && !visit[n.loc - 1]) {
						visit[n.loc - 1] = true;
						q.offer(new FootPrint(n.loc - 1, n.fp + " " + (n.loc - 1)));
					}
					if (0 <= n.loc + 1 && n.loc + 1 <= 100000 && !visit[n.loc + 1]) {
						visit[n.loc + 1] = true;
						q.offer(new FootPrint(n.loc + 1, n.fp + " " + (n.loc + 1)));
					}
					if (0 <= n.loc * 2 && n.loc * 2 <= 100000 && !visit[n.loc * 2]) {
						visit[n.loc * 2] = true;
						q.offer(new FootPrint(n.loc * 2, n.fp + " " + (n.loc * 2)));
					}		
				}
			}
			count++;
		}
	}
}
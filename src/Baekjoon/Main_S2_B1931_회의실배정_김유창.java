package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_B1931_회의실배정_김유창 {

	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;

		Meeting() {
		}

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end)
				return this.start - o.start;
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int count = 1;
		Queue<Meeting> q = new PriorityQueue<Meeting>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			q.offer(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Meeting current = q.poll();
		Meeting next;
		while (!q.isEmpty()) {
			next = q.poll();
			if (current.end <= next.start) {
				count++;
				current = next;
			}
		}
		System.out.println(count);
	}
}

package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_D4_7701_염라대왕의이름정렬_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Data> q = new PriorityQueue<Data>();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append('\n');
			int N = Integer.parseInt(br.readLine());
			String str;
			for (int i = 0; i < N; i++) {
				str = br.readLine();
				q.offer(new Data(str, str.length()));
			}
			Data temp = q.poll();
			sb.append(temp.str + '\n');
			while (!q.isEmpty()) {
				if (q.peek().str.equals(temp.str))
					q.poll();
				else {
					temp = q.poll();
					sb.append(temp.str + '\n');
				}
			}
		}
		System.out.println(sb);
	}

	public static class Data implements Comparable<Data> {
		String str;
		int size;

		public Data(String str, int size) {
			this.str = str;
			this.size = size;
		}

		@Override
		public int compareTo(Data d) {
			if (this.size == d.size) {
				return this.str.compareTo(d.str);
			}
			return this.size - d.size;
		}
	}
}

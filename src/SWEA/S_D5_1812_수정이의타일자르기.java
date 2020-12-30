package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_D5_1812_수정이의타일자르기 {
	public static class Rectangle implements Comparable<Rectangle> {
		int min, max;

		public Rectangle(int width, int height) {
			if (width < height) {
				min = width;
				max = height;
			} else {
				min = height;
				max = width;
			}
		}

		@Override
		public int compareTo(Rectangle o) {
			if (this.min == o.min)
				return o.max - this.max;
			return o.min - this.min;
		}
	}

	static int N, M, count;
	static int[] size;
	static Queue<Rectangle> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			size = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				size[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(size);
			count = 0;
			cut();
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}

	public static void cut() {
		q = new PriorityQueue<Rectangle>();
		q.offer(new Rectangle(M, M));
		count++;
		for (int i = N - 1; i >= 0; i--) {
			go(1 << size[i]);
		}
	}

	public static void go(int size) {
		Rectangle r = q.poll();
		if (r.min >= size) {
			if (r.min != size)
				q.offer(new Rectangle(r.min - size, size));
			q.offer(new Rectangle(r.min, r.max - size));
		} else {
			q.offer(r);
			q.offer(new Rectangle(M - size, size));
			q.offer(new Rectangle(M, M - size));
			count++;
		}
	}
}

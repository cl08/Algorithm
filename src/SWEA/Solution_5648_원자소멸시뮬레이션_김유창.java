package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5648_원자소멸시뮬레이션_김유창 {
	static class Atom implements Comparable<Atom> {
		int x;
		int y;
		int dir;
		int e;

		public Atom(int x, int y, int dir, int e) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.e = e;
		}

		@Override
		public int compareTo(Atom o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			Queue<Atom> pq = new PriorityQueue<Atom>();
			Queue<Atom> q = new LinkedList<Atom>();
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken()) * 2;
				int x = Integer.parseInt(st.nextToken()) * -2;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				q.offer(new Atom(x, y, dir, e));
			}
			int result = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					Atom temp = q.poll();
					switch (temp.dir) {
					case 0:
						temp.x--;
						break;
					case 1:
						temp.x++;
						break;
					case 2:
						temp.y--;
						break;
					case 3:
						temp.y++;
						break;
					}
					if (temp.x >= -2000 && temp.x <= 2000 && temp.y >= -2000 && temp.y <= 2000)
						pq.offer(temp);
				}
				if (pq.size() > 1) {
					Atom before = pq.poll();
					Atom current = null;
					boolean flag = false;
					while (!pq.isEmpty()) {
						current = pq.poll();
						if (before.x == current.x && before.y == current.y) {
							result = result + before.e;
							flag = true;
						} else {
							// 그전까지는 같았고 달라짐
							if (flag) {
								result = result + before.e;
								flag = false;
							} else {
								q.offer(before);
							}
						}
						before = current;
					}
					if (flag) {
						result = result + current.e;
					} else {
						q.offer(current);
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}
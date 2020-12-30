package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_5653_줄기세포배양 {
	static class Cell implements Comparable<Cell> {
		int x;
		int y;
		int vitality;
		int count;
		boolean active;

		public Cell(int x, int y, int vitality, int count, boolean active) {
			super();
			this.x = x;
			this.y = y;
			this.vitality = vitality;
			this.count = count;
			this.active = active;
		}

		@Override
		public int compareTo(Cell o) {
			return o.vitality - this.vitality;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };

		for (int tc = 1; tc <= T; tc++) {
			Queue<Cell> q = new LinkedList<Cell>();
			Queue<Cell> subQ = new PriorityQueue<Cell>();
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			boolean[][] map = new boolean[N + K][M + K];
			for (int i = (K / 2); i < N + (K / 2); i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = K / 2; j < M + K / 2; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (temp > 0) {
						map[i][j] = true;
						q.offer(new Cell(i, j, temp, temp, false));
					}
				}
			}
			int count = 0;
			while (count != K) {
				count++;
				int size = q.size();
				while (size-- > 0) {
					Cell temp = q.poll();
					temp.count--;
					// 활성화상태
					if (temp.active) {
						// 번식
						if (temp.count == temp.vitality - 1)
							subQ.offer(temp);
						if (temp.count > 0)
							q.offer(temp);
					}
					// 비활성화상태
					else {
						// 활성화로 전환
						if (temp.count == 0) {
							temp.active = true;
							temp.count = temp.vitality;
						}
						q.offer(temp);
					}
				}

				// 동시에 같은 셀에 번식할 시 생명력이 높은 줄기 세포가 번식
				while (!subQ.isEmpty()) {
					Cell temp = subQ.poll();
					for (int i = 0; i < 4; i++) {
						int tx = temp.x + dx[i];
						int ty = temp.y + dy[i];
						if (!map[tx][ty]) {
							map[tx][ty] = true;
							q.offer(new Cell(tx, ty, temp.vitality, temp.vitality, false));
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(q.size()).append('\n');
		}
		System.out.println(sb);
	}
}

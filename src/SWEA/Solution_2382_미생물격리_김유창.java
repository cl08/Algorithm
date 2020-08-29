package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2382_미생물격리_김유창 {
	static int N, M, K;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Info[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			info = new Info[K];
			for (int i = 0; i < K; i++) {
				info[i] = new Info();
				st = new StringTokenizer(br.readLine(), " ");
				info[i].x = Integer.parseInt(st.nextToken());
				info[i].y = Integer.parseInt(st.nextToken());
				info[i].size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if (dir == 1)
					info[i].dir = 0;
				else if (dir == 2)
					info[i].dir = 2;
				else if (dir == 3)
					info[i].dir = 3;
				else
					info[i].dir = 1;
			}
			for (int i = 0; i < M; i++) {
				int tx, ty;
				// 미생물 군집들 이동
				for (int j = 0; j < K; j++) {
					if (!info[j].live)
						continue;
					tx = info[j].x + dx[info[j].dir];
					ty = info[j].y + dy[info[j].dir];
					info[j].x = tx;
					info[j].y = ty;
					if (tx == 0 || tx == N - 1 || ty == 0 || ty == N - 1) {
						info[j].size = info[j].size / 2;
						info[j].dir = (info[j].dir + 2) % 4;
					}
				}
				// 이동후 겹치는 군집 처리
				overlap();
			}
			
			// 남은 미생물 세기
			int result = 0;
			for (int i = 0; i < K; i++) {
				if (info[i].live)
					result = result + info[i].size;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);

	}

	public static void overlap() {
		// 좌표가 겹치는 미생물 찾아서 큐에 넣음
		boolean[] visit = new boolean[K];
		Queue<Info> q = new LinkedList<Info>();
		for (int i = 0; i < K - 1; i++) {
			if (visit[i] || !info[i].live)
				continue;
			for (int j = i + 1; j < K; j++) {
				if (visit[j] || !info[j].live)
					continue;
				if (info[i].x == info[j].x && info[i].y == info[j].y) {
					visit[j] = true;
					q.offer(info[j]);
					info[j].live = false;
				}
			}
			// 큐에 있는 미생물 군집들의 크기를 합치고 조건에 따라 방향을 설정
			if (!q.isEmpty()) {
				q.offer(info[i]);
				visit[i] = true;
				int maxSize = 0;
				int sum = 0;
				int maxDir = 0;
				while (!q.isEmpty()) {
					Info temp = q.poll();
					sum = sum + temp.size;
					if (maxSize < temp.size) {
						maxSize = temp.size;
						maxDir = temp.dir;
					}
				}
				info[i].size = sum;
				info[i].dir = maxDir;
			}
		}
	}

	public static class Info {
		int x;
		int y;
		int size;
		int dir;
		boolean live;

		Info() {
			this.live = true;
		}
	}
}

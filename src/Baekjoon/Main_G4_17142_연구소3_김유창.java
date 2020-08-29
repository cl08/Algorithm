package Baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_17142_연구소3_김유창 {
	static int N, M, n, result;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[] select;
	static int[][] map;
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		select = new int[M];
		list = new ArrayList<Point>();
		n = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 2)
					list.add(new Point(i, j));
				else if (temp == 0)
					n++;
				map[i][j] = temp;
			}
		}
		result = Integer.MAX_VALUE;
		combination(0, 0);
		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	public static void combination(int index, int count) {
		if (count == M) {
			simul();
			return;
		}
		int size = list.size();
		for (int i = index; i < size; i++) {
			select[count] = i;
			combination(i + 1, count + 1);
		}
	}

	public static void simul() {
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < select.length; i++) {
			Point temp = list.get(select[i]);
			visit[temp.x][temp.y] = true;
			q.offer(temp);
		}
		int count = 0;
		int spreadCount = 0;
		while (!q.isEmpty()) {
			if (spreadCount >= result)
				return;
			if (count == n && spreadCount < result)
				result = spreadCount;
			int size = q.size();
			while (size-- > 0) {
				Point temp = q.poll();
				for (int i = 0; i < 4; i++) {
					int tx = temp.x + dx[i];
					int ty = temp.y + dy[i];
					if (tx >= 0 && tx < N && ty >= 0 && ty < N && !visit[tx][ty]) {
						if (map[tx][ty] == 0) {
							visit[tx][ty] = true;
							q.offer(new Point(tx, ty));
							count++;
						} else if (map[tx][ty] == 2) {
							visit[tx][ty] = true;
							q.offer(new Point(tx, ty));
						}
					}
				}
			}
			spreadCount++;
		}
	}
}
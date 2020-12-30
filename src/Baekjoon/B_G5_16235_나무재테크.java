package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_G5_16235_나무재테크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int[][] food = new int[N][N];
		int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
		int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Tree> spring = new PriorityQueue<Tree>();
		ArrayList<Tree> summer = new ArrayList<Tree>();
		ArrayList<Tree> fall = new ArrayList<Tree>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			spring.offer(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}

		int year = 0;
		while (year < K) {

			// 봄
			while (!spring.isEmpty()) {
				Tree temp = spring.poll();
				if (temp.age <= map[temp.x][temp.y]) {
					map[temp.x][temp.y] -= temp.age;
					temp.age++;
					fall.add(temp);
				} else {
					summer.add(temp);
				}
			}

			// 여름
			for (Tree temp : summer) {
				map[temp.x][temp.y] += (temp.age / 2);
			}
			summer.clear();

			// 가을
			for (Tree temp : fall) {
				if (temp.age % 5 == 0) {
					// 8방 번식
					int tx, ty;
					for (int i = 0; i < 8; i++) {
						tx = temp.x + dx[i];
						ty = temp.y + dy[i];
						if (tx >= 0 && tx < N && ty >= 0 && ty < N) {
							spring.offer(new Tree(tx, ty, 1));
						}
					}
				}
				spring.offer(temp);
			}
			fall.clear();

			// 겨울
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += food[i][j];
				}
			}

			year++;
		}
		System.out.println(spring.size());
	}

	public static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age > o.age ? 1 : -1;
		}
	}
}

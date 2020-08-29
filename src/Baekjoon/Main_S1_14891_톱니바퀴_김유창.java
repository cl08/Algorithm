package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_14891_톱니바퀴_김유창 {
	static int k, result;
	static Magnet[] magnet;
	static boolean[] visit;

	static class Magnet {
		int[] num; // 자석의 극 정보를 담고 있는 배열
		int top, right, left; // 자석의 위쪽, 오른쪽, 왼쪽 index를 가리킴

		public Magnet(int[] num, int top, int right, int left) {
			super();
			this.num = num;
			this.top = top;
			this.right = right;
			this.left = left;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		magnet = new Magnet[4];
		visit = new boolean[4];
		for (int i = 0; i < 4; i++) {
			String str = br.readLine().trim();
			int[] temp = new int[8];
			for (int j = 0; j < 8; j++) {
				temp[j] = str.charAt(j) - '0';
			}
			magnet[i] = new Magnet(temp, 0, 2, 6);
		}
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < 4; j++) {
				visit[j] = false;
			}
			rotate(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}
		result = 0;
		for (int i = 0; i < 4; i++) {
			if (magnet[i].num[magnet[i].top] == 1)
				result = result + (int) Math.pow(2, i);
		}

		System.out.println(result);
	}

	private static void rotate(int n, int dir) {
		// 한번 돌았던 자석은 또 다시 돌면 안됨
		visit[n] = true;

		// 자신의 좌우 극을 임시로 저장해 둔다
		int right = magnet[n].num[magnet[n].right];
		int left = magnet[n].num[magnet[n].left];

		// dir 1 일경우 시계 방향으로 회전
		if (dir == 1) {
			magnet[n].right = (magnet[n].right + 7) % 8;
			magnet[n].left = (magnet[n].left + 7) % 8;
			magnet[n].top = (magnet[n].top + 7) % 8;
		}

		// dir -1일 경우 반시계 방향으로 회전
		else {
			magnet[n].right = (magnet[n].right + 1) % 8;
			magnet[n].left = (magnet[n].left + 1) % 8;
			magnet[n].top = (magnet[n].top + 1) % 8;
		}

		// 저장해둔 좌우 극을 토대로 좌우 자석들이 연결 되어 있는지 확인 후 좌우 자석들을 회전
		// 왼쪽 자석 확인
		if (n - 1 >= 0 && !visit[n - 1])
			if (magnet[n - 1].num[magnet[n - 1].right] != left) {
				rotate(n - 1, dir * (-1));
			}
		// 오른쪽 자석 확인if(n+1)
		if (n + 1 < 4 && !visit[n + 1]) {
			if (magnet[n + 1].num[magnet[n + 1].left] != right) {
				rotate(n + 1, dir * (-1));
			}
		}
	}
}

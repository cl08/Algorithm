package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_5644_무선충전 {
	static class User {
		int x;
		int y;
		int[] step;

		public User(int x, int y, int[] step) {
			super();
			this.x = x;
			this.y = y;
			this.step = step;
		}

	}

	static class AP {
		int x;
		int y;
		int c;
		int p;
		int using;

		public AP(int x, int y, int c, int p, int using) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
			this.using = using;
		}
	}

	static int M, A, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력 받기
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			User[] user = new User[2];
			for (int i = 0; i < 2; i++) {
				int[] temp = new int[M + 1];
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++)
					temp[j] = Integer.parseInt(st.nextToken());
				if (i == 0)
					user[i] = new User(0, 0, temp);
				else
					user[i] = new User(9, 9, temp);
			}
			AP[] ap = new AP[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				ap[i] = new AP(x, y, c, p, 0);
			}

			// 시뮬 시작
			result = 0;
			for (int i = 0; i <= M; i++) {
				int max = 0;
				// 첫번째 사용자가 BC 범위 안에 있는지 확인
				for (int j = 0; j < A; j++) {
					int user1 = 0;
					// 첫번째 사용자가 BC 범위 안에 있는 경우
					if (Math.abs(ap[j].x - user[0].x) + Math.abs(ap[j].y - user[0].y) <= ap[j].c) {
						ap[j].using++;
						
						// 두번째 사용자가 BC 범위 안에 있는지 확인
						for (int k = 0; k < A; k++) {
							int user2 = 0;
							// 두 사용자가 같은 BC 를 사용중일 경우 처리
							if (Math.abs(ap[k].x - user[1].x) + Math.abs(ap[k].y - user[1].y) <= ap[k].c) {
								ap[k].using++;
								user1 = ap[j].p / ap[j].using;
								user2 = ap[k].p / ap[k].using;
								ap[k].using--;
							}
							// 두번째 사용자가 BC 범위 밖에 있는 경우
							else {
								user1 = ap[j].p / ap[j].using;
							}
							// 최대값을 저장
							int sum = user1 + user2;
							if (max < sum)
								max = sum;
						}
						ap[j].using--;
					} 
					// 첫번째 사용자가 BC 범위 밖에 있는 경우
					else {
						// 두번째 사용자가 BC 범위 안에 있는지 확인
						if (Math.abs(ap[j].x - user[1].x) + Math.abs(ap[j].y - user[1].y) <= ap[j].c) {
							if (max < ap[j].p)
								max = ap[j].p;
						}
					}
				}
				result = result + max;

				// 다음 칸 이동
				for (int j = 0; j < 2; j++) {
					switch (user[j].step[i]) {
					case 1:
						user[j].x--;
						break;
					case 2:
						user[j].y++;
						break;
					case 3:
						user[j].x++;
						break;
					case 4:
						user[j].y--;
						break;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}

package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_D4_8382_방향전환_김유창_Greedy {
	static int startX, startY, endX, endY, count;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");

			// 음수 제거
			startX = Integer.parseInt(st.nextToken()) + 100;
			startY = Integer.parseInt(st.nextToken()) + 100;
			endX = Integer.parseInt(st.nextToken()) + 100;
			endY = Integer.parseInt(st.nextToken()) + 100;

			// 시작점은 0,0 에 가깝게, 도착점은 200,200 에 가깝게 조정
			if (startX > endX) {
				int temp = startX;
				startX = endX;
				endX = temp;
			}
			if (startY > endY) {
				int temp = startY;
				startY = endY;
				endY = temp;
			}

			// flag true면 x이동. false면 y이동
			// 거리가 더 먼 축부터 이동
			if (Math.abs(startX - endX) > Math.abs(startY - endY))
				flag = true;
			else
				flag = false;

			count = 0;

			// 이동하며 count
			while (startX != endX || startY != endY) {
				if (flag) {
					if (startX == endX) {
						startY = startY + 1;
						count = count + 3;
					} else {
						startX = startX + 1;
						count++;
					}
					flag = false;
				} else {
					if (startY == endY) {
						startX = startX + 1;
						count = count + 3;
					} else {
						startY = startY + 1;
						count++;
					}
					flag = true;
				}
			}
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}

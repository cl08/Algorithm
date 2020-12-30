package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_5658_보물상자비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			Queue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String str = br.readLine();
			String sub;
			for (int i = 0; i < N / 4; i++) {

				// 각변 저장
				for (int j = 0; j < N / (N / 4); j++) {
					sub = str.substring((N / 4) * j, (N / 4) * (j + 1));
					// 10진수로 변경해서 저장
					int num = Integer.parseInt(sub,16);
					q.offer(num);
				}

				// 보물 상자 뚜껑 회전
				int size = str.length();
				char[] ch = new char[size];
				ch = str.toCharArray();
				char temp = ch[size - 1];
				for (int j = size - 1; j > 0; j--) {
					ch[j] = ch[j - 1];
				}
				ch[0] = temp;
				str = String.valueOf(ch);
			}

			// k번째로 큰 수 출력
			int result = q.poll();
			int count = 1;
			while (count < K) {
				int temp = q.poll();
				if (temp != result) {
					result = temp;
					count++;
				}
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}

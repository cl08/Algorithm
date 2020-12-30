package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S_D3_1244_최대상금 {
	static int[] numbers;
	static int size, n, max;
	static HashSet<String> s = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			n = Integer.parseInt(st.nextToken());
			size = str.length();
			numbers = new int[size];
			for (int i = 0; i < size; i++) {
				numbers[i] = str.charAt(i) - '0';
			}
			max = 0;
			find(n);
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void find(int n) {
		int value = 0;
		for (int i = 0; i < size; i++) {
			value = value * 10 + numbers[i];
		}
		if (s.contains("" + value + n)) {
			return;
		}
		s.add("" + value + n);
		if (n == 0) {
			if(max < value)
				max = value;
			return;
		}

		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				int temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
				find(n - 1);
				temp = numbers[i];
				numbers[i] = numbers[j];
				numbers[j] = temp;
			}
		}
	}
}

package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_4008_숫자만들기 {
	static int N, min, max;
	static int[] number, operator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			number = new int[N];
			operator = new int[N - 1];
			st = new StringTokenizer(br.readLine(), " ");
			int index = 0;
			for (int j = 0; j < 4; j++) {
				int temp = Integer.parseInt(st.nextToken());
				for (int k = 0; k < temp; k++) {
					operator[index] = j;
					index++;
				}
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			// nextPermutation
			do {
				cal();
			} while (np(operator));
			sb.append('#').append(tc).append(' ').append(max - min).append('\n');
		}
		System.out.println(sb);
	}

	private static boolean np(int[] p) {
		// 1. 교환이 필요한 i-1 위치 찾기(i:꼭대기)
		int i = N - 1 - 1; // i=배열에서 index 자릿수를 의미함
		while (i > 0 && p[i - 1] >= p[i])
			i--;
		if (i == 0)
			return false;

		// 2. i-1 위치값과 교환이 필요한 j위치 찾기
		int j = N - 1 - 1;
		while (p[i - 1] >= p[j])
			j--;

		// 3. i-1위치 값과 j위치값 교환 : i-1 을 한단계 큰 수로 만듦
		int temp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = temp;

		// 4. i위치부터 맨 뒤까지 내림차순 형태로 오름차순 형태로 재조정
		j = N - 1 - 1;
		while (i < j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;
			j--;
		}
		return true;
	}

	private static void cal() {
		// 계산하여 최소값 최대값 저장
		int sum = number[0];
		for (int i = 0; i < N - 1; i++) {
			switch (operator[i]) {
			case 0:
				sum = sum + number[i + 1];
				break;
			case 1:
				sum = sum - number[i + 1];
				break;
			case 2:
				sum = sum * number[i + 1];
				break;
			case 3:
				sum = sum / number[i + 1];
				break;
			}
		}
		if (max < sum)
			max = sum;
		if (sum < min)
			min = sum;
	}
}

package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C02_RecursiveTest {
	static int N, R, totalCount = 0;
	static int[] numbers, pick;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("N입력 : ");
		N = Integer.parseInt(br.readLine());
		System.out.print("R입력 : ");
		R = Integer.parseInt(br.readLine());
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}
		pick = new int[R];
		combination(N, R);
		System.out.println("총 경우의 수 : " + totalCount);
	}

	private static void combination(int n, int r) { // n : 조합의 고려대상 원소. // r : 조합 자리
		if (r == 0) {
			totalCount++;
			System.out.println(Arrays.toString(pick));
			return;
		}
		if (n < r)
			return;
		pick[r - 1] = numbers[n - 1];
		combination(n - 1, r - 1); // 조합에 포함
		combination(n - 1, r); // 조합에 미포함
	}
}

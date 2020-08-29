package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C03_NextPermutationTest {
	static int totalCount = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("N입력 : ");
		int N = Integer.parseInt(br.readLine());
		System.out.print("R입력 : ");
		int R = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] pick = new int[N];
		// 뽑을 R만큼 뒤에서부터 1로 바꿔줌
		for (int i = 0; i < R; i++) {
			pick[N - 1 - i] = 5;
		}
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}

		// Arrays.sort(numbers);
		do {
			for (int i = 0; i < N; i++) {
				if (pick[i] == 5)
					System.out.print(numbers[i] + " ");
			}
			System.out.println();
		} while (np(pick));
		System.out.println(totalCount);
	}

	private static boolean np(int[] p) {
		totalCount++;
		int N = p.length;
		// 1. 교환이 필요한 i-1 위치 찾기(i:꼭지)
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			i--;
		if (i == 0)
			return false;
		// 2. i-1 위치값과 교환이 필요한 j위치 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j])
			j--;

		// 3. i-1위치 값과 j위치값 교환 : i-1 을 한단계 큰 수로 만듦
		int temp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = temp;

		// 4. i위치부터 맨 뒤까지 내림차순 형태로 오름차순 형태로 재조정
		j = N - 1;
		while (i < j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;
			j--;
		}
		return true;
	}
}

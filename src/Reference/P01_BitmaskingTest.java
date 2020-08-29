package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P01_BitmaskingTest {
	static int N, R, totalCount=0;
	static int[] numbers, pick;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("N입력 : ");
		N = Integer.parseInt(br.readLine());
		System.out.print("R입력 : ");
		R = Integer.parseInt(br.readLine());
		numbers = new int[N];
		for(int i=0; i<N; i++) {
			numbers[i] = i+1;
		}
		pick = new int[R];
		permutation(0, 0);
		System.out.println("총 경우의 수 : "+totalCount);
	}

	private static void permutation(int index, int selected) {
		if(index == R) {
			totalCount++;
			System.out.println(Arrays.toString(pick));
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((selected & 1 << i) == 0) {
				pick[index] = numbers[i];
				permutation(index + 1, selected | 1 << i);
			}
		}
	}
}

package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class C01_RecursiveTest {
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
		combination(0, 0);
		System.out.println("총 경우의 수 : " + totalCount);
	}

	private static void combination(int index, int count) { // index : 조합대상 고려 원소 시작인덱스. count : 직전까지 조합한 원소의 수
		if(count == R ) {
			totalCount++;
			//System.out.println(Arrays.toString(pick));
			return ;
		}
		for (int i = index; i < N; i++) {
			pick[count] = numbers[i];
			combination(i+1, count+1);
		}
	}
}

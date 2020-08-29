package Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P02_SwapTest {
	static int N, R, totalCount = 0;
	static int[] numbers;

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
		N = numbers.length;
		permutation(0);
		System.out.println("총 경우의 수 : " + totalCount);
	}
	public static void permutation(int index) {
		if(index == R) {
			totalCount++;
			for(int i=0; i<R; i++) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=index; i<N; i++) {
			swap(index, i);
			permutation(index+1);
			swap(index, i);
		}
	}
	public static void swap(int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}

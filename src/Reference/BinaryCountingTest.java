package Reference;

public class BinaryCountingTest {

	static int N, totalCount;
	static int[] numbers;

	public static void main(String[] args) {
		numbers = new int[] {1,2,3,4};
		N = numbers.length;
		subset(N);
		System.out.println("총 경우의 수 : "+totalCount);
	}
	private static void subset(int n) {
		totalCount = 1<<n;
		for(int i=0; i<totalCount; i++) {
			for(int j=0; j<N; j++) {
				if((i & 1<<j)!=0) {
					System.out.print(numbers[j]+" ");
				}
			}
			System.out.println();
		}
	}
}

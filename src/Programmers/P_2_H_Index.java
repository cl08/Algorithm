package Programmers;
/*
 * 1. 오름 차순 정렬
 * 2. i값을 논문의 갯수로 지정
 * 3. i번이상 인용된 논문이 i개 이고, 나머지 논문이 i번 이하 인용된 i값을 찾을때까지 i를 1씩 줄여가며 반복
*/
import java.util.Arrays;

public class P_2_H_Index {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 0 }));
	}

	public static int solution(int[] citations) {
		int answer = 0;
		Arrays.sort(citations);
		for (int i = 0; i < citations.length / 2; i++) {
			int temp = 0;
			temp = citations[i];
			citations[i] = citations[citations.length - 1 - i];
			citations[citations.length - 1 - i] = temp;
		}
		for (int i = citations.length; i > 0; i--) {
			answer = 0;
			for (int j = 0; j < citations.length; j++) {
				if (citations[j] >= i) {
					answer++;
					if (answer == i && answer >= citations.length - i - 1) {
						return answer;
					}
				}
			}
		}
		return answer;
	}
}
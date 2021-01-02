package Programmers;

import java.util.Arrays;

/*
 * 1. 가로 : brown / 2 - i
 * 2. 세로 : i + 2
 * 3. (가로 - 2) * (세로 - 2) = yellow 가 될 때 가로 세로 값 구하기
 */
public class P_2_카펫 {

	public static void main(String[] args) {
		int brown = 24, yellow = 24;
		System.out.println(Arrays.toString(solution(brown, yellow)));
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int row = 0, column = 0;
		for (int i = 0; i < brown / 2; i++) {
			row = brown / 2 - i;
			column = 2 + i;
			if ((row - 2) * (column - 2) == yellow) {
				answer[0] = row;
				answer[1] = column;
				break;
			}
		}
		return answer;
	}
}

package Programmers;

import java.util.Arrays;

public class P_1_체육복 {

	public static void main(String[] args) {
		int n = 5;
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };
		System.out.println(solution(n, lost, reserve));
	}

	public static int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		int[] students = new int[n];
		Arrays.fill(students, 1);
		for (int i = 0; i < reserve.length; i++) {
			students[reserve[i] - 1]++;
		}
		for (int i = 0; i < lost.length; i++) {
			students[lost[i] - 1]--;
		}
		for (int i = 0; i < n; i++) {
			if (students[i] == 0) {
				if (i > 0 && students[i - 1] > 1) {
					students[i - 1]--;
					students[i]++;
				}
				else if (i < n - 1 && students[i + 1] > 1) {
					students[i + 1]--;
					students[i]++;
				}
				if (students[i] == 0)
					answer++;
			}
		}
		return n - answer;
	}
}

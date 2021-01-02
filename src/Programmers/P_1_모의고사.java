package Programmers;

public class P_1_모의고사 {
	public int[] solution(int[] answers) {
		int[][] mark = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };
		int[] count = { 0, 0, 0 };
		for (int i = 0; i < mark.length; i++) {
			int divide = mark[i].length;
			for (int j = 0; j < answers.length; j++) {
				if (mark[i][j % divide] == answers[j])
					count[i]++;
			}
		}
		int max = 0, max_count = 0;
		for (int i = 0; i < mark.length; i++) {
			if (max < count[i]) {
				max = count[i];
				max_count = 1;
			} else if (max == count[i]) {
				max_count++;
			}
		}
		int[] answer = new int[max_count];
		int index = 0;
		for (int i = 0; i < mark.length; i++) {
			if (count[i] == max) {
				answer[index] = i + 1;
				index++;
			}
		}
		return answer;
	}
}

package Programmers;

import java.util.Arrays;

public class P_1_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };
		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
		String answer = "";
		Arrays.sort(participant);
		Arrays.sort(completion);
		for (int i = 0; i < completion.length; i++) {
			if (participant[i].equals(completion[i])) {
				continue;
			}
			else {
				answer = participant[i];
				break;
			}
		}
		if (answer.length() == 0) {
			answer = participant[participant.length - 1];
		}
		return answer;
	}
}

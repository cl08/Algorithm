package Programmers;

import java.util.ArrayList;

public class P_2_위장 {

	public static void main(String[] args) {
		String[][] clothes = { { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" },
				{ "green_turban", "headgear" } };
		System.out.println(solution(clothes));
	}

	public static int solution(String[][] clothes) {
		int answer = 0;
		ArrayList<String> part = new ArrayList<String>();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		// 의상 종류별 카운트
		for (int i = 0; i < clothes.length; i++) {
			// 이미 있는 의상 종류면 카운트 증가
			boolean flag = true;
			for (int j = 0; j < part.size(); j++) {
				if (part.get(j).equals(clothes[i][1])) {
					counts.set(j, counts.get(j) + 1);
					flag = false;
					break;
				}
			}
			// 새로운 의상 종류면 추가
			if (flag) {
				part.add(clothes[i][1]);
				counts.add(1);
			}
		}
		int sum = 1;
		for (int i = 0; i < counts.size(); i++) {
			sum = sum * (counts.get(i) + 1);
		}
		// 모두 벗은 경우의 수 빼기
		answer = sum - 1;
		return answer;
	}
}

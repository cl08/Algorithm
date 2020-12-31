package Programmers;

import java.util.Arrays;

public class P_2_전화번호목록 {

	public static void main(String[] args) {
		String[] phone_book = { "123", "456", "789" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for (int i = 0; i < phone_book.length - 1; i++) {
			// phone_book[i]가 phone_book[i+1]의 접두사인지 확인
			boolean flag = true;
			for (int j = 0; j < phone_book[i].length(); j++) {
				if (phone_book[i].charAt(j) == phone_book[i + 1].charAt(j)) {
					continue;
				}
				else {
					flag = false;
					break;
				}
			}
			if (flag) {
				return false;
			}
		}
		return answer;
	}

}

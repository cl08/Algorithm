package Programmers;

public class P_2_타겟넘버 {
	static int answer = 0;

	public int solution(int[] numbers, int target) {
		dfs(0, 0, numbers, target);
		return answer;
	}

	public static void dfs(int sum, int count, int[] numbers, int target) {
		if (count == numbers.length) {
			if (sum == target) {
				answer++;
			}
			return;
		}
		dfs(sum + numbers[count], count + 1, numbers, target);
		dfs(sum - numbers[count], count + 1, numbers, target);
	}
}

package Programmers;

public class P_3_단어변환 {
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(solution(begin, target, words));
	}

	static int answer = 0;

	public static int solution(String begin, String target, String[] words) {
		boolean[] visit = new boolean[words.length];
		dfs(begin, target, words, visit, 0);
		return answer;
	}

	public static void dfs(String word, String target, String[] words, boolean[] visit, int count) {
		if (word.equals(target)) {
			if (answer == 0 || count < answer)
				answer = count;
			return;
		}
		for (int i = 0; i < words.length; i++) {
			if (!visit[i] && compare(word, words[i])) {
				visit[i] = true;
				dfs(words[i], target, words, visit, count + 1);
				visit[i] = false;
			}
		}
	}

	// 두 단어를 비교해서 한글자만 틀리면 true, 아니면 false 반환
	public static boolean compare(String word1, String word2) {
		int count = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) == word2.charAt(i))
				continue;
			else
				count++;
			if (count > 1)
				return false;
		}
		if (count == 1)
			return true;
		else
			return false;
	}
}

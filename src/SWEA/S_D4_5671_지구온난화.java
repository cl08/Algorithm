package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S_D4_5671_지구온난화 {
	static int N, M, max;
	static boolean[] alphabet;
	static char[][] word;
	static HashSet<Character> set;
	static ArrayList<Character> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			alphabet = new boolean[26];
			
			set = new HashSet<Character>();
			word = new char[N][];
			for (int i = 0; i < N; i++) {
				word[i] = br.readLine().trim().toCharArray();
				for (int j = 0; j < word[i].length; j++)
					set.add(word[i][j]);
			}
			list = new ArrayList<Character>();
			list.addAll(set);
			if (list.size() <= M) {
				max = N;
			} else {
				max = 0;
				combination(0, 0);
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void combination(int index, int count) {
		if (count == M) {
			int cnt = 0;
			L: for (int i = 0; i < N; i++) {
				int length = word[i].length;
				for (int j = 0; j < length; j++) {
					if (!alphabet[word[i][j] - 'a'])
						continue L;
				}
				cnt++;
			}
			if (max < cnt)
				max = cnt;
			return;
		}
		for (int i = index; i < list.size(); i++) {
			int temp = list.get(i) - 'a';
			alphabet[temp] = true;
			combination(i + 1, count + 1);
			alphabet[temp] = false;
		}
	}
}

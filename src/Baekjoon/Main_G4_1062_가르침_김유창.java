package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_G4_1062_가르침_김유창 {
	static int N, K, max;
	static boolean[] alphabet;
	static char[][] word;
	static HashSet<Character> set;
	static ArrayList<Character> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (K < 5) {
			System.out.println(0);
			return;
		}
		if (K >= 26) {
			System.out.println(N);
			return;
		}

		alphabet = new boolean[26];
		alphabet['a' - 'a'] = true;
		alphabet['c' - 'a'] = true;
		alphabet['i' - 'a'] = true;
		alphabet['n' - 'a'] = true;
		alphabet['t' - 'a'] = true;

		set = new HashSet<Character>();
		word = new char[N][];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			word[i] = str.substring(4, str.length() - 4).toCharArray();

			for (int j = 0; j < word[i].length; j++) {
				char temp = word[i][j];
				if (temp == 'a' || temp == 'c' || temp == 'i' || temp == 'n' || temp == 't')
					continue;
				set.add(temp);
			}
		}
		list = new ArrayList<Character>();
		list.addAll(set);
		if (list.size() <= K - 5) {
			System.out.println(N);
			return;
		}

		max = 0;
		combination(0, 0);
		System.out.println(max);
	}

	public static void combination(int index, int count) {
		if (count == K - 5) {
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

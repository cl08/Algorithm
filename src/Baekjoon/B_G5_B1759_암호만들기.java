package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_G5_B1759_암호만들기 {
	static int L, C;
	static char[] pick, ch;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ch = new char[C];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			ch[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(ch);
		pick = new char[L];
		combination(0, 0);
	}

	private static void combination(int index, int count) { // index : 조합대상 고려 원소 시작인덱스. count : 직전까지 조합한 원소의 수
		if (count == L) {
			int mo = 0, ja = 0;
			for (int i = 0; i < L; i++) {
				if (pick[i] == 'a' || pick[i] == 'e' || pick[i] == 'i' || pick[i] == 'o' || pick[i] == 'u')
					mo++;
				else
					ja++;
			}
			if (mo > 0 && ja > 1) {
				for (int i = 0; i < L; i++) {
					System.out.print(pick[i]);
				}
				System.out.println();
			}
			return;
		}
		for (int i = index; i < C; i++) {
			pick[count] = ch[i];
			combination(i + 1, count + 1);
		}
	}
}

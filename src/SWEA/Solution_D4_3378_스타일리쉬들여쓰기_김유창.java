package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 마스터 코드를 보고 R S C 를 찾아내 내 코드에 적용 하는 문제
 */
public class Solution_D4_3378_스타일리쉬들여쓰기_김유창 {
	// [code index][0]:남은 열린 소괄호 수, [1]:남은 열린 중괄호 수, [2]:남은 열린 대괄호 수, [3]:들여쓰기 수
	static int[][] data, result;
	static int p, q;
	static int R, C, S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			// 입력 받으면서 코드 분석
			data = new int[p][4];
			for (int i = 0; i < p; i++) {
				String line = br.readLine();
				int index = 0;
				while (line.charAt(index) == '.')
					index++;
				data[i][0] = index;
				if (i > 0) {
					data[i][1] = data[i - 1][1];
					data[i][2] = data[i - 1][2];
					data[i][3] = data[i - 1][3];
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						data[i][1]++;
						break;
					case ')':
						data[i][1]--;
						break;
					case '{':
						data[i][2]++;
						break;
					case '}':
						data[i][2]--;
						break;
					case '[':
						data[i][3]++;
						break;
					case ']':
						data[i][3]--;
						break;
					}
				}
			}

			result = new int[q][4];
			for (int i = 0; i < q; i++) {
				String line = br.readLine();
				int index = 0;
				if (i > 0) {
					result[i][1] = result[i - 1][1];
					result[i][2] = result[i - 1][2];
					result[i][3] = result[i - 1][3];
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						result[i][1]++;
						break;
					case ')':
						result[i][1]--;
						break;
					case '{':
						result[i][2]++;
						break;
					case '}':
						result[i][2]--;
						break;
					case '[':
						result[i][3]++;
						break;
					case ']':
						result[i][3]--;
						break;
					}
				}
			}
			for (int i = 0; i < q; i++) {
				result[i][0] = -2;
			}
			for (int R = 1; R <= 20; R++) {
				for (int C = 1; C <= 20; C++) {
					for (int S = 1; S <= 20; S++) {
						if (check(R, C, S)) {
							cal(R, C, S);
						}
					}
				}
			}
			sb.append('#').append(tc).append(" 0");
			for (int i = 1; i < result.length; i++) {
				sb.append(' ').append(result[i][0]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static boolean check(int R, int C, int S) {
		for (int i = 1; i < p; i++) {
			if (data[i][0] != data[i - 1][1] * R + data[i - 1][2] * C + data[i - 1][3] * S) {
				return false;
			}
		}
		return true;
	}

	public static void cal(int R, int C, int S) {
		for (int i = 1; i < q; i++) {
			int temp = result[i - 1][1] * R + result[i - 1][2] * C + result[i - 1][3] * S;
			if (result[i][0] == -2) {
				result[i][0] = temp;
			} else if (result[i][0] != temp) {
				result[i][0] = -1;
			}
		}
	}

}

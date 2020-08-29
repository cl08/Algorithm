package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D4_4366_정식이의은행업무_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			char[] binary = br.readLine().trim().toCharArray();
			char[] tetra = br.readLine().trim().toCharArray();
			int size1 = binary.length;
			int size2 = tetra.length;
			char[] compare = { '0', '1', '2' };
			int result = -1;
			// 2진수 한자리씩 변경
			L: for (int i = 0; i < size1; i++) {
				char temp1 = binary[i];
				if (binary[i] == '1')
					binary[i] = '0';
				else
					binary[i] = '1';
				result = conversion(binary, 2);
				// 3진수 한자리씩 변경
				for (int j = 0; j < size2; j++) {
					char temp2 = tetra[j];
					for (int k = 0; k < 3; k++) {
						if (compare[k] != temp2) {
							tetra[j] = compare[k];
							// 두 수가 맞나 비교
							if (result == conversion(tetra, 3))
								break L;
						}
					}
					// 롤백
					tetra[j] = temp2;
				}
				// 롤백
				binary[i] = temp1;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static int conversion(char[] ch, int num) {
		int ret = 0;
		int size = ch.length;
		int count = 0;
		for (int i = size - 1; i >= 0; i--) {
			ret = ret + (ch[i] - '0') * (int) Math.pow(num, count);
			count++;
		}
		return ret;
	}
}

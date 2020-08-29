package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B3_B16917_양념반후라이드반_김유창 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		int price = 0, min = Integer.MAX_VALUE;

		// 반반 안사기
		price = X * A;
		price = price + Y * B;

		if (min > price)
			min = price;

		// 1.반반으로 양념 다 사기
		price = X * C * 2;
		if (Y - X > 0)
			price = price + (Y - X) * B;
		if (min > price)
			min = price;

		// 2.반반으로 후라이드 다 사기
		price = Y * C * 2;
		if (X - Y > 0)
			price = price + (X - Y) * A;

		if (min > price)
			min = price;

		System.out.println(min);

	}
}

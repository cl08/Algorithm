package JUNGOL;

import java.util.Scanner;

public class Main_1102_스택_김유창 {
	static int top=0;
	static int[] arr = new int[100];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int data;
		int n = sc.nextInt();
		for(int i=0; i<n; i++) {
			String s = sc.next();
			char c = s.charAt(0);
			if(c=='i') {
				data = sc.nextInt();
				push(data);
			}
			else if(c=='o')
				pop();
			else if(c=='c')
				size();
		}
		sc.close();
		
	}

	public static boolean isEmpty() {
		if(top==0)
			return true;
		else
			return false;
	}

	public static void push(int data) {
		arr[top] = data;
		top++;
	}

	public static boolean pop() {
		if(isEmpty()) {
			System.out.println("empty");
			return false;
		}
		else {
			top--;
			System.out.println(arr[top]);
			return true;
		}
	}

	public static void size() {
		System.out.println(top);
	}
}

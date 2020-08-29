package Reference;

import java.util.Arrays;
import java.util.Comparator;

public class ComparableTest {

	static class Student implements Comparable<Student> {
		int no, score;

		public Student(int no, int score) {
			super();
			this.no = no;
			this.score = score;
		}

		@Override
		public String toString() {
			return "Student [no=" + no + ", score=" + score + "]";
		}

		@Override
		public int compareTo(Student o) {
			return this.no - o.no;
		}

	}

	public static void main(String[] args) {
		int[] arr = new int[] { 4, 3, 7, 9, 10 };
		Integer[] arr2 = new Integer[] { new Integer(4), 3, 7, 9, 10 };

		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

		System.out.println(Arrays.toString(arr2));
		Arrays.sort(arr2, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.intValue() - o2.intValue();
//				return o2.compareTo(o1);
			}
		});
		System.out.println(Arrays.toString(arr2));

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>.1.");

		int[][] arr3 = new int[][] { { 1, 10 }, { 3, 50 }, { 8, 50 }, { 2, 80 }, { 4, 10 } };
		Arrays.sort(arr3, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for (int[] ar : arr3) {
			System.out.println(Arrays.toString(ar));
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>.2.");

		Arrays.sort(arr3, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int result = o1[1] - o2[1];
				if (result == 0) {
					result = o1[0] - o2[0];
				}
				return result;
			}
		});
		for (int[] ar : arr3) {
			System.out.println(Arrays.toString(ar));
		}

		Student[] arr4 = new Student[] { new Student(1, 10), new Student(3, 50), new Student(2, 80),
				new Student(4, 10) };

		System.out.println("=========================");
//      Arrays.sort(arr4);
		Arrays.sort(arr4, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o2.score - o1.score;
			}
		});
		for (Student student : arr4) {
			System.out.println(student);
		}
	}

}
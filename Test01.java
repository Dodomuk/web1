package workshop4;

public class Test01 {

	public static void main(String[] args) {
		
		int[][] arr2 = {
				{5,5},
				{10,10,10,10,10},
				{20,20,20},
				{30,30,30,30}
		};
		int total = 0;
		int[] sum = new int[arr2.length];
		double[] averageArr = new double[arr2.length];
		double average = 0;
		int i;
		int j = 0;
		for (i = 0; i < arr2.length; i++) {
			
			sum[i] = 0;
			averageArr[i] = 0;
			for (j = 0; j < arr2[i].length; j++) {
				
				total += arr2[i][j];
				sum[i] += arr2[i][j];
				
			}
			averageArr[i] = (double)(sum[i])/j;
			average += averageArr[i];
		}
		
		
		System.out.println("total=" + total);
		System.out.println("average=" + average/j);
	}

}

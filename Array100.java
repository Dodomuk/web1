package homework;

public class Array100 {

	public static void main(String[] args) {
		
		int[][] arr = new int[4][25];
		
		for (int i = 0; i < 4; i++) {
		   
			for (int j = 0; j < 25; j++) {
				arr[i][j] = (i*25)+(j+1);
				
				if(j == 0) {
					System.out.print("[");
				}else if(j == 24) {
					System.out.println(arr[i][j] + "]");
					break;
				}
				System.out.print(arr[i][j] + ", ");
			}
			
		}
		
		
	}

}

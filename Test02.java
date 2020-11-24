package workshop4;

public class Test02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr3 = new int[5];
		int sum = 0;
		double avg;
		for (int i = 0; i < arr3.length; i++) {
			
			arr3[i] = (int)(Math.random()*11);
			
			for (int j = 0; j < arr3.length; j++) {
				if(i != j) { //자기 자신과 비교하면 안됨
				if(arr3[i] != arr3[j]) {
					continue;
				}else { //만약 자기와 같은 숫자를 가진 배열이 있을 경우 다시 숫자를 입력 받기 위해 i를 하나 뺀다
					i--;
				}
			}
			}
		}
		for (int k = 0; k < arr3.length; k++) {
			System.out.print(arr3[k] + " ");
			sum += arr3[k];
		}
		avg =(double)sum/5;
		
		System.out.println(" ");		
		System.out.println("sum=" + sum);
		System.out.println("avg=" + avg);
	}

}

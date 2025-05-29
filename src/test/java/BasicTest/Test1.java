package BasicTest;

public class Test1 {
	public static int reverse(int x) {
        String result = "";
        if (x<0) {
            result = "-";
            int num = -x;
            while(num!=0){
                result = result + num%10;
                num = (int) num/10;
            }
            
        }
        else if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }
        else if (x==0){
            return 0;
        }
        else{
            while(x!=0){
                result = result + x%10;
                x = (int) x/10;
            }
            
        }
        
    try {
    	return Integer.parseInt(result);
    }
    catch (Exception e) {
    	return 0;
    }
        
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(reverse(1245));

	}

}

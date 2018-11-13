import java.io.*;
 
    class Flu14 {
      static int initial = 10;
      public static void main(String args[]){
        int n,d,m;
        String buf;
        
       	 	try{
          	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Enter Number of days");
            	buf = br.readLine();
            	d = Integer.parseInt(buf);
            	n = flu_in_hamamatsu(d);
            	m= day_of_flu(d);
            	
        if(n>10000)
        System.out.print("More than 10000 people may have flu in day " + m + ".");
        else
        System.out.print("Total " + n + " people may have flu in day " + d + ".");
        	} catch(Exception e){
            	System.err.print("Error:" + e);
            }
            
        return;
      }
      
      static int day_of_flu(int day) {
      	int i,r;
      
        r = initial;
      
        for( i = 0; i < day; i++ ) {
         r=one_percent_increase_of(r);
         if(r>10000){
         r=i;
         break;
         }
        }
        
        return r;
      
      
      }
      
     
      static int flu_in_hamamatsu(int day) {
        int i,r;
      
        r = initial;
      
        for( i = 0; i < day; i++ ) {
         r=one_percent_increase_of(r);
         
        }
        
        return r;
      }
     
      static int one_percent_increase_of(int num) {
        int r;
     
        r = num;
        r *= 1.01;
        if( r < 100 && (int)(Math.random() * 100) < 1 ) {
          r += 1;
        }
      
        return r;
      }
    }

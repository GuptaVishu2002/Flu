    class Flu_rp11 {
      static final int initial = 10;
     
      public static void main(String args[]){
        int n,d;
        d = 300;
        n = flu_in_hamamatsu(d);
        
        System.out.print("Total " + n + " people may have flu in day " + d + "\n");
        
        return;
      }
      
      static int flu_in_hamamatsu(int day) {
        int i,r;
        
        if( day == 0 ) {
          return initial;
        } else {
          r = flu_in_hamamatsu(day-1);
          r *= 1.01;
          if( r < 100 && (int)(Math.random() * 100) < 1 ) {
            r += 1;
          }
        }
        return r;
      }
    }

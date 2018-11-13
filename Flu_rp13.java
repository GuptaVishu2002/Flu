    class Flu_rp13 {
      static final int initial = 10;
      public static void main(String args[]){
        int n,d;
        d = 10000;
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
          r += flu_in_iwata(day-1)*0.01;
          r += flu_in_tokyo(day-1)*0.01;
          r *= 1.01;
          if( r < 100 && day % 10 == 0) {
            r += 1;
          }
        }
        
        return r;
      }  
      
      static int flu_in_iwata(int day) {
        int i,r;
        
        if( day == 0 ) {
          return initial;
        } else {
          r = flu_in_iwata(day-1);
          /* r += flu_in_hamamatsu(day-1) * 0.1; */
          r *= 1.01;
          if( r < 100 && day % 10 == 0) {
            r += 1;
          }
        }
        
        return r;
      }  
     
      static int flu_in_tokyo(int day) {
        int i,r;
        
        if( day == 0 ) {
          return initial;
        } else {
          r = flu_in_tokyo(day-1);
          r *= 1.10;
          if( r < 10 && day % 10 == 0 ) {
            r += 1;
          }
        }
        
        return r;
      }
    }

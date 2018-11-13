    class Flu_rp14 {
      static final int initial = 10;
      static int mid;
      static int end;
     
      static int flu_in_hamamatsu_history[] = new int[10000];
      static int flu_in_iwata_history[]     = new int[10000];
      static int flu_in_tokyo_history[]     = new int[10000];
      
      public static void main(String args[]){
        int i, n, d;
        d = 9999;
     
        for (i = 0; i <= d; i++){
          flu_in_hamamatsu_history[i] = 0;
          flu_in_iwata_history[i] = 0;
          flu_in_tokyo_history[i] = 0;
        }
          
        n = flu_in_hamamatsu(d);
        
        System.out.print("Total " + n + " people may have flu in day " + d + "\n");
        
        return;
      }
     
      static int flu_in_hamamatsu(int day) {
        int i,r;
        int x=0;
        
        r = initial;
        
        if( x <= day ) {
          return initial;
        } else if( flu_in_hamamatsu_history[day] != 0 ) {
          return flu_in_hamamatsu_history[day];
        } else if( day == 300){
        	mid = r;
        	return mid;
        } else if(r>10000){
        	end = day;
        	return end;
        }else {
          r = flu_in_hamamatsu(x);
          x=x+1;
          r += flu_in_iwata(x)*0.01;
          r += flu_in_tokyo(x)*0.01;
          r *= 1.01;
          if( r < 100 && day % 10 == 0) {
            r += 1;
          }
          flu_in_hamamatsu_history[day] = r;
        }
        
        return r;
      }  
      
      static int flu_in_iwata(int day) {
        int i,r;
        int x=0;
        
        if( x <= day ) {
          return initial;
        } else if( flu_in_iwata_history[day] != 0 ) {
          return flu_in_iwata_history[day];
        } else {
          r = flu_in_iwata(x);
          r += flu_in_hamamatsu(x) * 0.1;
          r *= 1.01;
          if( r < 100 && day % 10 == 0) {
            r += 1;
          }
        }
        x=x+1;
        return r;
      }  
     
      static int flu_in_tokyo(int day) {
        int i,r;
        int x=0;
        
        System.out.print("call: flu_in_tokyo(" + day + ")\n");
        
        if( x <= day ) {
          return initial;
        } else if( flu_in_tokyo_history[day] != 0 ) {
          return flu_in_tokyo_history[day];
        } else {
          r = flu_in_tokyo(day-1);
          r *= 1.10;
          if( r < 10 && day % 10 == 0 ) {
            r += 1;
          }
          flu_in_tokyo_history[day] = r;
        }
        x=x+1;
        return r;
      }
    }

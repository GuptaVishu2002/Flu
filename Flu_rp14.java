    class Flu_rp14 {
      static final int initial = 10;
     
      static int flu_in_hamamatsu_history[] = new int[30];
      static int flu_in_iwata_history[]     = new int[30];
      static int flu_in_tokyo_history[]     = new int[30];
      
      public static void main(String args[]){
        int i, n, d;
        d = 29;
     
        for (i = 0; i <= d; i++){
          flu_in_hamamatsu_history[i] = 0;
          flu_in_iwata_history[i] = 0;
          flu_in_tokyo_history[i] = 0;
        }
          
        n = flu_in_hamamatsu(d);
        
        System.out.print("Total " + n + " people may have flu in day " + d +
        "\n");
        
        return;
      }
     
      static int flu_in_hamamatsu(int day) {
        int i,r;
        
        System.out.print("call: flu_in_hamamatsu(" + day + ")\n");
        
        if( day == 0 ) {
          return initial;
        } else if( flu_in_hamamatsu_history[day] != 0 ) {
          return flu_in_hamamatsu_history[day];
        } else {
          r = flu_in_hamamatsu(day-1);
          r += flu_in_iwata(day-1)*0.01;
          r += flu_in_tokyo(day-1)*0.01;
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
        
        System.out.print("call: flu_in_iwata(" + day + ")\n");
        
        if( day == 0 ) {
          return initial;
        } else if( flu_in_iwata_history[day] != 0 ) {
          return flu_in_iwata_history[day];
        } else {
          r = flu_in_iwata(day-1);
          r += flu_in_hamamatsu(day-1) * 0.1;
          r *= 1.01;
          if( r < 100 && day % 10 == 0) {
            r += 1;
          }
        }
        
        return r;
      }  
     
      static int flu_in_tokyo(int day) {
        int i,r;
        
        System.out.print("call: flu_in_tokyo(" + day + ")\n");
        
        if( day == 0 ) {
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
        return r;
      }
    }

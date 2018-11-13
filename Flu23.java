    class Flu23 {
    
     final static int REGION_SIZE = 20;
    
      public static void main(String args[]){
        
        int n,i,j;
        int n_in_region[][] = new int[REGION_SIZE][REGION_SIZE];
      
        array_input(n_in_region);
        
        for( n = 0; n < 1000; n++ ){
          for( i = 0; i < REGION_SIZE; i++ ) {
            for( j = 0; j < REGION_SIZE; j++ ) {
              n_in_region[i][j] = one_percent_increase(n_in_region[i][j]);
              if( i > 0 ) {
                n_in_region[i][j] +=
                  one_percent_increase(n_in_region[i-1][j])
                    - n_in_region[i-1][j];
              }
              if( i < REGION_SIZE-1 ) {
                n_in_region[i][j] +=
                  one_percent_increase(n_in_region[i+1][j])
                    - n_in_region[i+1][j];
              }
            }
          }
          System.out.print("day " + n + ":\n");
          array_print(n_in_region);
          
        }
        
        return;
      }
      
      static int one_percent_increase(int num) {
        int r;
     
        r = num;
        r *= 1.01;
        if( r < 100 && (int)(Math.random() * 100) < 1 ) {
          r += 1;
        }
        return r;
      }
      
      static void array_input(int array[][]){
      
      
       for(int i = 0; i < REGION_SIZE; i++ ) {
          for(int j = 0; j < REGION_SIZE; j++ ) {
            array[i][j] = 0;
          }
        }
      
      }
      
      static void array_print(int array[][]){
      
      for(int i = 0; i < REGION_SIZE; i++ ) {
            for(int j = 0; j < REGION_SIZE; j++ ) {
              System.out.printf("%3d ",array[i][j]);
            }
            System.out.print("\n");
          }
      
      
      }
      
 
    }

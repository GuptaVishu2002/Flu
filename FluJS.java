    class FluJS {
      public static void main(String args[]){
        System.out.print("<html><head> <script type=\"text/javascript\">  var count = 0;  var pandem = [");
        calc();
        System.out.print(" ];  function doAnimLoop() {   var p1 = pandem[count];   for( var j = 0; j < p1.length; j++ ) {    var p2 = p1[j];    for( var i = 0; i < p2.length; i++ ) {     el = document.getElementById('t'+j+'_'+i);     if( el != null ) {       el.style.background= 'rgb(' + (100 - 100/(p2[i]+1)) + '%, 0%, 0%)';     }    }   }   count++;   if( count < pandem.length ) { 	setTimeout(doAnimLoop,20);   }  }  function doAnimStart() {   placeBlocks();   doAnimLoop(); } function placeBlocks() {   var xsize = pandem[0][0].length;   var ysize = pandem[0].length;   var wd = 30;   var ht = 30;   var mg = 10;   var rootObj = document.getElementById('rootObj');   for( var j = 0; j < ysize; j++ ) {    for( var i = 0; i < xsize; i++ ) {     var el = document.createElement('div');     el.style.position='absolute';     el.style.left = (i*wd+mg/2) + 'px';     el.style.top = (mg*3 + ht*j) + 'px';     el.style.width= (wd - mg) + 'px';     el.style.height = (ht-mg) + 'px';     el.style.background= 'rgb(' + (100 - 100/(i+1)) + '%, 0%, 0%)';     el.id=('t'+j+'_'+i);     document.body.insertBefore(el,rootObj);    }   } } window.onload = doAnimStart; </script> </head><body><div id=\"rootObj\"></div></body></html>");
      }
      
      final static int REGION_SIZE = 20;
      
      static void calc() {
        
        
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
          // System.err.print("day " + n + ":\n");
          System.out.print("[");
		  array_print(n_in_region);	
          System.out.print("],\n");
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
            System.out.print("[");
            for(int j = 0; j < REGION_SIZE; j++ ) {
              System.out.printf("%3d,",array[i][j]);
            }
            System.out.print("],\n");
          }
      
      
      }
      
      
    }

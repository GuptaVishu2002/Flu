import java.io.*;

    class Flu3 {
      public static void main(String args[]){
      
		int a_in_region[][] = new int[REGION_SIZE][REGION_SIZE];
        int b_in_region[][] = new int[REGION_SIZE][REGION_SIZE];
        int c_in_region[][] = new int[REGION_SIZE][REGION_SIZE];  
        int option;
        
   		String buf;
   		
   		System.out.print("Enter the disease spread case 1/2/3");
            
            try{
            
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    buf = br.readLine();
		    option = Integer.parseInt(buf);
		  		    
		    if(option==1)
        		array_web_print(a_in_region,1);
        	if(option==2)	
                array_web_print(b_in_region,2);
            if(option==3)    
                array_web_print(c_in_region,3);
            }catch(Exception e){
            	System.err.print("Error:" + e);
            }   
        
      }
      
      final static int REGION_SIZE = 20;
      
      static void calc1(int a_in_region[][]) {
         
        int n,i,j;
        
      
        array_input(a_in_region);
        
        for( n = 0; n < 1000; n++ ){
          for( i = 0; i < REGION_SIZE; i++ ) {
            for( j = 0; j < REGION_SIZE; j++ ) {
              a_in_region[i][j] = one_percent_increase(a_in_region[i][j]);
              if( i > 0 ) {
                a_in_region[i][j] +=  one_percent_increase(a_in_region[i-1][j]) - a_in_region[i-1][j];
              }
              if( i < REGION_SIZE-1 ) {
                a_in_region[i][j] += one_percent_increase(a_in_region[i+1][j]) - a_in_region[i+1][j];
              }
            }
          }
           
          array_data_print(a_in_region);
        }
        
        return;
      }
      
      static void calc2(int a_in_region[][]) {
        
        
        int n,i,j;
        
      
        array_input(a_in_region);
        
        for( n = 0; n < 1000; n++ ){
          for( i = 0; i < REGION_SIZE; i++ ) {
            for( j = 0; j < REGION_SIZE; j++ ) {
              a_in_region[i][j] = one_percent_increase(a_in_region[i][j]);
              if( i > 0 ) {
                a_in_region[i][j] +=  one_percent_increase(a_in_region[i-1][j]) - a_in_region[i-1][j];
              }
              if( j > 0 ) {
                a_in_region[i][j] +=  one_percent_increase(a_in_region[i-1][j]) - a_in_region[i-1][j];
              }
              if( i < REGION_SIZE-1 ) {
                a_in_region[i][j] += one_percent_increase(a_in_region[i+1][j]) - a_in_region[i+1][j];
              }
              if( j < REGION_SIZE-1 ) {
                a_in_region[i][j] += one_percent_increase(a_in_region[i+1][j]) - a_in_region[i+1][j];
              }
            }
          }
           
          array_data_print(a_in_region);
        }
        
        return;
      }
      
      static void calc3(int a_in_region[][]) {
        
        
        int n,i,j,ch;
        int min = 0;
        int max = 4;
        
      
        array_input(a_in_region);
        
        for( n = 0; n < 1000; n++ ){
          for( i = 0; i < REGION_SIZE; i++ ) {
            for( j = 0; j < REGION_SIZE; j++ ) {
              a_in_region[i][j] = one_percent_increase(a_in_region[i][j]);
               ch= min + (int)(Math.random() * (max-min));
		      switch(ch){
		          case 0 :if( i > 0 ) {
						    a_in_region[i][j] +=  one_percent_increase(a_in_region[i-1][j]) - a_in_region[i-1][j];
						  }
						  break;
		          case 1 :if( j > 0 ) {
						    a_in_region[i][j] +=  one_percent_increase(a_in_region[i-1][j]) - a_in_region[i-1][j];
						  }
						  break;
		          case 2 :if( i < REGION_SIZE-1 ) {
						    a_in_region[i][j] += one_percent_increase(a_in_region[i+1][j]) - a_in_region[i+1][j];
						  }
						  break;
		          case 3 :if( j < REGION_SIZE-1 ) {
						    a_in_region[i][j] += one_percent_increase(a_in_region[i+1][j]) - a_in_region[i+1][j];
						  }
						  break;
              }
            }
          }
           
          array_data_print(a_in_region);
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
      
      static void array_data_print(int array[][]){
      
       System.out.print("[");
	   array_print(array);	
       System.out.print("],\n");
      
      }
      
      static void array_web_print(int array[][],int option){
      
      System.out.print("<html><head> <script type=\"text/javascript\">  var count = 0;  var pandem = [");
      if(option==1)
        calc1(array);
      if(option==2)
      	calc2(array);
      if(option==3)  
      	calc3(array);
        System.out.print(" ];  function doAnimLoop() {   var p1 = pandem[count];   for( var j = 0; j < p1.length; j++ ) {    var p2 = p1[j];    for( var i = 0; i < p2.length; i++ ) {     el = document.getElementById('t'+j+'_'+i);     if( el != null ) {       el.style.background= 'rgb(' + (100 - 100/(p2[i]+1)) + '%, 0%, 0%)';     }    }   }   count++;   if( count < pandem.length ) { 	setTimeout(doAnimLoop,20);   }  }  function doAnimStart() {   placeBlocks();   doAnimLoop(); } function placeBlocks() {   var xsize = pandem[0][0].length;   var ysize = pandem[0].length;   var wd = 30;   var ht = 30;   var mg = 10;   var rootObj = document.getElementById('rootObj');   for( var j = 0; j < ysize; j++ ) {    for( var i = 0; i < xsize; i++ ) {     var el = document.createElement('div');     el.style.position='absolute';     el.style.left = (i*wd+mg/2) + 'px';     el.style.top = (mg*3 + ht*j) + 'px';     el.style.width= (wd - mg) + 'px';     el.style.height = (ht-mg) + 'px';     el.style.background= 'rgb(' + (100 - 100/(i+1)) + '%, 0%, 0%)';     el.id=('t'+j+'_'+i);     document.body.insertBefore(el,rootObj);    }   } } window.onload = doAnimStart; </script> </head><body><div id=\"rootObj\"></div></body></html>");
      }
      
      
    }

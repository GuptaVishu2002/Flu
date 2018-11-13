    class R05_Step3 {
      public static void main(String args[]){
        System.out.print("<html><head> <script type=\"text/javascript\">");
		PandemBase array[] = {new PandemBase(0),new PandemEveryDirection(1), new PandemOneDirection(2)};
		
		for(PandemBase o : array){
			o.Calc();
			o.PrintFunctions();
		}
        System.out.print("function doAnimStart() {");
		for(PandemBase o : array){
			o.InsertFunctions();
		}        
        System.out.print("}window.onload = doAnimStart; </script> </head><body><div id=\"rootObj\"></div></body></html>");
        }
	}
	
	//感染症のシミュレーションを行う基底クラス
	class PandemBase
	{
		//定数
	    protected static final int REGION_SIZE = 20;	//二次元配列の縦横の大きさ
	    
	    //メンバ変数
	    protected int m_num;
	    
	    //コンストラクタ
	    PandemBase(int num)
	    {
    		m_num = num;
    	}
    	    		    
	    //二次元配列の初期化を行う
	    protected void InitArray(int n_in_region[][])
	    {
	    	for(int[] o1 : n_in_region){
	    		for(int i=0; i<o1.length; ++i){
	    			o1[i] = 0;
	    		}
	    	}
	    }
	    
	    //二次元配列の表示
	    protected void PrintArray(int n_in_region[][])
	    {
	    	//System.out.print("var pandem" + m_num + " = [");
			System.out.print("[");
          	for( int i = 0; i < REGION_SIZE; i++ ) {
            	System.out.print("[");
            	for( int j = 0; j < REGION_SIZE; j++ ) {
              		System.out.printf("%3d,",n_in_region[i][j]);
            	}
            	System.out.print("],\n");
	    	}
	    }
		
		//n%増加させた値を返す(計算方法はStep2のプログラムのone_percent_increaseを踏襲)
		protected int n_percent_increase(int num, int n)
		{
		    int r;
		 
		    r = num;
		    r *= (1.0 + (float)n/100);
		    if( r < 100 && (int)(Math.random() * 100) < 1 ) {
				r += 1;
		    }
	        return r;
		}
		
		//確率計算を行う		
		protected void CalcProbability(int[][] n_in_region)
		{
			for( int i = 0; i < REGION_SIZE; i++ ) {
				for( int j = 0; j < REGION_SIZE; j++ ) {
					n_in_region[i][j] = n_percent_increase(n_in_region[i][j],1);
					if( i > 0 ) {
						n_in_region[i][j] += n_percent_increase(n_in_region[i-1][j],1) - n_in_region[i-1][j];
					}
					if( i < REGION_SIZE-1 ) {
					n_in_region[i][j] += n_percent_increase(n_in_region[i+1][j],1) - n_in_region[i+1][j];
					}
				}
			}
		}						
		
		//計算を行い、アニメーション用の配列を出力
		public void Calc()
		{
		    int n,i,j;
		    int n_in_region[][] = new int[REGION_SIZE][REGION_SIZE];
		    
		    InitArray(n_in_region);
			
			System.out.print("var count"+m_num+" = 0; var pandem" + m_num + " = [");
        	for( n = 0; n < 1000; n++ ){
        		CalcProbability(n_in_region);
				PrintArray(n_in_region);
		      	System.out.print("],\n");
        	}
        	System.out.print(" ];");
		}
		
		//javaスクリプト用の関数を出力
		public void PrintFunctions()
		{
			System.out.print("function doAnimLoop" + m_num +"() {   var p1 = pandem" + m_num +"[count" + m_num +"];   for( var j = 0; j < p1.length; j++ ) {    var p2 = p1[j];    for( var i = 0; i < p2.length; i++ ) {     el = document.getElementById('t" + m_num +"'+j+'_'+i);     if( el != null ) {       el.style.background= 'rgb(' + (100 - 100/(p2[i]+1)) + '%, 0%, 0%)';     }    }   }   count" + m_num +"++;   if( count" + m_num +" < pandem" + m_num +".length ) { 	setTimeout(doAnimLoop" + m_num +",20);   }  } function placeBlocks" + m_num +"() {   var xsize = pandem" + m_num +"[0][0].length;   var ysize = pandem" + m_num +"[0].length;   var wd = 30;   var ht = 30;   var mg = 10;   var rootObj = document.getElementById('rootObj');   for( var j = 0; j < ysize; j++ ) {    for( var i = 0; i < xsize; i++ ) {     var el = document.createElement('div');     el.style.position='absolute';     el.style.left = ( ((wd+1)*xsize)*"+m_num+"+ i*wd+mg/2) + 'px';     el.style.top = (mg*3 + ht*j) + 'px';     el.style.width= (wd - mg) + 'px';     el.style.height = (ht-mg) + 'px';     el.style.background= 'rgb(' + (100 - 100/(i+1)) + '%, 0%, 0%)';     el.id=('t"+m_num+"'+j+'_'+i);     document.body.insertBefore(el,rootObj);    }   } }");
		}
		
		//javascriptのdoAnimStartに登録する関数を出力する
		public void InsertFunctions()
		{
			System.out.print("placeBlocks"+m_num+"();   doAnimLoop"+m_num+"();");
		}
		
		//ゲッター
		public int GetNum()
		{
			return m_num;
		}
		
		//セッター
		public void SetNum(int num)
		{
			m_num = num;
		}
	}
	
	//感染ルートを4方に拡張したクラス	
	class PandemEveryDirection extends PandemBase{
		
		//コンストラクタ
		PandemEveryDirection(int num)
		{
			super(num);
		}
		
		//感染ルートを4方に拡張に拡張(オーバーライド)
		protected void CalcProbability(int[][] n_in_region)
		{
			for( int i = 0; i < REGION_SIZE; i++ ) {
				for( int j = 0; j < REGION_SIZE; j++ ) {
					n_in_region[i][j] = n_percent_increase(n_in_region[i][j],1);
					if( i > 0 ) {
						n_in_region[i][j] += n_percent_increase(n_in_region[i-1][j],1) - n_in_region[i-1][j];
					}
					if( i < REGION_SIZE-1 ) {
						n_in_region[i][j] += n_percent_increase(n_in_region[i+1][j],1) - n_in_region[i+1][j];
					}
					
					if(j>0){
						n_in_region[i][j] += n_percent_increase(n_in_region[i][j-1],1) - n_in_region[i][j-1];
					}

					if( j < REGION_SIZE-1 ) {
						n_in_region[i][j] += n_percent_increase(n_in_region[i][j+1],1) - n_in_region[i][j+1];
					}
				}
			}
		}						
	}
	
	//感染ルートを一つにし、感染力をあげる
	class PandemOneDirection extends PandemBase{
		
		//コンストラクタ
		PandemOneDirection(int num)
		{
			super(num);
		}
		
		//感染ルートを4方に拡張に拡張(オーバーライド)
		protected void CalcProbability(int[][] n_in_region)
		{
			int num;
			for( int i = 0; i < REGION_SIZE; i++ ) {
				for( int j = 0; j < REGION_SIZE; j++ ) {
					num = j%4;
					n_in_region[i][j] = n_percent_increase(n_in_region[i][j],5);
					
					switch(num){	//jの値によって感染する方向を決定する(感染のタイプを増やしてるだけなので、この処理自体に理論的裏付けがあるわけではない)
						case 0:
							if( i > 0 ) {
								n_in_region[i][j] += n_percent_increase(n_in_region[i-1][j],5) - n_in_region[i-1][j];
							}
							break;
						
						case 1:
							if( i < REGION_SIZE-1 ) {
								n_in_region[i][j] += n_percent_increase(n_in_region[i+1][j],5) - n_in_region[i+1][j];
							}
							break;
						
						case 2: 
							if(j>0){
								n_in_region[i][j] += n_percent_increase(n_in_region[i][j-1],5) - n_in_region[i][j-1];
							}
							break;
						case 3:
							if( j < REGION_SIZE-1 ) {
								n_in_region[i][j] += n_percent_increase(n_in_region[i][j+1],5) - n_in_region[i][j+1];
							}
							break;
					}
				}
			}
		}						
	}
	
	


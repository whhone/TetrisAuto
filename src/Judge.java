/* Judge is the class for deciding what to do */

public class Judge {
	
	/* cost for an empty square below a tetris */
	static int emptyCost = 12; 

	/* cost for the height */
	static int heightCost = 1;

	/* cost for the different between two column */
	static int diffCost = 1;

	static int lastClear = 0;
	static int thisClear = 0;


	static public int utility(boolean[][] block){
		boolean[] del = new boolean[20];
		int ret = 0;
		int[] hh = new int[10];
		int h = 0;
		int low=20;
		int clear = 0;
		for (int y=0;y<18;++y){
			int cnt = 0;
			for (int x=0;x<10;++x){
				if (block[x][y]){
					++cnt;
					hh[x] = y;
					if (low > y) low = y;
				}
			}
			if (cnt % 10 == 0){
				del[y] = true;
				if (cnt==10) ret -= 3;
				if (cnt==10) ++clear;
			}else{
				h = y;
			}
			
		}
		/*
		if (lastClear == 0){
			if (clear == 1) ret += 40;
		}else if (lastClear > 0){
			if (clear > 0) ret -= 40;
			else ret += 40;
		}
		*/
		thisClear = clear;
		
		for (int x=0;x<block.length;++x){
			ret += h - hh[x];
			if (x < 9) ret += Math.abs(hh[x]-hh[x+1]) * diffCost;
			if (x>0 && x<9 && hh[x]<hh[x-1] && hh[x]<hh[x+1]){
				ret += (Math.max(hh[x-1],hh[x+1]) - hh[x]) * diffCost;
			}
			
			for (int y=low;y<20;++y){
				if (del[y]) continue;
				
				if (block[x][y]==true){
					//ret += empty*emptyCost + y*heightCost;
					int cnt = 0;
					for (int j=1;cnt <= 1 && j<=5;++j){
						if (hh[x]>low && y-j>=0 && y-j>=low && block[x][y-j]==false){
							ret += emptyCost / j;
						}else{
							++cnt;
						}
					}
				}else{
					
				}
			}
		}
		return ret;
	}
	
	void copy2Darray(boolean[][] source, boolean[][] dest){
		for (int i=0;i<source.length;++i){
			System.arraycopy(source[i], 0, dest[i], 0, source[i].length);
		}
	}
	
	public TetrisBlock Test(boolean[][] block, int next){
		
		int arot = 0, ax = 0;
		int best = 10000;
		
		boolean[][] tmp = new boolean[10][20];
		
		for (int rot=0;rot<4;++rot){
			for (int x=0;x+TetrisBlock.config[next][rot].length <= 10;++x){
				int y = 16;
				boolean ok = true;
				
				copy2Darray(block, tmp);
				while (ok && y>=1){
					--y;
					for (int i=0;i<TetrisBlock.config[next][rot].length;++i){
						if (tmp[x+i][ y + TetrisBlock.config[next][rot][i][0] ] == true){
							ok = false;
							y++;
						}
					}
				}
				for (int i=0;i<TetrisBlock.config[next][rot].length;++i){
					int t = TetrisBlock.config[next][rot][i][0];
					for (int j=0;j<TetrisBlock.config[next][rot][i][1];++j){
						tmp[x+i][y+j+t] = true;
					}
				}
				
				int util = utility(tmp);
				if (util < best){
					arot = rot;
					ax = x;
					best = util;
					lastClear = thisClear;
				}		
			}
		}
		//TetrisBot.print(bes);
		System.out.println(best);
		TetrisBlock action = new TetrisBlock(next, arot, ax, best);
		return action;
	}
}

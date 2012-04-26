/* Analyse the screen captured by TetrisBot */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Viewer{

	/* the lenght of a square in the game */
	int d = 18;
	
	public boolean[][] block;
	public BufferedImage screen;
	public int next;
	public int[] corner = new int[2];
	
	
	public int block(Color ccc){
		int r = ccc.getRed() , g = ccc.getGreen() , b = ccc.getBlue();
		
		System.out.printf("red = %d %d %d\n", r, g, b);
		//try {
			//Thread.sleep(6000);
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		if (r==255 && g==194 && b==37) return 0;
		if (r==50 && g==190 && b==250) return 1;
		if (r==250 && g==50 && b==90) return 2;
		if (r==124 && g==212 && b==36) return 3;
		if (r==210 && g==76 && b==173) return 4;
		if (r==68 && g==100 && b==233) return 5;
		if (r==255 && g==126 && b==37) return 6;
		
		return 0;
	}
	
	public void update(){
		block = new boolean[10][20];
		boolean ok = false;
		for (int i=0;i<=4;++i){
			if (!isEmpty(4,19-i)){
				next = block(new Color(screen.getRGB(colX(4),rowY(19-i))) );
				ok = true;
				break;
			}
		}
		
		System.out.printf("block %d\n",next);
		
		for (int x=0;x<10;++x){
			for (int y=0;y<18;++y){
				block[x][y] = (!isEmpty(x,y));
			}
		}
	}
	
	public boolean update(BufferedImage scn){
		screen = scn;
		boolean ok = false;
		for (int i=0;i<=4;++i){
			if (!isEmpty(4,19-i)){
				ok = true;
				break;
			}
		}
		if (ok)	update();
		else return false;
		return true;
	}
	
	public int getNext(){
		return next;
	}
	
	public boolean[][] getBlock(){
		return block;
	}
	
	public boolean isEmpty(Color ccc){
		return (ccc.getBlue()==ccc.getGreen() && ccc.getRed()==ccc.getBlue());
	}
	
	public boolean isEmpty(int c,int r){
		Color ccc = new Color(screen.getRGB(colX(c), rowY(r)));
		//System.out.printf("%d %d %d\n", ccc.getRed(), ccc.getGreen(), ccc.getBlue());
		return isEmpty(ccc);
	}
	
	public int colX(int col){
		return corner[0] + col * d;
	}
	
	public int rowY(int row){
		return corner[1] - row * d;
	}
	
	public int[] getCorner(){
		for (int x=50;x<=200;++x){
			for (int y=800;y>=500;--y){
				boolean ok = true;
				int lastc=0;
				for (int xx=0;ok==true && xx<=100;++xx){	
					int c = screen.getRGB(x+xx, y);
					if (c==-1) ok = false;
					//System.out.printf("%d %d\n",c,lastc);
					
					if (xx==0){
					}else 
						if (c!=lastc){
						ok = false;
						System.out.printf("%d\n",c);
					}
					lastc = c;
				}
				for (int yy=0;ok==true && yy<=300;++yy){	
					int c = screen.getRGB(x, y-yy);
					if (yy==0){
					}else if (c!=lastc){
						ok = false;
					}
					lastc = c;
				}
				if (ok){
					corner[0] = x+15;
					corner[1] = y-7;
					return corner;
				}
			}
		}
		return corner;
	}

}

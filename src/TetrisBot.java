// TetrisBot extends the java.awt.Robot which is able to 
// read pixel from screen and control the mouse and keyboard. 

import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


public class TetrisBot extends Robot {
	
	public TetrisBot() throws AWTException {
	}
	
	public Judge judge = new Judge();
	public Viewer viewer = new Viewer();
	public Rectangle rect = new Rectangle(0,0,600,900);
	int delay = 45;
	
	public void keyTouch(int keycode){
		this.keyPress(keycode);
		this.delay(delay);
		this.keyRelease(keycode);
		this.delay(delay); 
	}
	
	public void mouseClick(int button){
		this.mousePress(button);
		this.delay(delay);
		this.mouseRelease(button);
		this.delay(delay);
	}

	public void hardDrop(){
		this.keyTouch(KeyEvent.VK_SPACE);
	}
	
	public void left(int cnt){
		for (int i=0;i<cnt;++i){
			this.keyTouch(KeyEvent.VK_LEFT);
		}
	}
	public void right(int cnt){
		for (int i=0;i<cnt;++i){
			this.keyTouch(KeyEvent.VK_RIGHT);
		}
	}
	public void hold(){
		this.keyTouch(KeyEvent.VK_SHIFT);
	}

	public void rotate(int cnt){
		if (cnt==3) this.keyTouch(KeyEvent.VK_Z);
		else{
			for (int i=0;i<cnt;++i)
				this.keyTouch(KeyEvent.VK_X);
		}
	}
	
	static public void print(boolean[][] block){
		for (int y=18;y>=0;--y){
			for (int x=0;x<10;++x){
				if (block[x][y]) System.out.print("*");
				else System.out.print(".");
			}
			System.out.print("\n");
		}
	}
	
	public void solve(TetrisBlock tb){
		this.rotate(tb.rot);
		this.left(TetrisBlock.left[tb.type][tb.rot] - tb.x);
		this.right(tb.x - TetrisBlock.left[tb.type][tb.rot]);
		this.hardDrop();
	}
	
	public void update(){
		while (true){
			BufferedImage scn = this.createScreenCapture(rect);
			if (viewer.update(scn)){
				if (Math.random() < 0.1){
					//this.keyTouch(KeyEvent.VK_SPACE);
					this.keyTouch(KeyEvent.VK_ENTER);
					this.mouseClick(InputEvent.BUTTON1_DOWN_MASK);
				}
				break;
			}else{
				this.keyTouch(KeyEvent.VK_ENTER);
				this.mouseClick(InputEvent.BUTTON1_DOWN_MASK);
			}
		}
	}

	public void Test(){
		this.delay(2000);
		viewer.screen = this.createScreenCapture(rect);
		viewer.getCorner();
		this.mouseMove(viewer.corner[0], viewer.corner[1]);
		this.mouseClick(InputEvent.BUTTON1_MASK);
		//this.mouseMove(viewer.colX(4), viewer.rowY(19));
		//this.delay(1000);
		//print(viewer.block);
		//this.mouseMove(a[0], a[1]);
		//if (true) return;
		//System.out.printf("corner = %d %d\n",a[0],a[1]);
		TetrisBlock tb = new TetrisBlock();
		while (true){
			int cnt = 0;
			while (cnt<=1){
				this.update();
				int oldscore = Judge.utility(viewer.block);
				tb = judge.Test(viewer.block, viewer.next);
				if (tb.score - 12 <= oldscore) break;
				if (cnt==0) this.hold();
				++cnt;
			}
			
			this.solve(tb);
		}
		
	}
}

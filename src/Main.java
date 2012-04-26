/* Main.main is the entry of the TetrisAuto */

import java.awt.AWTException;

public class Main {
	public static void main(String[] args) {
		
		try{
			TetrisBot tr = new TetrisBot();
			for (int i=1;i<=1;++i){
				tr.Test();
			}
		}catch(AWTException e){
			e.printStackTrace();
		}
		
	}

}

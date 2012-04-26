/* Representing those blocks */

public class TetrisBlock {

	/* config[i][j][k][l] : block type i, rotate j times, ... forgotten */
	public static int[][][][] config = 
	{
		{ {{0,2},{0,2}} , {{0,2},{0,2}} , {{0,2},{0,2}} , {{0,2},{0,2}} },
		{ {{0,1},{0,1},{0,1},{0,1}} , {{0,4}}, {{0,1},{0,1},{0,1},{0,1}} , {{0,4}} },
		{ {{1,1},{0,2},{0,1}} , {{0,2},{1,2}} , {{1,1},{0,2},{0,1}} , {{0,2},{1,2}} },
		{ {{0,1},{0,2},{1,1}} , {{1,2},{0,2}} , {{0,1},{0,2},{1,1}} , {{1,2},{0,2}} },
		{ {{0,1},{0,2},{0,1}} , {{0,3},{1,1}} , {{1,1},{0,2},{1,1}} , {{1,1},{0,3}} },
		{ {{0,2},{0,1},{0,1}} , {{0,3},{2,1}} , {{1,1},{1,1},{0,2}} , {{0,1},{0,3}} },
		{ {{0,1},{0,1},{0,2}} , {{0,3},{0,1}} , {{0,2},{1,1},{1,1}} , {{2,1},{0,3}} },
	};

	
	public static int[][] left =
	{
		{4,4,4,4},
		{3,5,3,4},
		{3,4,3,3},
		{3,4,3,3},
		{3,4,3,3},
		{3,4,3,3},
		{3,4,3,3}
	};
	
	/* Constructor for the TetrisBlock */
	public TetrisBlock(int tt, int rr, int xx, int ss){
		type = tt; rot = rr; x = xx; score = ss;
	}
	
	/* Constructor for the TetrisBlock */
	public TetrisBlock(){
	}

	public int score;
	public int type;
	public int rot;
	public int x;
}

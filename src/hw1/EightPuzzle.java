package hw1;

import java.util.Arrays;

public class EightPuzzle {
	int[] puzzle = new int[6];
	
	public EightPuzzle(int[] in) {
		for(int i=0; i<6;i++){
			puzzle[i] = in[i];
		}
	}
	@Override
	public String toString(){
		return puzzle[0]+" ,"+puzzle[1]+" ,"+puzzle[2]+", \n"+puzzle[3]+" ,"+puzzle[4]+" ,"+puzzle[5]+";\n";
	}
	
	public boolean canMove(Move dir){
		int space=emptyIndex();
		switch(dir){
		case MOVE_UP: return (space!=0 && space!=1 && space!=2);
		case MOVE_DOWN: return (space!=3 && space!=5 && space!=4);
		case MOVE_RIGHT: return (space!=2 && space!=5);
		case MOVE_LEFT: return (space!=0 && space != 3);
		}
		return true;
	}
	
	public enum Move{
		MOVE_UP,
		MOVE_DOWN,
		MOVE_RIGHT,
		MOVE_LEFT;
	}
	
	public int emptyIndex(){
		int space=-1;
		for(int i=0; i<6;i++){
			if(puzzle[i]==0) {space = i;break;}
		}
		return space;
	}
	
	public EightPuzzle moveUp(){
		int space = emptyIndex();
		EightPuzzle out = new EightPuzzle(puzzle);
		out.puzzle[space] = puzzle[space-3];
		out.puzzle[space-3]=0;
		return out;
	}
	public EightPuzzle moveDown(){
		int space = emptyIndex();
		EightPuzzle out = new EightPuzzle(puzzle);
		out.puzzle[space] = puzzle[space+3];
		out.puzzle[space+3]=0;
		return out;
	}
	public EightPuzzle moveLeft(){
		int space = emptyIndex();
		EightPuzzle out = new EightPuzzle(puzzle);
		out.puzzle[space] = puzzle[space-1];
		out.puzzle[space-1]=0;
		return out;
	}
	public EightPuzzle moveRight(){
		int space = emptyIndex();
		EightPuzzle out = new EightPuzzle(puzzle);
		out.puzzle[space] = puzzle[space+1];
		out.puzzle[space+1]=0;
		return out;
	}
	
	public boolean isGoal(){
		return (puzzle[0]==0 && puzzle[1]==1 && puzzle[2]==2 && puzzle[3]==5 && puzzle[4]==4 && puzzle[5]==3);
	}
	
	@Override
	public boolean equals( Object o){
		 if (o == this) return true;
	        if (!(o instanceof EightPuzzle)) return false;
	     
	     EightPuzzle p2 = (EightPuzzle) o;
	     for(int i=0; i<5;i++){
	    	 if(puzzle[i] != p2.puzzle[i]) return false;
	     }
		return true;
	}

	@Override
	public int hashCode(){
		int result = 31*17 + Arrays.hashCode(puzzle);
		return result;
	}
	
	
	public static void main(String[] args){
		int[] test_puzzle = {4,5,1,3,2,0};
		EightPuzzle test = new EightPuzzle(test_puzzle);
		System.out.println(test.toString());
		System.out.println(test.canMove(Move.MOVE_UP));
		System.out.println(test.canMove(Move.MOVE_DOWN));
		System.out.println(test.canMove(Move.MOVE_LEFT));
		System.out.println(test.canMove(Move.MOVE_RIGHT));
		
		System.out.println(test.moveUp().toString());
		//System.out.println(test.toString());
		System.out.println(test.moveLeft().toString());
		//System.out.println(test.moveRight().toString());
	}
}

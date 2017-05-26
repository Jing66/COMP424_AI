package hw1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import hw1.EightPuzzle.Move;

public class BFSPuzzle {
	static EightPuzzle start;
	static HashMap<EightPuzzle, Integer> visited = new HashMap<EightPuzzle, Integer>();
	//bfs
	static ArrayDeque<EightPuzzle> que = new ArrayDeque<EightPuzzle>();
	
	//dfs
	static Stack<EightPuzzle> stak = new Stack<EightPuzzle>();
	//ids
	static int limit = 0;
	
	public BFSPuzzle() {
		int[] test = {5,1,0,4,3,2};
		start = new EightPuzzle(test);
		visited.put(start, 1);
		que.add(start);
		stak.add(start);
	}
	
	//iterative deepening search
	public static void ids(){
		int count=0;
		//System.out.println(dfs(start,3));
		//System.out.println("=============");
		while(count<100){
			System.out.println("====NEW ITERATION=========");
			HashMap<EightPuzzle, Integer> visitedIDS = new HashMap<EightPuzzle, Integer>();
			EightPuzzle found = dfs(start, limit, visitedIDS);
			limit+=1;
			if(found!=null&& found.isGoal()) {break;}
			count++;
			
		}
	}
	
	public static EightPuzzle dfs(EightPuzzle cur, int depth,HashMap<EightPuzzle, Integer> visitedIDS ){
		//System.out.println("depth is " + depth);
		System.out.println(cur);
		if(depth==0 || cur.isGoal()) {System.out.println("======");return cur;}
		
		else if(depth>0){
			//order the steps to move the lowest number first
			int space = cur.emptyIndex();
			HashMap<Move, Integer> toMove = new HashMap<Move, Integer>();
			ArrayList<Move> dir = new ArrayList<Move>();
			if(cur.canMove(Move.MOVE_DOWN)) toMove.put(Move.MOVE_DOWN, cur.puzzle[space+3]);
			if(cur.canMove(Move.MOVE_UP)) toMove.put(Move.MOVE_UP, cur.puzzle[space-3]);
			if(cur.canMove(Move.MOVE_LEFT)) toMove.put(Move.MOVE_LEFT, cur.puzzle[space-1]);
			if(cur.canMove(Move.MOVE_RIGHT)) toMove.put(Move.MOVE_RIGHT, cur.puzzle[space+1]);
			Collection<Integer> moveValue = toMove.values();
			
			List<Integer> temp = new ArrayList<Integer>(moveValue);
			Collections.sort(temp);
			for(int i=temp.size()-1; i>=0;i--){
				for (Entry<Move, Integer> entry: toMove.entrySet()){
					if (entry.getValue().equals(temp.get(i))) {
		               dir.add(entry.getKey());
		            }
				}
			}
			//execute the ordered steps
			for(int i= dir.size()-1;i>=0;i--){
				EightPuzzle tmp;
				switch(dir.get(i)){
				case MOVE_DOWN: {tmp = cur.moveDown();break;}
				case MOVE_UP: {tmp = cur.moveUp();break;}
				case MOVE_LEFT: {tmp = cur.moveLeft();break;}
				case MOVE_RIGHT: {tmp = cur.moveRight();break;}
				default: tmp = cur;
				}
				if(!visitedIDS.containsKey(tmp)){
					visitedIDS.put(tmp,1);
					EightPuzzle found = dfs(tmp, depth-1, visitedIDS);
					if(found!=null) return found;
				}
			}
			
		}
		return null;
	}
	
	//depth-first-search
	public static void dfs(){
		while(!stak.isEmpty()){
			EightPuzzle cur = stak.pop();
			System.out.println(cur.toString());
			//goal state found
			if(cur.isGoal()) { break;}
			//current level
			else{
				//order the square to move from high to low
				int space = cur.emptyIndex();
				HashMap<Move, Integer> toMove = new HashMap<Move, Integer>();
				ArrayList<Move> dir = new ArrayList<Move>();
				if(cur.canMove(Move.MOVE_DOWN)) toMove.put(Move.MOVE_DOWN, cur.puzzle[space+3]);
				if(cur.canMove(Move.MOVE_UP)) toMove.put(Move.MOVE_UP, cur.puzzle[space-3]);
				if(cur.canMove(Move.MOVE_LEFT)) toMove.put(Move.MOVE_LEFT, cur.puzzle[space-1]);
				if(cur.canMove(Move.MOVE_RIGHT)) toMove.put(Move.MOVE_RIGHT, cur.puzzle[space+1]);
				Collection<Integer> moveValue = toMove.values();
				
				List<Integer> temp = new ArrayList<Integer>(moveValue);
				Collections.sort(temp);
				for(int i=temp.size()-1; i>=0;i--){
					for (Entry<Move, Integer> entry: toMove.entrySet()){
						if (entry.getValue().equals(temp.get(i))) {
			               dir.add(entry.getKey());
			            }
					}
				}
				
				//push the first in list into the queue
				for(int i=0; i < dir.size();i++){
					
					EightPuzzle tmp;
					switch(dir.get(i)){
					case MOVE_DOWN: {tmp = cur.moveDown();break;}
					case MOVE_UP: {tmp = cur.moveUp();break;}
					case MOVE_LEFT: {tmp = cur.moveLeft();break;}
					case MOVE_RIGHT: {tmp = cur.moveRight();break;}
					default: tmp = cur;
					}
					if(!visited.containsKey(tmp)){
						visited.put(tmp, 1);
						stak.add(tmp);
					}
				}
			
			}
		}
		System.out.println("=============DFS Finished!===============");
	}
	
	//breath-first-search
	public static void bfs(){
		while(!que.isEmpty()){
			EightPuzzle cur = que.remove();
			System.out.println(cur.toString());
			//goal state found
			if(cur.isGoal()) { break;}
			//current level
			else{
				//order the square to move from high to low
				int space = cur.emptyIndex();
				HashMap<Move, Integer> toMove = new HashMap<Move, Integer>();
				ArrayList<Move> dir = new ArrayList<Move>();
				if(cur.canMove(Move.MOVE_DOWN)) toMove.put(Move.MOVE_DOWN, cur.puzzle[space+3]);
				if(cur.canMove(Move.MOVE_UP)) toMove.put(Move.MOVE_UP, cur.puzzle[space-3]);
				if(cur.canMove(Move.MOVE_LEFT)) toMove.put(Move.MOVE_LEFT, cur.puzzle[space-1]);
				if(cur.canMove(Move.MOVE_RIGHT)) toMove.put(Move.MOVE_RIGHT, cur.puzzle[space+1]);
				Collection<Integer> moveValue = toMove.values();
				
				List<Integer> temp = new ArrayList<Integer>(moveValue);
				Collections.sort(temp);
				for(int i=0; i<temp.size();i++){
					for (Entry<Move, Integer> entry: toMove.entrySet()){
						if (entry.getValue().equals(temp.get(i))) {
			               dir.add(entry.getKey());
			            }
					}
				}
				
				//push the first in list into the queue
				for(int i=0; i < dir.size();i++){
					
					EightPuzzle tmp;
					switch(dir.get(i)){
					case MOVE_DOWN: {tmp = cur.moveDown();break;}
					case MOVE_UP: {tmp = cur.moveUp();break;}
					case MOVE_LEFT: {tmp = cur.moveLeft();break;}
					case MOVE_RIGHT: {tmp = cur.moveRight();break;}
					default: tmp = cur;
					}
					if(!visited.containsKey(tmp)){
						visited.put(tmp, 1);
						que.add(tmp);
					}
				}
			
			}
		}
		System.out.println("=============BFS Finished!===============");
	}

	
	public static void main(String[] args){
		BFSPuzzle test = new BFSPuzzle();
		//BFSPuzzle.bfs();
		//BFSPuzzle.dfs();
		BFSPuzzle.ids();
		
		/*
		HashMap<EightPuzzle, Integer> check = new HashMap<EightPuzzle, Integer>();
		int[] test_puzzle = {4,5,1,3,2,0};
		EightPuzzle test = new EightPuzzle(test_puzzle);
		EightPuzzle test2 = new EightPuzzle(test_puzzle);
		System.out.println(test.equals(test2));
		check.put(test, 1);
		System.out.println(check.containsKey(test2));*/
	}
}

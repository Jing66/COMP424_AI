package hw1;

import java.util.Map.Entry;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class UCSPuzzle {
	static PriorityQueue<MyTuple> pqueue;
	static HashMap<EightPuzzle, Integer> visited = new HashMap<EightPuzzle, Integer>();
	static EightPuzzle start;
	
	public UCSPuzzle() {
		int[] test = {5,1,0,4,3,2};
		start = new EightPuzzle(test);
		visited.put(start, 1);
		pqueue.add(new MyTuple(start,0));
	}
	
	//Q: what is the cost?
	public void ucs(){
		
	}

	
	
}


class MyTuple implements Comparator<MyTuple>{
	public EightPuzzle state;
	public int cost;
	
	MyTuple(EightPuzzle in, int cost){
		state = in;
		this.cost = cost;
	}
	
	@Override
	public int compare(MyTuple t1, MyTuple t2){
		if(t1.cost > t2.cost) return 1;
		else if(t1.cost == t2.cost) return 0;
		else return -1;
	}
	
}
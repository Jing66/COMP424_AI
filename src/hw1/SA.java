package hw1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class SA {
	public int start;
	public double step;
	
	double alpha ;
	
	//start point: X= 0...10, step size: 0.02...0.07
	public SA(int start, double alpha, double step) {
		this.start = start;
		this.alpha = alpha;
		this.step = step;
		System.out.print(start+","+step+",");
		
	}
	
	double eval(double x){
		double ang = Math.PI*x/180;
		double cos = Math.cos(Math.pow(x, 2)/2);
		double log = Math.log(x+2)/Math.log(2);
		return cos/log;
	}
	
	boolean accept(double initE, double neighborE, double temp){
		double pow = (neighborE-initE)/temp;
		double prob = Math.exp(pow);
		//System.out.print(" p = "+prob);
		if(Math.random()<prob) { return true;}
		else { return false;}
	}
	
	double generateRan(double init){
		double lo = -init;
		double hi = 10-init;
		return Math.random()*(hi-lo)+lo;	
	}
	
	 double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();

		    BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}
	
	void simutaledAnnealing(){
		double temp = 1000000000;
		double init = (double)start;
		double max = eval(init);
//		System.out.println("initial max = "+max);
		int count_dir = 0;
		double maxNeighbor = init;
		while(temp>0.01){
			//generate neighbor
			//double neighbor = round(init+ generateRan(init),2);
			Random ran = new Random();
			int dir = (ran.nextDouble()<0.5)?-1:1; count_dir = dir==1?(count_dir+1):(count_dir-1);
			double neighbor = init + dir*step;
			neighbor = neighbor<0?(neighbor+step*2):neighbor; //new X > 0
			neighbor = neighbor>10?(neighbor-step*2):neighbor; //new X < 10
			neighbor = round(neighbor, 2);
//			System.out.print("X= "+init+" E(X)="+eval(init)+" X_i ="+neighbor +" E(X_i)="+eval(neighbor));
			if(eval(neighbor) > eval(init) || accept(eval(init), eval(neighbor),temp)){
//				if (eval(neighbor) > eval(init) ) System.out.println("   Going up");
				init = neighbor;
				
			}
			
			if(max < eval(neighbor)) {
				max = eval(neighbor);
				maxNeighbor = neighbor;
			}
			temp *= alpha;
		}
		System.out.println(maxNeighbor+","+max);
//		System.out.println("count direction: "+count_dir);
	}
	
	public static void main(String[] args){
//		SA test = new SA(1,0.95, 0.1);
//		test.simutaledAnnealing();
	
		for(int i=0; i<=10;i++){
			for(double step = 0.05;step<0.1;step+=0.01){
				SA test = new SA(i, 0.99,step);
				test.simutaledAnnealing();
			}
		}
		
	}
}

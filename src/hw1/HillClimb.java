package hw1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class HillClimb {
	public int start;
	public double step;
	static double upperbound = (double)10;
	
	public HillClimb(int start, double step) {
		this.start = start;
		this.step = step;
		//System.out.println("new object with start = "+start+" step size "+step);
		System.out.print(start+","+step+",");
	}
	
	double eval(double x){
		double ang = Math.PI*x/180;
		//double cos = Math.cos(Math.pow(ang, 2)/2);
		double cos = Math.cos(Math.pow(x, 2)/2);
		double log = Math.log(x+2)/Math.log(2);
		return cos/log;
	}

	int hillClimb(){
		double init = (double)start;
		int count=0;
		double max = eval(init);
		while(round(init+step,2)<=upperbound && round(init-step,2)>=0 ){
			max = Math.max(eval(step+init), eval(init));
			max = Math.max(max, eval(init-step));
			if(max==eval(init)) break;
			else if(max == eval(step+init)) init = step+init;
			else init = init-step;
			count++;
			init = round(init,2);
			//System.out.println(init);
		}
		System.out.print(init);
		System.out.print(","+max+",");
		return count;
	}
	
	
	 double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static void main(String[] args){
		//System.out.println(toDouble(0.999999));
		//HillClimb test1 = new HillClimb(1,0.01);
//		System.out.println(test1.eval(10.00));
//		System.out.println(test1.eval(9.9));
//		System.out.println(test1.eval(9.8));
		//System.out.println(test1.hillClimb());
		
		for(int i=0;i<=10;i++){
			for(double step = 0.01; step<=0.1;step+=0.01){
				HillClimb test = new HillClimb(i,step);
				System.out.println(test.hillClimb());
			}	
		}
		
		
		
	}
}

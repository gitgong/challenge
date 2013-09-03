package dev.blake.portfolio.particle;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ChamberBoolCache {

	//keeps track of time / moves
	int numMoves = 0;	//stores move records for each
	List<ArrayList<Boolean>> particlePositionCache = new ArrayList<ArrayList<Boolean>>();
	List<Particle> particles = new ArrayList<Particle>();
	int size;

	static Logger log = Logger.getLogger("dev.blake.portfolio.particlechamber");

	public ChamberBoolCache (int inSize){
		size = inSize;
	}
	void setSize(int inSize){
		size = inSize;
	}

	public String[] animateParticles(int speed, String init){
		//initializes chamber with particles in starting position
		loadParticlesInChamber(speed, init);   
		log.info(toString());
		//particles are moved through chamber until chamber is empty
		while(!isEmpty()){
			moveParticles();
			log.info(toString());   
		}
		//obtain a grid of particle movement record
		return getDisplayGridArray(particlePositionCache);
		
	}

	public void loadParticlesInChamber(int speed, String init){
		char[] initChars = init.toCharArray();
		particlePositionCache.clear();
		numMoves=0;
		setSize(initChars.length);
		particlePositionCache.add(new ArrayList<Boolean>(size));
		for(int loc = 0; loc <  size; loc++){
			if( initChars [loc]=='L' | initChars [loc]=='R'){
				Particle p =  new Particle(initChars[loc], loc, speed); 
				particles.add(p);
				particlePositionCache.get(numMoves).add(true);
			}else{
				//TODO prefer not to have to do this. should happen wherever they are "not"
				particlePositionCache.get(numMoves).add(false);
			}
		}
		numMoves++;
	}

	public void moveParticles(){
		particlePositionCache.add(new ArrayList<Boolean>(size)); 

		//add non-particle indicators to display grid first
		//TODO prefer not to have to do this. should happen wherever they are "not"
		for(int loc =0; loc < size; loc++){
			particlePositionCache.get(numMoves).add(false);
		}
		ListIterator<Particle> it = this.particles.listIterator();
		while(it.hasNext()){
			Particle p = it.next();
			p.move();
			if(p.done){
				//remove particle from chamber if its finished
				it.remove();
			}else{
				//NOT DONE. store move location in display grid.
				if(!particlePositionCache.get(numMoves).get(p.location).equals(true))
					particlePositionCache.get(numMoves).set(p.location, true);
			}
		} 
		numMoves++;
	}

	public boolean isEmpty(){
		return particles.isEmpty();
	}

	///***********************************************************************************************
	//inner class models particle
	class Particle  {
		char direction;
		int location;
		int speed;
		boolean done= false;

		public Particle(char dir, int loc, int sp){
			direction = dir;
			location = loc;
			speed = sp;
		}

		void move(){
			if(direction=='L')
				location = location - speed;
			else if(direction=='R'){
				location = location + speed;
			} 
			if(location < 0){
				done=true;
			}
			if(location>=ChamberBoolCache.this.size){
				done=true;
			}
		} 
		public String toString(){
			return "Particle: " + " location="+location + " direction="+direction + " speed="+ speed + " done="+done;
		}
	}
	//utility methods
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("chamber size="+ size);
		for(Particle p: particles){
			sb.append(" [  " + p.toString() + " ]");
		}
		return sb.toString();
	}

	//converts display grid record from list of byte list to string array
	static String[] getDisplayGridArray(List<ArrayList<Boolean>> particlePosCache){
		
		String[] particleLevelsArr = new String[particlePosCache.size()];
		int rowCounter=0;
		int boolCounter = 0;
		for(List<Boolean> row: particlePosCache){
			StringBuffer sb = new StringBuffer();
			for(Boolean b: row){
				if(b==true){ 
					sb.append("X");
					 boolCounter++;
				}else{
					sb.append(".");
					boolCounter++;
				}
			} 
			particleLevelsArr[rowCounter]=sb.toString();
			rowCounter++;
		}
		System.out.println("cache numbools: "+boolCounter);
		return particleLevelsArr;
	}

	static void printDisplayGridArray(String[] arr){
		for(String s: arr){
			System.out.println(s);
		}
		System.out.println("");
	}
	//main method
	public static void main(String[] args) {
		log.setLevel(Level.OFF);

		System.out.println("0: ..R....");
		log.info("0-..R....");
		String init="..R....";
		int speed = 2;

		ChamberBoolCache chamber = new ChamberBoolCache(init.length()); 
		String[] sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr);

		System.out.println("1-RR..LRL");
		log.info("1-RR..LRL");
		init="RR..LRL";
		speed = 3; 	
		sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr);

		System.out.println("2-LRLR.LRLR");
		log.info("2-LRLR.LRLR");
		init="LRLR.LRLR";
		speed=2;
		sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr); 

		System.out.println("3-RLRLRLRLRL");
		log.info("3-RLRLRLRLRL");
		speed=10;
		init="RLRLRLRLRL";
		sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr);  

		System.out.println("4-...");
		log.info("4-...");
		init="...";
		speed=1; 
		sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr); 

		System.out.println("5-LRRL.LR.LRR.R.LRRL.");
		log.info("5-LRRL.LR.LRR.R.LRRL.");
		init="LRRL.LR.LRR.R.LRRL.";
		speed=1;  
		sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr);


		System.out.println("6-LRRL.LR.LRR.R.LRRL.LLLRRRRRL...LLLLRRLL....LRR.RRLR.LRRL.LR.LRR.R.LRRL.LLLRRRRRL...LLL");
		log.info("6-LRRL.LR.LRR.R.LRRL.LLLRRRRRL...LLLLRRLL....LRR.RRLR.LRRL.LR.LRR.R.LRRL.LLLRRRRRL...LLL");
		init="LRRLL....LRR.RRLR.LRRL.LR.LRR.R.LRRL.LLLRRRRRL...LLLLRRLL....LRR.RRLR.LRRL.LR.LRR.R.LRRL.LLLRRRRRL...LLL";
		speed=2;  
		sArr = chamber.animateParticles(speed, init);      
		printDisplayGridArray(sArr);
	}
}


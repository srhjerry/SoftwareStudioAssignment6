package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float x, y, radius;
	private String name;
	private ArrayList<Character> targets;

	public Character(MainApplet parent, String name/*, float x, float y*/){
		targets = new ArrayList<Character>();
		this.parent = parent;
		this.name = name;
		/*this.x = x;
		this.y = y;
		this.radius = 5;*/
	}

	public void display(){
		parent.fill(150);
		parent.rect(this.x-5, this.y-10, 70, 15);
		
		parent.fill(255);
		parent.text(this.name, this.x, this.y);
		
		parent.stroke(50);
		for(int i = 0; i < this.targets.size(); i++){
			parent.line(this.x, this.y, targets.get(i).x, targets.get(i).y);
			
		}
	}
	//temporary
	public void addTarget(Character target){
		targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
public void setposition(float x,float y){
		
		this.x=x;
		this.y=y;
	}


	
public float getx(){
		
		return this.x;
	}
public float gety(){
	
	return this.y;
}

public String getname(){
		
		return this.name;
	}


}

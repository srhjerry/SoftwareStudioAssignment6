package main.java;

import java.util.ArrayList;
import java.util.*;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float x, y, radius;
	private String name, colour;
	private ArrayList<Character> targets;
	private Map<Character, Object> interactCount;

	public Character(MainApplet parent, String name, String colour/*, float x, float y*/){
		targets = new ArrayList<Character>();
		interactCount = new HashMap<Character, Object>();
		this.parent = parent;
		this.name = name;
		this.colour = colour;
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
/*		for(int i = 0; i < this.targets.size(); i++){
			parent.line(this.x, this.y, targets.get(i).x, targets.get(i).y);
			int value_tmp = (int)interactCount.get(targets.get(i)); // get target character's interact counts in episode
		} */
	}
	
	//clear
	public void addTarget(Character target, int value){
		targets.add(target);
		interactCount.put(target,value);
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
public int getwidth(Character i){
	if(interactCount.get(i)!=null){
		return (int)interactCount.get(i);
	}else{
		return 1;
	}
	
}
public String getname(){
		
		return this.name;
	}


}

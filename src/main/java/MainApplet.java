package main.java;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.event.MouseInputListener;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.KeyEvent;
import controlP5.*;
/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet implements MouseInputListener{
	private String path = "main/resources/";
	private String fileHead = "starwars-episode-";
	private String fileTail = "-interactions.json";
	
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private ArrayList<Character> activech;
	private Random ran = new Random();
	private ControlP5 cp5;
    private CheckBox checkbox;
	private final static int width = 1200, height = 650;
	private int myColorBackground;

	public void setup() {
		size(width, height);
		characters = new ArrayList<Character>();
		activech = new ArrayList<Character>();
		
			
		cp5=new ControlP5(this);
		cp5.addButton("addall").setLabel("ADDALL").setPosition(width*5/6,height/6).setSize(100, 20);
		cp5.addButton("removeall").setLabel("REMOVEALL").setPosition(width*5/6,height/6+50).setSize(100, 20);
		//cp5.addButton("refresh").setLabel("refresh").setPosition(width*5/6,height/6+100).setSize(100, 20);
		
		 loadData(1);
		
		 smooth();


	}	
	public void removeall(){
     

		activech.clear();
		
		
	if(checkbox.getItems().size()>=characters.size()){
		for(int i=0;i<characters.size();i++)
		{
			
			if(checkbox.getState(i))
			{
				checkbox.toggle(i);
				
			}
			
		}
	}
		System.out.println("removeall");
	}
	public void addall(){
		
		for(int i=0;i<characters.size();i++)
		{
			
			if(!activech.contains(characters.get(i)))
			{
				
				activech.add(characters.get(i));
			}
			
			if(!checkbox.getState(i))
			{
				checkbox.toggle(i);
				
			}
			
		}
		System.out.println("addall");
	}
	public void mouseClicked(MouseEvent e) {
       refresh();
    }
	public void refresh(){
		
		Iterator<Character> iteratorch = characters.iterator();
		
		while(iteratorch.hasNext()){
			Character i=iteratorch.next();
			if(checkbox.getState(i.getname())){
				
			
			if(!activech.contains(i))
			{
				
				activech.add(i);
				
			}
			}else{
				if(activech.contains(i))
				{
					Iterator<Character> activeit = activech.iterator();
				while(activeit.hasNext()){
				Character j=activeit.next();
				if(j==i){
					activeit.remove();
				}
				
			}
					
					
					
				}
			}
			System.out.println(i.getname()+": "+checkbox.getState(i.getname()));
		}
		
	}
	



	public void draw() {
		
	  //  BasicStroke wideStroke = new BasicStroke(8.0f);
		background(0);
		  pushMatrix();
		  translate(width/2 + 200, height/2);
		  stroke(255);
		  strokeWeight(2);
		  fill(myColorBackground);

		  popMatrix();
		  Iterator<Character> iterator = activech.iterator();
	while(iterator.hasNext()){
			Character i=iterator.next();
			i.display();
			if(!i.getTargets().isEmpty()){
				Iterator<Character> iterator2 = i.getTargets().iterator();
			while(iterator2.hasNext()){
				Character current=iterator2.next();
				
				if(activech.contains(current)){
				
						strokeWeight(i.getwidth(current)/3+1);
					this.line(i.getx(), i.gety(), current.getx(), current.gety());
				}
				}
			}
			
		}
		
	}
	
	public void keyPressed(KeyEvent key){
	//	this.clear();
/*		for(Character i:characters){
		checkbox.removeItem(i.getname());
	}
	*/
		removeall();
		characters.clear();
		
		if(key.getKey()<='7'|| key.getKey()>='1')
			loadData(key.getKey()-'0');
		else
			loadData(1);
				
		/*this.background(250, 250, 250);
		for(int i = 0; i < characters.size(); i++){
			characters.get(i).x = ran.nextFloat()*700;
			characters.get(i).y = ran.nextFloat()*700;
		}*/
	}

	private void loadData(int episode){
		
		 checkbox = cp5.addCheckBox("checkBox")
	                .setPosition(10, 20)
	                .setColorForeground(color(120))
	                .setColorActive(color(255))
	                .setColorLabel(color(255))
	                .setSize(15, 15)
	                .setItemsPerRow(3)
	                .setSpacingColumn(80)
	                .setSpacingRow(20)
	                ;
			data = loadJSONObject(path+fileHead+episode+fileTail);
		    
			nodes = data.getJSONArray("nodes");
			links = data.getJSONArray("links");

data = loadJSONObject(path+fileHead+episode+fileTail);
		    
			nodes = data.getJSONArray("nodes");
			links = data.getJSONArray("links");
			
			

			for(int i = 0; i < nodes.size(); i++){	
				characters.add(new Character(this, nodes.getJSONObject(i).getString("name"), nodes.getJSONObject(i).getString("colour")/*, ran.nextFloat()*700, ran.nextFloat()*700*/));
			}
			for(int i = 0; i < links.size(); i++){
				characters.get(links.getJSONObject(i).getInt("source")).addTarget(characters.get(links.getJSONObject(i).getInt("target")), links.getJSONObject(i).getInt("value"));
			}
			 for(int i=0;i<characters.size();i++)
			 {
				 characters.get(i).setposition(3000+ran.nextInt(800), 50+ran.nextInt(500));
				 checkbox.addItem(characters.get(i).getname(), (float)i);
			 }
			 checkbox.updateLayout();

	}

}

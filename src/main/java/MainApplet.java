package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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
public class MainApplet extends PApplet{
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
		cp5.addButton("addall").setLabel("ADDALL").setPosition(width*3/4,height/5).setSize(width/5, height/5);
		cp5.addButton("removeall").setLabel("REMOVEALL").setPosition(width*4/5,height/5+30).setSize(100, 20);
		 checkbox = cp5.addCheckBox("checkBox")
	                .setPosition(10, 20)
	                .setColorForeground(color(120))
	                .setColorActive(color(255))
	                .setColorLabel(color(255))
	                .setSize(500, 400)
	                .setItemsPerRow(3)
	                .setSpacingColumn(30)
	                .setSpacingRow(20)
	                ;
		 loadData(1);
		 smooth();
		

	}
	public void addall(){
		for(int i=0;i<characters.size();i++)
		{
			if(!activech.contains(characters.get(i)))
			{
				activech.add(characters.get(i));
			}
			if((int)checkbox.getArrayValue(i)!=1)
			{
				checkbox.setArrayValue(i, (float)1);
				
			}
		}
	}
	
	void controlEvent(ControlEvent theEvent) {
		  if (theEvent.isFrom(checkbox)) {
		    myColorBackground = 0;
		    print("got an event from "+checkbox.getName()+"\t\n");
		    // checkbox uses arrayValue to store the state of 
		    // individual checkbox-items. usage:
		    println(checkbox.getArrayValue());
		    int col = 0;
		    for (int i=0;i<checkbox.getArrayValue().length;i++) {
		      int n = (int)checkbox.getArrayValue()[i];
		      print(n);
		      if(n==1) {
		        myColorBackground += checkbox.getItem(i).internalValue();
		        activech.add(characters.get(i));
		      }else {
		    	  if(activech.contains(characters.get(i)))
		    	  {
		    		  activech.remove(characters.get(i));
		    	  }
		      }
		    }
		    println();    
		  }
		}


	public void draw() {
		for(int i = 0; i < activech.size(); i++){
			activech.get(i).display();
		}
	}
	
	public void keyPressed(KeyEvent key){
		//this.clear();
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
				checkbox.addItem(characters.get(i).getname(), (float)i);
			 }
	}

}

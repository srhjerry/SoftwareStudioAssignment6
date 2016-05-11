package main.java;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.KeyEvent;

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
	private Random ran = new Random();
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		characters = new ArrayList<Character>();
		smooth();
		loadData(1);		
	}

	public void draw() {
		/*for(int i = 0; i < characters.size(); i++){
			characters.get(i).display();
		}*/
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
				characters.add(new Character(this, nodes.getJSONObject(i).getString("name")/*, ran.nextFloat()*700, ran.nextFloat()*700*/));
			}
			for(int i = 0; i < links.size(); i++){
				characters.get(links.getJSONObject(i).getInt("source")).addTarget(characters.get(links.getJSONObject(i).getInt("target")));
			}
	}

}

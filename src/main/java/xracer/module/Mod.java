package xracer.module;

import net.minecraft.client.MinecraftClient;

public class Mod {
	public String getName() {
		return name;
	}
	
	public void toggle() {
		this.enabled = !this.enabled;
		
		if(enabled) {
			onEnable();
		}else {
			onDisable();
		}
		
	}
	
	public void onEnable() {
		
	}

	public void onDisable() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void onTick() {
		
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if(enabled) {
			onEnable();
		}else {
			onDisable();
		}
	}

	public Category getCategory() {
		return category;
	}

	private int x;
	private int x2;
	private int y;
	private int y2;

	public int getX() {
		return x;
	}

	public int getX2(){
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public void setX(int x, int x2){
		this.x = x;
		this.x2 = x2;
	}

	public void setY(int y, int y2){
		this.y = y;
		this.y2 = y2;
	}

	public int getY() {
		return y;
	}

	private String name;
	private String description;
	private int key;
	private Category category;
	private boolean enabled;
	protected static MinecraftClient mc = MinecraftClient.getInstance();
	
	public Mod(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
	}
	
	public enum Category{
		player,
		world,
		render
	}}

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
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int xe){
		x = xe;
	}

	public void setY(int ye){
		y = ye;
	}

	public int getY() {
		return y;
	}

	private String name;
	private String description;
	private int key;
	private Category category;
	private boolean enabled;
	protected MinecraftClient mc = MinecraftClient.getInstance();
	
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

package xracer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import xracer.module.Mod;
import xracer.module.ModuleManager;
import xracer.ui.Hud;
import xracer.ui.menu.MenuGUI;

public class Client implements ModInitializer{

	public static final Client INSTANCE = new Client();

	private MinecraftClient mc = MinecraftClient.getInstance();
	public Logger LOGGER = LogManager.getLogger(Client.class);
    static File file = new File(MinecraftClient.getInstance().runDirectory, "save");
	  public static void save() {
	        try {
	            PrintWriter pw = new PrintWriter(file);
				for(Mod mod : ModuleManager.instance.getModules()){
					pw.println(mod.isEnabled());
				}
	            pw.close();
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void load() {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(file));
				for(Mod mod : ModuleManager.instance.getModules()){
					mod.setEnabled(Boolean.parseBoolean(reader.readLine()));
				}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void stopped() {
	    	save();
	    }
		
	@Override
	public void onInitialize(){
		load();
		LOGGER.info("XRACER EZ");
	}
	 

	
	
	public void onKeyPress(int key, int action) {
		if(action == GLFW.GLFW_PRESS) {
			for(Mod module : ModuleManager.instance.getModules()) {
				
				if(key == module.getKey()) module.toggle();
			}
			
			if(key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
				mc.setScreen(MenuGUI.INSTANCE);
				MenuGUI.INSTANCE.menu_button = true;

			}
		}
		Hud.pressed(key, action);
	}
	
	public void onTick() {
		if(mc.player != null) {
			for(Mod module : ModuleManager.instance.getEnabledModules()) {
				module.onTick();
			}
		}
	}
}

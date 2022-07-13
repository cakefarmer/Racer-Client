package xracer.module.modules;

import org.lwjgl.glfw.GLFW;

import xracer.module.Mod;
import xracer.module.Mod.Category;

public class sprint extends Mod{

	public sprint() {
		super("Toggle Sprint", "Toggles Sprint", Category.client);
		this.setKey(GLFW.GLFW_KEY_R);
	}
	
	public void onTick() {
		if(mc.player.input.hasForwardMovement()) {
			mc.player.setSprinting(true);
			super.onTick();
		}
	}
	
	public void onDisable() {
		mc.player.setSprinting(false);
	}
}

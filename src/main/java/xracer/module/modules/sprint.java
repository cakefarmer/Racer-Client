package xracer.module.modules;

import org.lwjgl.glfw.GLFW;

import xracer.module.Mod;
import xracer.module.Mod.Category;

public class sprint extends Mod{

	public sprint() {
		super("Toggle Sprint", "Toggles Sprint", Category.player);
		this.setKey(GLFW.GLFW_KEY_R);
	}
	@Override
	public void onTick() {
		if (this.isEnabled()) {
			if (mc.player.input.hasForwardMovement()) {
				if (mc.player != null) {
					mc.player.setSprinting(true);
					super.onTick();
				}
			}
		}
	}
	@Override
	public void onDisable() {
		if (mc.player != null) {
			mc.player.setSprinting(false);
		}
	}
}

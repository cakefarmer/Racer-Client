package xracer.ui;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import net.minecraft.entity.Entity;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import xracer.Client;
import xracer.module.Mod;
import xracer.module.Mod.Category;
import xracer.module.ModuleManager;
import xracer.module.modules.armorhud;
import xracer.ui.item.draw;
import xracer.ui.menu.MenuGUI;

public class Hud {

	public static boolean armor;
	public static boolean fps;
	public static boolean keystrokes;
	public static boolean menu = false;
	public static boolean radar;
	public static MinecraftClient mc = MinecraftClient.getInstance();

	public static void render(MatrixStack matrices, float tickDelta) {
		int sWidth = mc.getWindow().getScaledWidth();
		int sHeight = mc.getWindow().getScaledHeight();

		MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, "xracer", 10, 10, -1);
		if(ModuleManager.instance.getModule("Coordinates").isEnabled()) {
			mc.textRenderer.drawWithShadow(matrices, "x: " + (int) mc.player.getX() + " y: " + (int) mc.player.getY() + " z: " + (int) mc.player.getZ(), sWidth / 2 - (mc.textRenderer.getWidth("x: " + (int) mc.player.getX() + " y: " + (int) mc.player.getY() + " z: " + (int) mc.player.getZ()) / 2), 25, -1);
		}
		mc.textRenderer.drawWithShadow(matrices, "Facing: " + mc.player.getHorizontalFacing(), 10, 20, -1);

		if(ModuleManager.instance.getModule("FPS").isEnabled()) {
			matrices.push();
			matrices.scale(1.2f, 1.2f,1.2f);
			mc.textRenderer.drawWithShadow(matrices, "FPS: " + mc.fpsDebugString.substring(0, 4).replace("f", "                    "), 8, 50, -1);
			matrices.pop();
		}
	}

	static File file = new File(MinecraftClient.getInstance().runDirectory, "save");


	public static void renderArrayList(MatrixStack matrices) {
		int index = 0;
		int sWidth = mc.getWindow().getScaledWidth();
		int sHeight = mc.getWindow().getScaledHeight();
		List<Mod> enabled = ModuleManager.instance.getEnabledModules();
		enabled.sort(Comparator.comparingInt(m -> mc.textRenderer.getWidth(((Mod) m).getName())).reversed());
		for (Mod mod : enabled) {
			if (mod.getName() == "Toggle Sprint") {
				mc.textRenderer.drawWithShadow(matrices, mod.getName(), (sWidth / 2) - (mc.textRenderer.getWidth(mod.getName()) / 2), 10 + (index * mc.textRenderer.fontHeight), -1);
				index++;
			} else {

			}
		}

	}

	public void addMods(String mod, Category category) {
		if (category == Category.render) {
			MenuGUI.rendermods.add(mod);
		}
		if (category == Category.player) {
			MenuGUI.playermods.add(mod);
		}
		if (category == Category.world) {
			MenuGUI.worldmods.add(mod);
		}

	}

	static int color = 0x44000000;
	static int color1 = 0x44000000;
	static int color2 = 0x44000000;
	static int color3 = 0x44000000;




	public static void drawKeys(MatrixStack matrices) {
		if (ModuleManager.instance.getModule("Keystrokes").isEnabled() == true) {
			int sWidth = mc.getWindow().getScaledWidth();
			int sHeight = mc.getWindow().getScaledHeight();
			DrawableHelper.fill(matrices, sWidth - 10, sHeight - 10, sWidth - 40, sHeight - 40, color);
			DrawableHelper.fill(matrices, sWidth - 75, sHeight - 10, sWidth - 45, sHeight - 40, color1);
			DrawableHelper.fill(matrices, sWidth - 110, sHeight - 10, sWidth - 80, sHeight - 40, color2);
			DrawableHelper.fill(matrices, sWidth - 75, sHeight - 45, sWidth - 45, sHeight - 80, color3);
			mc.textRenderer.drawWithShadow(matrices, "D", sWidth - mc.textRenderer.getWidth("D") - 22, sHeight - 27, -1);
			mc.textRenderer.drawWithShadow(matrices, "S", sWidth - mc.textRenderer.getWidth("S") - 57, sHeight - 27, -1);
			mc.textRenderer.drawWithShadow(matrices, "A", sWidth - mc.textRenderer.getWidth("A") - 92, sHeight - 27, -1);
			mc.textRenderer.drawWithShadow(matrices, "W", sWidth - mc.textRenderer.getWidth("W") - 57, sHeight - 67, -1);
		}
	}

	public static void pressed(int key, int action) {
		if (action == GLFW.GLFW_PRESS) {
			if(key == GLFW.GLFW_KEY_ESCAPE){
				if(MenuGUI.isdisplayingmenu == true){
					MenuGUI.isdisplayingmenu = false;
				}
			}
			if(key == GLFW.GLFW_KEY_RIGHT_SHIFT){
				if(MenuGUI.isdisplayingmenu == true){
					MenuGUI.isdisplayingmenu = false;
				}
			}
			if (key == GLFW.GLFW_KEY_W) {
				color3 = 0x44ffffff;
			}
			if (key == GLFW.GLFW_KEY_D) {
				color = 0x44ffffff;
			}
			if (key == GLFW.GLFW_KEY_S) {
				color1 = 0x44ffffff;
			}
			if (key == GLFW.GLFW_KEY_A) {
				color2 = 0x44ffffff;
			}
		}
		if (action == GLFW.GLFW_RELEASE) {
			if (key == GLFW.GLFW_KEY_W) {
				color3 = 0x44000000;
			}
			if (key == GLFW.GLFW_KEY_D) {
				color = 0x44000000;
			}
			if (key == GLFW.GLFW_KEY_S) {
				color1 = 0x44000000;
			}
			if (key == GLFW.GLFW_KEY_A) {
				color2 = 0x44000000;
			}
		}


	}


	public static void items(MatrixStack matrices) {
		int index = 120;
		int width = mc.getWindow().getScaledWidth();
		int height = mc.getWindow().getScaledHeight();
		Inventory inv = mc.player.getInventory();
		if (ModuleManager.instance.getModule("Armor Display").isEnabled() == true) {
			draw.drawItem(MinecraftClient.getInstance().player.getInventory().armor.get(3), 5, index, 1);
			if (MinecraftClient.getInstance().player.getInventory().armor.get(3).getMaxDamage() != 0) {
				mc.textRenderer.draw(matrices, "" + (MinecraftClient.getInstance().player.getInventory().armor.get(3).getMaxDamage() - MinecraftClient.getInstance().player.getInventory().armor.get(3).getDamage()), 25, index + 4.5f, -1);
			}
			index = index + 15;
			draw.drawItem(MinecraftClient.getInstance().player.getInventory().armor.get(2), 5, index, 1);
			if (MinecraftClient.getInstance().player.getInventory().armor.get(2).getMaxDamage() != 0) {
				mc.textRenderer.draw(matrices, "" + (MinecraftClient.getInstance().player.getInventory().armor.get(2).getMaxDamage() - MinecraftClient.getInstance().player.getInventory().armor.get(2).getDamage()), 25, index + 4.5f, -1);
			}
			index = index + 15;
			draw.drawItem(MinecraftClient.getInstance().player.getInventory().armor.get(1), 5, index, 1);
			if (MinecraftClient.getInstance().player.getInventory().armor.get(1).getMaxDamage() != 0) {
				mc.textRenderer.draw(matrices, "" + (MinecraftClient.getInstance().player.getInventory().armor.get(1).getMaxDamage() - MinecraftClient.getInstance().player.getInventory().armor.get(1).getDamage()), 25, index + 4.5f, -1);
			}

			index = index + 15;
			draw.drawItem(MinecraftClient.getInstance().player.getInventory().armor.get(0), 5, index, 1);
			if (MinecraftClient.getInstance().player.getInventory().armor.get(0).getMaxDamage() != 0) {
				mc.textRenderer.draw(matrices, "" + (MinecraftClient.getInstance().player.getInventory().armor.get(0).getMaxDamage() - MinecraftClient.getInstance().player.getInventory().armor.get(0).getDamage()), 25, index + 4.5f, -1);
			}

			index = index + 15;
			draw.drawItem(MinecraftClient.getInstance().player.getMainHandStack(), 5, index, 1);
		}
	}


	public static void radar(MatrixStack matrices) {
		String text = "";
		int val = 1;
		int index = 20;
		int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
		TextRenderer tr = mc.textRenderer;
		if (ModuleManager.instance.getModule("Radar").isEnabled() == true) {
			for (PlayerEntity plr : mc.world.getPlayers()) {
				if (plr != mc.player) {
					if (plr.distanceTo(mc.player) <= 50) {
						if (!plr.getEntityName().startsWith("&")) {
							if (!plr.getEntityName().startsWith("CIT-")) {
								tr.draw(matrices, plr.getEntityName() + " " + ((int) plr.distanceTo(mc.player)) + " blocks away", width - tr.getWidth(plr.getEntityName() + " " + ((int) plr.distanceTo(mc.player)) + " blocks away"), index + 60, -1);
								index = index + tr.fontHeight;
								Client.INSTANCE.LOGGER.info(plr.getEntityName());
							}
						}
					}
				}
			}
		}
	}

}

 
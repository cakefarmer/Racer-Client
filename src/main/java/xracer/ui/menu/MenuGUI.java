package xracer.ui.menu;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import xracer.Client;
import xracer.module.Mod;
import xracer.module.ModuleManager;
import xracer.ui.Hud;


public class MenuGUI extends Screen {

	public static boolean menu_button = false;
	public static boolean player = false;
	public static boolean world = false;


	public static boolean render = false;
	public static final MenuGUI INSTANCE = new MenuGUI();
	protected MinecraftClient mc = MinecraftClient.getInstance();
	public static List<String> worldmods = new ArrayList<>();
	public static List<String> rendermods = new ArrayList<String>();
	public static List<String> playermods = new ArrayList<>();





	protected MenuGUI() {
		super(Text.literal("MenuGUI"));
		// TODO Auto-generated constructor stub
	}



	@Override
	public void render(MatrixStack matrices, int mx, int my, float delta) {
		super.render(matrices, mx, my, delta);
		int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
		int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
		if (menu_button == true) {
			if (mc.player != null) {
				if (isdisplayingmenu == false) {
					DrawableHelper.fill(matrices, width / 2 - 40, height / 2 - 20, width / 2 + 40, height / 2 + 20, 0x50000000);
					mc.textRenderer.draw(matrices, "Menu", width / 2 - (mc.textRenderer.getWidth("Menu") / 2), height / 2 - (mc.textRenderer.fontHeight / 2), -1);
				}
			}
		}
			if (menu_button == false) {
				isdisplayingmenu = true;
				mainmenu(matrices);
			}
		}


	public static int color_radar = 0x997fff00;
	public static int color_keys = 0x997fff00;
	public static int color_armor = 0x997fff00;
	public static boolean isdisplayingmenu = false;
	public static Mod.Category currentCategory;
	public void mainmenu(MatrixStack matrices) {

		//	DrawableHelper.fill(matrices, 200 , 50, width - 200, height-50, 0x44);
			DrawableHelper.fill(matrices, width / 2 - 140, height / 2 + 100, width / 2 + 140, height / 2 - 100, 0x44000000);

			mc.textRenderer.draw(matrices, "world", width / 2 - 120, height / 2 - 80, -1);
			mc.textRenderer.draw(matrices, "render", width / 2 - 120, height / 2 - 40, -1);
			mc.textRenderer.draw(matrices, "player", width / 2 - 120, height / 2 - 0, -1);
			if (world == true) {
			//	mc.textRenderer.draw(matrices, "Radar", width / 2 + 20, height / 2 - 80, color_radar);
				ModuleManager.instance.drawCategory(matrices, Mod.Category.world, 15);
			}
			if (render == true) {
			//	mc.textRenderer.draw(matrices, "Keystrokes", width / 2 + 20, height / 2 - 80, color_keys);
				ModuleManager.instance.drawCategory(matrices, Mod.Category.render, 15);
			}
			if (player == true) {
				ModuleManager.instance.drawCategory(matrices, Mod.Category.player, 15);
			//	mc.textRenderer.draw(matrices, "Armor Display", width / 2 + 20, height / 2 - 80, color_armor);
			}
	}

	public boolean mouseClicked(double mx, double my, int button) {
	//width / 2 + 20, height / 2 - 80
		for(Mod m : ModuleManager.instance.getModules()) {
			if (m.getCategory() == currentCategory) {
				if (mx > (width / 2 + m.getX()) && mx < (width / 2 + m.getX() + textRenderer.getWidth(m.getName())) && my > (height / 2 - m.getY() - 1) && my < (height / 2 - m.getY() + mc.textRenderer.fontHeight)) {
					m.toggle();
					Client.INSTANCE.LOGGER.info("Mod Clicked " + m.getName() + " | " + m.getY());
				}
			}
		}

		if(mx > (width / 2 - 122) && mx <  (width / 2 - 120 + textRenderer.getWidth("render")) && my > (height / 2 - 41) && my < (height /2 - 37 + mc.textRenderer.fontHeight)) {
			world = false;
			render = true;
			player = false;
			currentCategory = Mod.Category.render;
			Client.INSTANCE.LOGGER.info("ez");
		}
		if(mx > (width / 2 - 122) && mx <  (width / 2 - 120 + textRenderer.getWidth("world")) && my > (height / 2 - 81) && my < (height /2 - 77 + mc.textRenderer.fontHeight)) {
			world = true;
			render = false;
			player = false;
			currentCategory = Mod.Category.world;
			Client.INSTANCE.LOGGER.info("ez");
		}
		if(mx > (width / 2 - 122) && mx <  (width / 2 - 120 + textRenderer.getWidth("player")) && my > (height / 2 - 1) && my < (height /2 + mc.textRenderer.fontHeight)) {
			world = false;
			render = false;
			player = true;
			currentCategory = Mod.Category.player;
			Client.INSTANCE.LOGGER.info("ez");
		}

		if (mx > (width / 2 - 40) && mx < (width / 2 + 40) && my > (height / 2 - 20) && my < (height / 2 + 20)) {

			if (menu_button == true) {
				menu_button = false;

			}
		}



			return super.mouseClicked(mx, my, button);
		}



	}

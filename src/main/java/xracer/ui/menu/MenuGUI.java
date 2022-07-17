package xracer.ui.menu;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import xracer.Client;
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

	public void mainmenu(MatrixStack matrices) {

		//	DrawableHelper.fill(matrices, 200 , 50, width - 200, height-50, 0x44);
			DrawableHelper.fill(matrices, width / 2 - 140, height / 2 + 100, width / 2 + 140, height / 2 - 100, 0x44000000);

			mc.textRenderer.draw(matrices, "world", width / 2 - 120, height / 2 - 80, -1);
			mc.textRenderer.draw(matrices, "render", width / 2 - 120, height / 2 - 40, -1);
			mc.textRenderer.draw(matrices, "player", width / 2 - 120, height / 2 - 0, -1);
			if (world == true) {
				mc.textRenderer.draw(matrices, "Radar", width / 2 + 20, height / 2 - 80, color_radar);
			}
			if (render == true) {
				mc.textRenderer.draw(matrices, "Keystrokes", width / 2 + 20, height / 2 - 80, color_keys);
			}
			if (player == true) {
				mc.textRenderer.draw(matrices, "Armor Display", width / 2 + 20, height / 2 - 80, color_armor);
			}
	}

	public boolean mouseClicked(double mx, double my, int button) {
		if(mx > (width / 2 - 122) && mx <  (width / 2 - 120 + textRenderer.getWidth("render")) && my > (height / 2 - 41) && my < (height /2 - 37 + mc.textRenderer.fontHeight)) {
			world = false;
			render = true;
			player = false;
			Client.INSTANCE.LOGGER.info("ez");
		}
		if(mx > (width / 2 - 122) && mx <  (width / 2 - 120 + textRenderer.getWidth("world")) && my > (height / 2 - 81) && my < (height /2 - 77 + mc.textRenderer.fontHeight)) {
			world = true;
			render = false;
			player = false;
			Client.INSTANCE.LOGGER.info("ez");
		}
		if(mx > (width / 2 - 122) && mx <  (width / 2 - 120 + textRenderer.getWidth("player")) && my > (height / 2 - 1) && my < (height /2 + mc.textRenderer.fontHeight)) {
			world = false;
			render = false;
			player = true;
			Client.INSTANCE.LOGGER.info("ez");
		}

		if (mx > (width / 2 - 40) && mx < (width / 2 + 40) && my > (height / 2 - 20) && my < (height / 2 + 20)) {

			if (menu_button == true) {
				menu_button = false;

			}
		}
		if (mx > (width / 2 - 132) && mx < (width / 2 - 129 + textRenderer.getWidth("Radar")) && my > (height / 2 - 90) && my < (height / 2 - 83)) {
			if (world == true) {
				if (color_radar == -1) {
					color_radar = 0x997fff00;
					Hud.radar = true;
				} else {
					Hud.radar = false;
					color_radar = -1;

				}
			}
		}
			if (mx > (width / 2 - 97) && mx < (width / 2 - 92 + textRenderer.getWidth("Keystrokes")) && my > (height / 2 - 90) && my < (height / 2 - 83)) {
				if (render == true) {
					if (color_keys == -1) {
						color_keys = 0x997fff00;
						Hud.keystrokes = true;
					} else {
						Hud.keystrokes = false;
						color_keys = -1;
					}
				}
			}

			if (mx > (width / 2 - 33) && mx < (width / 2 - 31 + textRenderer.getWidth("Armor Display")) && my > (height / 2 - 90) && my < (height / 2 - 83)) {
				if (player == true) {
					if (color_armor == -1) {
						color_armor = 0x997fff00;
						Hud.armor = true;
					} else {
						Hud.armor = false;
						color_armor = -1;
					}
				}
			}


			return super.mouseClicked(mx, my, button);
		}



	}

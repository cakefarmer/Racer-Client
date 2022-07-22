package xracer.module;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import xracer.module.modules.*;


public class ModuleManager {
	
	public static final ModuleManager instance = new ModuleManager();
	private List<Mod> modules = new ArrayList<>();
	public ModuleManager() {
		addModules();
	}
	
	public Mod getModule(String name) {
		Mod mod = null;
		for(Mod module : modules) {
			if(module.getName() == name) {
				mod = module;
			}
		}
		return mod;
	}
	
	public List<Mod> getModules() {
		return modules;
	}

	public List<Mod> getEnabledModules(){
		List<Mod> enabled = new ArrayList<>();
		for(Mod module : modules) {
			if(module.isEnabled()) enabled.add(module);
		}
		return enabled;
	}


	public void drawCategory(MatrixStack matrices, Mod.Category category, int index){
		int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
		int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
		int i = 0;
		float rainbowhue = (System.currentTimeMillis() % 4000) / 4000f;
		int huerainbow = Color.HSBtoRGB(rainbowhue, 1, 1);
		for(Mod mod : ModuleManager.instance.getModules()){
			TextRenderer t = MinecraftClient.getInstance().textRenderer;
			int color = -1;
			int color2 = -1;

			if(mod.getCategory() == category){
				if(mod.isEnabled()){
					color2 = huerainbow;
				}
				t.draw(matrices, mod.getName(), width / 2 - 20, height / 2 - 80 + i, color);

				DrawableHelper.fill(matrices,width / 2 + 90,height / 2 - 70 + i,width / 2 + 110,height / 2 - 82 + i, color2);
				if(mod.isEnabled() == false) {
					DrawableHelper.fill(matrices, width / 2 + 92, height / 2 - 72 + i, width / 2 + 100, height / 2 - 80 + i, 0xff303033);
				}
				if(mod.isEnabled()){

					DrawableHelper.fill(matrices, width / 2 + 100, height / 2 - 72 + i, width / 2 + 108, height / 2 - 80 + i, 0xff303033);
				}
				//	if (mx > (width / 2 + 90) && mx < (width / 2 + 110) && my > (height / 2 - 70 + i) && my < (height / 2 - 82 + i)) {
				mod.setX(90, 110);
				mod.setY(70 - i, 82 - i);
				i = i+index;


			}
		}
	}

	private void addModules() {
		modules.add(new sprint());
		modules.add(new radar());
		modules.add(new keys());
		modules.add(new armor());
		modules.add(new fullbright());
		modules.add(new fps());
		modules.add(new coords());

	}
	
	
}

package xracer.module;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import xracer.module.modules.armor;
import xracer.module.modules.keys;
import xracer.module.modules.radar;
import xracer.module.modules.sprint;


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
	int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
	int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
	int i = 0;
	public void drawCategory(MatrixStack matrices, Mod.Category category, int index){
		int i = 0;
		for(Mod mod : ModuleManager.instance.getModules()){
			TextRenderer t = MinecraftClient.getInstance().textRenderer;
			if(mod.getCategory() == category){
				i = i+index;
				t.draw(matrices, mod.getName(), width / 2 + 20, height / 2 - 80 + i, -1);

			}
		}
	}
	private void addModules() {
		modules.add(new sprint());
		modules.add(new radar());
		modules.add(new keys());
		modules.add(new armor());
	}
	
	
}

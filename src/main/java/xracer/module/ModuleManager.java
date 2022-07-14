package xracer.module;

import java.util.ArrayList;
import java.util.List;

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

	private void addModules() {
		modules.add(new sprint());
		modules.add(new radar());
		modules.add(new keys());
		modules.add(new armor());
	}
	
	
}

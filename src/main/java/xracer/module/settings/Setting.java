package xracer.module.settings;

public class Setting {
	
	protected String name;
	
	public Setting(String name) {
		this.name = name;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	private boolean visible = true;
	
	public String getName() {
		return name;
	}

}

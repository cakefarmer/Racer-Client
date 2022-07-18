package xracer.module.modules;

import xracer.module.Mod;
import xracer.module.settings.ISimpleOption;

public class fullbright extends Mod {

    public fullbright() {
        super("Fullbright", "ez", Category.world);
    }
    @Override
    public void onTick() {
        if (this.isEnabled()) {
            ((ISimpleOption<Double>) (Object) mc.options.getGamma()).setValueUnrestricted(100.0d);


    }

}
    @Override
    public void onDisable() {
        mc.options.getGamma().setValue(0.0);
        super.onDisable();
    }

}

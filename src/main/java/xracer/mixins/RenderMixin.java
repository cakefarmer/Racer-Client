package xracer.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import xracer.ui.Hud;

@Mixin(InGameHud.class)
public class RenderMixin {
	

	@Inject(method = "render", at = @At("RETURN"), cancellable = true)
	public void renderHud(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
		Hud.render(matrices, tickDelta);
		Hud.renderArrayList(matrices);
		Hud.drawKeys(matrices);
		Hud.items(matrices);
		Hud.radar(matrices);;
	}

}

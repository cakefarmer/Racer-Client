package xracer.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import xracer.Client;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	public void onTick(CallbackInfo ci) {
		Client.INSTANCE.onTick();
	}
	
	@Inject(at = @At("TAIL"), method = "scheduleStop")
	public void onShutdown(CallbackInfo ci) {
		Client.INSTANCE.stopped();
	}
}

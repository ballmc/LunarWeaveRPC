package me.ballmc.mod.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.ballmc.mod.discord.RPC;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    private static RPC rpcInstance = new RPC();

    @Inject(method = "startGame", at = @At("HEAD"))
    public void onStartGame(CallbackInfo ci) {
        System.out.println("Starting LunarWeaveRPC");
        rpcInstance.startUpdatingDiscordActivity();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void onShutdown(CallbackInfo ci) {
        System.out.println("Shutting down LunarWeaveRPC");
        rpcInstance.shutdown();
    }
}

package ru.yurannnzzz.compat.mekatomicsilktouch.mixin;

import mekanism.api.radial.RadialData;
import mekanism.api.radial.mode.IRadialMode;
import mekanism.common.item.gear.ItemAtomicDisassembler;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import mekanism.common.network.to_server.PacketRadialModeChange;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.yurannnzzz.compat.mekatomicsilktouch.util.DisassemblerModeExt;

@Mixin(PacketRadialModeChange.class)
public abstract class PacketRadialModeChangeMixin {
    @Inject(at = @At("TAIL"), method = "setMode(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lmekanism/common/lib/radial/IGenericRadialModeItem;Lmekanism/api/radial/RadialData;)V", remap = false)
    private <MODE extends IRadialMode>  void changeModeMixin(Player player, ItemStack stack, IGenericRadialModeItem item, RadialData<MODE> radialData, CallbackInfo ci) {
        if (stack.isEmpty() || !(stack.getItem() instanceof ItemAtomicDisassembler itemAtomicDisassembler)) return;

        DisassemblerModeExt.updateItemMode(itemAtomicDisassembler, stack);
    }
}

package ru.yurannnzzz.compat.mekatomicsilktouch.mixin;

import mekanism.common.item.gear.ItemAtomicDisassembler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.yurannnzzz.compat.mekatomicsilktouch.util.DisassemblerModeExt;

@Mixin(ItemAtomicDisassembler.class)
public abstract class ItemAtomicDisassemblerMixin {
    @Inject(at = @At("TAIL"), method = "changeMode(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;IZ)V", remap = false)
    private void changeModeMixin(Player player, ItemStack stack, int shift, boolean displayChangeMessage, CallbackInfo ci) {
        if (stack.isEmpty() || !(stack.getItem() instanceof ItemAtomicDisassembler itemAtomicDisassembler)) return;

        DisassemblerModeExt.updateItemMode(itemAtomicDisassembler, stack);
    }
}

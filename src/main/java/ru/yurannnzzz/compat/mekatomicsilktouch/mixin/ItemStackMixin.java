package ru.yurannnzzz.compat.mekatomicsilktouch.mixin;

import mekanism.common.item.gear.ItemAtomicDisassembler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.yurannnzzz.compat.mekatomicsilktouch.util.DisassemblerModeExt;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Inject(at = @At("RETURN"), method = "isEnchanted()Z", cancellable = true)
    private void forceNoEnchants(CallbackInfoReturnable<Boolean> cir) {
        if (getItem() instanceof ItemAtomicDisassembler itemAtomicDisassembler) {
            if (itemAtomicDisassembler.getMode((ItemStack)(Object)this) == DisassemblerModeExt.SILK_TOUCH) {
                cir.setReturnValue(false);
            }
        }
    }
}

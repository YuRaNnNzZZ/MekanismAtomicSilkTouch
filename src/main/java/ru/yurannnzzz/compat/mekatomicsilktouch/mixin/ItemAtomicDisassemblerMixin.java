package ru.yurannnzzz.compat.mekatomicsilktouch.mixin;

import mekanism.common.item.gear.ItemAtomicDisassembler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.yurannnzzz.compat.mekatomicsilktouch.util.DisassemblerModeExt;

import java.util.Map;

@Mixin(ItemAtomicDisassembler.class)
public abstract class ItemAtomicDisassemblerMixin {
    @Inject(at = @At("TAIL"), method = "setMode(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lmekanism/common/item/gear/ItemAtomicDisassembler$DisassemblerMode;)V", remap = false)
    private void changeModeMixin(ItemStack stack, Player player, ItemAtomicDisassembler.DisassemblerMode mode, CallbackInfo ci) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (mode == DisassemblerModeExt.SILK_TOUCH) {
            enchantments.put(Enchantments.SILK_TOUCH, 1);
        } else {
            enchantments.remove(Enchantments.SILK_TOUCH);
        }
        EnchantmentHelper.setEnchantments(enchantments, stack);
    }
}

package ru.yurannnzzz.compat.mekatomicsilktouch.util;

import mekanism.common.item.gear.ItemAtomicDisassembler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Map;

public class DisassemblerModeExt {
    public static ItemAtomicDisassembler.DisassemblerMode SILK_TOUCH;

    public static void updateItemMode(ItemAtomicDisassembler item, ItemStack stack) {
        ItemAtomicDisassembler.DisassemblerMode mode = item.getMode(stack);

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        if (mode == DisassemblerModeExt.SILK_TOUCH) {
            enchantments.put(Enchantments.SILK_TOUCH, 1);
        } else {
            enchantments.remove(Enchantments.SILK_TOUCH);
        }
        EnchantmentHelper.setEnchantments(enchantments, stack);
    }
}

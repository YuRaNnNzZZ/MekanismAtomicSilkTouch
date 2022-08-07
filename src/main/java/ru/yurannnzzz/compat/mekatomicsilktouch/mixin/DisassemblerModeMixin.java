package ru.yurannnzzz.compat.mekatomicsilktouch.mixin;

import mekanism.api.text.EnumColor;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.item.gear.ItemAtomicDisassembler;
import mekanism.common.util.MekanismUtils;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.yurannnzzz.compat.mekatomicsilktouch.util.DisassemblerModeExt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;

@Mixin(ItemAtomicDisassembler.DisassemblerMode.class)
@Unique
public abstract class DisassemblerModeMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    private static ItemAtomicDisassembler.DisassemblerMode[] MODES;

    @Inject(at = @At("TAIL"), method = "<clinit>", remap = false)
    private static void registerExtModes(CallbackInfo ci) {
        DisassemblerModeExt.SILK_TOUCH = appendExtendedMode("SILK_TOUCH", MekanismLang.MINER_SILK, 20, () -> true, EnumColor.BRIGHT_GREEN, MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "wrench.png"));
    }

    @Invoker(value = "<init>", remap = false)
    public static ItemAtomicDisassembler.DisassemblerMode initExtendedMode(String internalName, int internalId, ILangEntry langEntry, int efficiency, BooleanSupplier checkEnabled, EnumColor color, ResourceLocation icon) {
        throw new AssertionError();
    }

    @Inject(at = @At("RETURN"), method = "values", cancellable = true, remap = false)
    private static void values(CallbackInfoReturnable<ItemAtomicDisassembler.DisassemblerMode[]> cir) {
        if (MODES == null) return;

        cir.setReturnValue(MODES);
    }

    private static ItemAtomicDisassembler.DisassemblerMode appendExtendedMode(String internalName, ILangEntry langEntry, int efficiency, BooleanSupplier checkEnabled, EnumColor color, ResourceLocation icon) {
        ArrayList<ItemAtomicDisassembler.DisassemblerMode> variants = new ArrayList<>(Arrays.asList(MODES));

        ItemAtomicDisassembler.DisassemblerMode last = variants.get(variants.size() - 1);
        ItemAtomicDisassembler.DisassemblerMode newMode = initExtendedMode(internalName, last.ordinal() + 1, langEntry, efficiency, checkEnabled, color, icon);
        variants.add(newMode);

        MODES = variants.toArray(new ItemAtomicDisassembler.DisassemblerMode[0]);

        return newMode;
    }
}

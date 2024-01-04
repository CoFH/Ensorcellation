package cofh.ensorcellation.mixin;

import cofh.ensorcellation.common.config.OverrideEnchantmentConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;

@Mixin (ThornsEnchantment.class)
public abstract class ThornsEnchantmentMixin extends Enchantment {

    public ThornsEnchantmentMixin(Enchantment.Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {

        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!OverrideEnchantmentConfig.improvedThorns) {
            return super.canApplyAtEnchantingTable(stack);
        }
        Item item = stack.getItem();
        return super.canApplyAtEnchantingTable(stack) || item instanceof HorseArmorItem || item.canPerformAction(stack, ToolActions.SHIELD_BLOCK);
    }

}
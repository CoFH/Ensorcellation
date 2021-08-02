package cofh.ensorcellation.enchantment.nyi;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;

public class SoulSpeedEnchantmentImp extends EnchantmentOverride {

    public SoulSpeedEnchantmentImp() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET});
        maxLevel = 3;
        treasureEnchantment = true;

        allowGenerateInLoot = false;
        allowVillagerTrade = false;
    }

    @Override
    public int getMinEnchantability(int level) {

        return level * 10;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 15;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
        }
        return super.canApplyAtEnchantingTable(stack) || stack.getItem() instanceof HorseArmorItem;
    }

}
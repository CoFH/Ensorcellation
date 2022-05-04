package cofh.ensorcellation.enchantment.nyi;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SoulSpeedEnchantmentImp extends EnchantmentOverride {

    public SoulSpeedEnchantmentImp() {

        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
        maxLevel = 3;
        treasureEnchantment = true;

        allowGenerateInLoot = false;
        allowVillagerTrade = false;
    }

    @Override
    public int getMinCost(int level) {

        return level * 10;
    }

    @Override
    public int getMaxCost(int level) {

        return getMinCost(level) + 15;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
        }
        return super.canApplyAtEnchantingTable(stack) || stack.getItem() instanceof HorseArmorItem;
    }

}
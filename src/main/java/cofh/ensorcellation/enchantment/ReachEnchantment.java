package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ReachEnchantment extends EnchantmentCoFH {

    public ReachEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.ARMOR_CHEST, new EquipmentSlotType[]{EquipmentSlotType.CHEST});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

}

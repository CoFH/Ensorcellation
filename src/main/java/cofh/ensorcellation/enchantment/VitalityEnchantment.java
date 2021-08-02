package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class VitalityEnchantment extends EnchantmentCoFH {

    public static int health = 4;

    public VitalityEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_CHEST, new EquipmentSlotType[]{EquipmentSlotType.CHEST});
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

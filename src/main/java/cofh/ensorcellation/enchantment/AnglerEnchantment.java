package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class AnglerEnchantment extends EnchantmentCoFH {

    public static int chance = 50;

    public AnglerEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentType.FISHING_ROD, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
        treasureEnchantment = true;
    }

    @Override
    public int getMinCost(int level) {

        return 10 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 15;
    }

}

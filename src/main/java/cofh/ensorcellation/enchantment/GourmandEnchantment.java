package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class GourmandEnchantment extends EnchantmentCoFH {

    public GourmandEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 5 + (level - 1) * 10;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

}

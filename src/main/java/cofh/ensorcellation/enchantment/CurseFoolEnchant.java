package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CurseFoolEnchant extends EnchantmentCoFH {

    public CurseFoolEnchant() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
        treasureEnchantment = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 25;
    }

    @Override
    protected int maxDelegate(int level) {

        return 50;
    }

    @Override
    public boolean isCurse() {

        return true;
    }

}

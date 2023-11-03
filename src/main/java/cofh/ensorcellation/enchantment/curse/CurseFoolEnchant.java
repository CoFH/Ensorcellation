package cofh.ensorcellation.enchantment.curse;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CurseFoolEnchant extends EnchantmentCoFH {

    public CurseFoolEnchant() {

        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
        treasureEnchantment = true;
    }

    @Override
    public int getMinCost(int level) {

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

package cofh.ensorcellation.common.enchantment.curse;

import cofh.core.init.CoreEnchantments;
import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;

public class CurseMercyEnchantment extends EnchantmentCoFH {

    public CurseMercyEnchantment() {

        super(Rarity.VERY_RARE, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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

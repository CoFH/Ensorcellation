package cofh.ensorcellation.common.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;

public class WeedingEnchantment extends EnchantmentCoFH {

    public WeedingEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.HOE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

}
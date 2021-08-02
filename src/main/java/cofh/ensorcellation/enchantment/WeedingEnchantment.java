package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.inventory.EquipmentSlotType;

public class WeedingEnchantment extends EnchantmentCoFH {

    public WeedingEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

}
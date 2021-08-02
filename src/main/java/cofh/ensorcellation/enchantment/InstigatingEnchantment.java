package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.inventory.EquipmentSlotType;

public class InstigatingEnchantment extends DamageEnchantmentCoFH {

    public InstigatingEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 1;
        treasureEnchantment = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return level * 25;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

}

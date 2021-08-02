package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class ExcavatingEnchantment extends EnchantmentCoFH {

    public ExcavatingEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.PICKAXE_OR_SHOVEL, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
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

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.LOOTING;
    }

}
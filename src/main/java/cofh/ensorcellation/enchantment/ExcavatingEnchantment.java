package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class ExcavatingEnchantment extends EnchantmentCoFH {

    public ExcavatingEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.PICKAXE_OR_SHOVEL, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        treasureEnchantment = true;
    }

    @Override
    public int getMinCost(int level) {

        return level * 25;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != Enchantments.MOB_LOOTING;
    }

}
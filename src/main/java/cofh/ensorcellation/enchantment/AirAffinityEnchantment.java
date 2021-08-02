package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class AirAffinityEnchantment extends EnchantmentCoFH {

    public AirAffinityEnchantment() {

        super(Rarity.RARE, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 40;
    }

}

package cofh.ensorcellation.common.enchantment;

import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AirAffinityEnchantment extends EnchantmentCoFH {

    public AirAffinityEnchantment() {

        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }

    @Override
    public int getMinCost(int level) {

        return 1;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 40;
    }

}

package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class FireAspectEnchantmentImp extends EnchantmentOverride {

    public FireAspectEnchantmentImp() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxEnchantability(int level) {

        return super.getMinEnchantability(level) + 50;
    }

}

package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class KnockbackEnchantmentImp extends EnchantmentOverride {

    public KnockbackEnchantmentImp() {

        super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 5 + 20 * (level - 1);
    }

}

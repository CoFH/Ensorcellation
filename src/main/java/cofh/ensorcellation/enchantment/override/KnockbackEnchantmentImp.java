package cofh.ensorcellation.enchantment.override;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.inventory.EquipmentSlotType;

public class KnockbackEnchantmentImp extends EnchantmentOverride {

    public KnockbackEnchantmentImp() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinCost(int level) {

        return 5 + 20 * (level - 1);
    }

}

package cofh.ensorcellation.enchantment.override;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

import static cofh.lib.util.references.EnsorcReferences.EXCAVATING;

public class LootingEnchantmentImp extends EnchantmentOverride {

    public LootingEnchantmentImp() {

        super(Rarity.RARE, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    public int getMaxCost(int level) {

        return super.getMinCost(level) + 50;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != Enchantments.SILK_TOUCH && ench != EXCAVATING;
    }

}

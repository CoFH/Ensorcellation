package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

import static cofh.lib.util.references.EnsorcReferences.TILLING;

public class FurrowingEnchantment extends EnchantmentCoFH {

    public FurrowingEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 4;
    }

    @Override
    public int getMinCost(int level) {

        return 10 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != TILLING;
    }

}
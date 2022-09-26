package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

import static cofh.core.util.references.EnsorcReferences.FURROWING;

public class TillingEnchantment extends EnchantmentCoFH {

    public TillingEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.HOE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 4;
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

        return super.checkCompatibility(ench) && ench != FURROWING;
    }

}
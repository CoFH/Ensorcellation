package cofh.ensorcellation.common.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class SoulboundEnchantment extends EnchantmentCoFH {

    public static boolean permanent = true;

    public SoulboundEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.ENCHANTABLE, EquipmentSlot.values());
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 1 + (level - 1) * 5;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

    @Override
    public int getMaxLevel() {

        return permanent ? 1 : maxLevel;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != Enchantments.VANISHING_CURSE;
    }

}

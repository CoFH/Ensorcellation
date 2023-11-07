package cofh.ensorcellation.common.enchantment;

import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static cofh.core.util.helpers.ArcheryHelper.validBow;

public class HunterEnchantment extends EnchantmentCoFH {

    public static int chance = 50;

    public HunterEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 2;
        treasureEnchantment = true;
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
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (stack.canApplyAtEnchantingTable(this) || validBow(stack));
    }

}

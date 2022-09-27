package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static cofh.core.util.helpers.ArcheryHelper.validBow;

public class QuickdrawEnchantment extends EnchantmentCoFH {

    public QuickdrawEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 12 + (level - 1) * 20;
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

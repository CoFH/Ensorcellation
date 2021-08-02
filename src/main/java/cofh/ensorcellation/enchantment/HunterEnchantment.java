package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import static cofh.lib.util.helpers.ArcheryHelper.validBow;

public class HunterEnchantment extends EnchantmentCoFH {

    public static int chance = 50;

    public HunterEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentType.BOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
        treasureEnchantment = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (stack.canApplyAtEnchantingTable(this) || validBow(stack) || supportsEnchantment(stack));
    }

}

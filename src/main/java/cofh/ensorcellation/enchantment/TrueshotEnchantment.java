package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static cofh.core.util.helpers.ArcheryHelper.validBow;
import static cofh.core.util.references.EnsorcIDs.ID_VOLLEY;
import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;

public class TrueshotEnchantment extends EnchantmentCoFH {

    public TrueshotEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinCost(int level) {

        return 15 + (level - 1) * 10;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (validBow(stack));
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != ENCHANTMENTS.get(ID_VOLLEY);
    }

}

package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import static cofh.lib.util.helpers.ArcheryHelper.validBow;
import static cofh.lib.util.references.EnsorcReferences.TRUESHOT;

public class VolleyEnchantment extends EnchantmentCoFH {

    public VolleyEnchantment() {

        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinCost(int level) {

        return 20;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return enable && (validBow(stack) || supportsEnchantment(stack));
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != TRUESHOT;
    }

}

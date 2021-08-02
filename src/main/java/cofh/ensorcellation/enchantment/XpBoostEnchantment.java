package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.Random;

public class XpBoostEnchantment extends EnchantmentCoFH {

    public static int xp = 4;

    public XpBoostEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    // region HELPERS
    public static int getExp(int baseExp, int level, Random rand) {

        return baseExp + level + rand.nextInt(1 + level * xp);
    }
    // endregion
}

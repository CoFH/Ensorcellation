package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class PilferingEnchantment extends EnchantmentCoFH {

    public static boolean allowPlayerStealing = true;

    public PilferingEnchantment() {

        super(Rarity.RARE, EnchantmentType.FISHING_ROD, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        treasureEnchantment = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 25;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

}

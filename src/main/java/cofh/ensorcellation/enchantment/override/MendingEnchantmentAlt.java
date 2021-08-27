package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLATION;

public class MendingEnchantmentAlt extends EnchantmentOverride {

    public static float anvilDamage = 0.03F;

    public MendingEnchantmentAlt() {

        super(Rarity.RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
        descriptionId = "enchantment." + ID_ENSORCELLATION + ".preservation";
        treasureEnchantment = true;
    }

    public EnchantmentCoFH setEnable(boolean enable) {

        this.enable = enable;
        descriptionId = "enchantment." + (enable ? ID_ENSORCELLATION + ".preservation" : "minecraft.mending");
        return this;
    }

    @Override
    public int getMinCost(int level) {

        return level * 25;
    }

    @Override
    public int getMaxCost(int level) {

        return getMinCost(level) + 50;
    }

}

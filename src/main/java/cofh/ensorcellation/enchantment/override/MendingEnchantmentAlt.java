package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;

public class MendingEnchantmentAlt extends EnchantmentOverride {

    public static float anvilDamage = 0.03F;

    public MendingEnchantmentAlt() {

        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values());
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

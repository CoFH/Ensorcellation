package cofh.ensorcellation.common.enchantment;

import cofh.lib.common.enchantment.EnchantmentCoFH;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;

public class ProtectionEnchantmentMagic extends EnchantmentCoFH {

    public ProtectionEnchantmentMagic(Rarity rarityIn, EquipmentSlot[] slots) {

        super(rarityIn, EnchantmentCategory.ARMOR, slots);
        maxLevel = 4;
    }

    @Override
    public int getDamageProtection(int level, DamageSource source) {

        if (level <= 0 || source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return 0;
        } else if (source.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            return level * 2;
        }
        return 0;
    }

    @Override
    public int getMinCost(int level) {

        return 10 + (level - 1) * 8;
    }

    @Override
    public int getMaxCost(int level) {

        return getMinCost(level) + 8;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        if (ench instanceof ProtectionEnchantment protect) {
            return protect.type == ProtectionEnchantment.Type.FALL;
        } else {
            return super.checkCompatibility(ench);
        }
    }

}

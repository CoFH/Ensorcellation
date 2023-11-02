package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ProtectionEnchantmentImp extends EnchantmentOverride {

    private final Type protectionType;

    public ProtectionEnchantmentImp(Rarity rarityIn, Type protectionTypeIn, EquipmentSlot[] slots) {

        super(rarityIn, protectionTypeIn == Type.FALL ? EnchantmentCategory.ARMOR_FEET : EnchantmentCategory.ARMOR, slots);
        this.protectionType = protectionTypeIn;

        maxLevel = 4;
    }

    @Override
    public int getDamageProtection(int level, DamageSource source) {

        if (level <= 0 || source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return 0;
        } else if (this.protectionType == Type.ALL) {
            return level;
        } else if (this.protectionType == Type.FALL && source.is(DamageTypeTags.IS_FALL)) {
            return level * 3;
        } else if (this.protectionType == Type.FIRE && source.is(DamageTypeTags.IS_FIRE)) {
            return level * 2;
        } else if (this.protectionType == Type.EXPLOSION && source.is(DamageTypeTags.IS_EXPLOSION)) {
            return level * 2;
        } else if (this.protectionType == Type.MAGIC && source.is(DamageTypeTags.WITCH_RESISTANT_TO)) {
            return level * 2;
        } else {
            return this.protectionType == Type.PROJECTILE && source.is(DamageTypeTags.IS_PROJECTILE) ? level * 2 : 0;
        }
    }

    @Override
    public int getMinCost(int level) {

        return protectionType.getMinimalEnchantability() + (level - 1) * protectionType.getEnchantIncreasePerLevel();
    }

    @Override
    public int getMaxCost(int level) {

        return getMinCost(level) + protectionType.getEnchantIncreasePerLevel();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            if (this.protectionType == Type.MAGIC) {
                return false;
            }
            return super.canApplyAtEnchantingTable(stack);
        }
        return super.canApplyAtEnchantingTable(stack) || stack.getItem() instanceof HorseArmorItem;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        if (ench instanceof ProtectionEnchantmentImp) {
            ProtectionEnchantmentImp enchProtection = (ProtectionEnchantmentImp) ench;
            if (this.protectionType == enchProtection.protectionType) {
                return false;
            } else {
                return this.protectionType == Type.FALL || enchProtection.protectionType == Type.FALL;
            }
        } else {
            return super.checkCompatibility(ench);
        }
    }

    @Override
    public boolean isAllowedOnBooks() {

        return this.protectionType == Type.MAGIC ? enable && allowOnBooks : super.isAllowedOnBooks();
    }

    // region TYPE
    public enum Type {
        ALL("all", 1, 11),
        FALL("fall", 5, 6),
        FIRE("fire", 10, 8),
        EXPLOSION("explosion", 5, 8),
        MAGIC("magic", 10, 8),
        PROJECTILE("projectile", 3, 6);

        private final String typeName;
        private final int minEnchantability;
        private final int levelCost;

        Type(String typeName, int minEnchantability, int levelCost) {

            this.typeName = typeName;
            this.minEnchantability = minEnchantability;
            this.levelCost = levelCost;
        }

        public int getMinimalEnchantability() {

            return this.minEnchantability;
        }

        public int getEnchantIncreasePerLevel() {

            return this.levelCost;
        }
    }
    // endregion
}

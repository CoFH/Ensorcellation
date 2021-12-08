package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.Map;
import java.util.Random;

import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static net.minecraft.enchantment.Enchantments.THORNS;

public class ThornsEnchantmentImp extends EnchantmentOverride {

    public static int chance = 15;

    public ThornsEnchantmentImp() {

        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_CHEST, ARMOR_SLOTS);
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {

        return super.getMinCost(level) + 50;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {

        Item item = stack.getItem();
        if (!enable) {
            return item instanceof ArmorItem || super.canEnchant(stack);
        }
        return item instanceof ArmorItem || item instanceof HorseArmorItem || item.isShield(stack, null);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
        }
        Item item = stack.getItem();
        return super.canApplyAtEnchantingTable(stack) || item instanceof HorseArmorItem || item.isShield(stack, null);
    }

    // region HELPERS
    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {

        Random rand = user.getRandom();
        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.getRandomItemWith(THORNS, user);
        if (shouldHit(level, rand)) {
            if (attacker != null) {
                attacker.hurt(DamageSource.thorns(user), (float) ThornsEnchantment.getDamage(level, rand));
            }
            if (stack != null) {
                (stack.getValue()).hurtAndBreak(3, user, (entity) -> entity.broadcastBreakEvent(stack.getKey()));
            }
        } else if (stack != null) {
            (stack.getValue()).hurtAndBreak(1, user, (entity) -> entity.broadcastBreakEvent(stack.getKey()));
        }

    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}

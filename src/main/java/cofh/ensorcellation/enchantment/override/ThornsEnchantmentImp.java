package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraftforge.common.ToolActions;

import java.util.Map;

import static cofh.lib.util.Constants.ARMOR_SLOTS;

public class ThornsEnchantmentImp extends EnchantmentOverride {

    public static int chance = 15;

    public ThornsEnchantmentImp() {

        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, ARMOR_SLOTS);
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
        return item instanceof ArmorItem || item instanceof HorseArmorItem || item.canPerformAction(stack, ToolActions.SHIELD_BLOCK);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
        }
        Item item = stack.getItem();
        return super.canApplyAtEnchantingTable(stack) || item instanceof HorseArmorItem || item.canPerformAction(stack, ToolActions.SHIELD_BLOCK);
    }

    // region HELPERS
    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {

        RandomSource rand = user.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> stack = EnchantmentHelper.getRandomItemWith(Enchantments.THORNS, user);
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

    public static boolean shouldHit(int level, RandomSource rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}

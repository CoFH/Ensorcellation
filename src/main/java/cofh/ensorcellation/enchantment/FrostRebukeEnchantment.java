package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.ToolActions;

import java.util.Map;
import java.util.Random;

import static cofh.core.init.CoreMobEffects.CHILLED;
import static cofh.core.init.CoreParticles.FROST;
import static cofh.core.util.references.EnsorcIDs.ID_DISPLACEMENT;
import static cofh.core.util.references.EnsorcIDs.ID_FIRE_REBUKE;
import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;
import static cofh.lib.util.Constants.ARMOR_SLOTS;

public class FrostRebukeEnchantment extends EnchantmentCoFH {

    public static int chance = 20;
    public static boolean mobsAffectPlayers = false;

    public FrostRebukeEnchantment() {

        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, ARMOR_SLOTS);
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 5 + 15 * (level - 1);
    }

    @Override
    protected int maxDelegate(int level) {

        return super.getMinCost(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (super.canApplyAtEnchantingTable(stack) || item instanceof HorseArmorItem || item.canPerformAction(stack, ToolActions.SHIELD_BLOCK));
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != Enchantments.THORNS && ench != ENCHANTMENTS.get(ID_DISPLACEMENT) && ench != ENCHANTMENTS.get(ID_FIRE_REBUKE);
    }

    // region HELPERS
    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        Map.Entry<EquipmentSlot, ItemStack> stack = EnchantmentHelper.getRandomItemWith(this, user);
        if (shouldHit(level, user.getRandom())) {
            onHit(user, attacker, level);
            if (stack != null) {
                (stack.getValue()).hurtAndBreak(2, user, (entity) -> entity.broadcastBreakEvent(stack.getKey()));
            }
        }
    }

    public static void onHit(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        if (user instanceof Player || !(attacker instanceof Player) || mobsAffectPlayers) {
            ((LivingEntity) attacker).knockback(0.5F * level, user.getX() - attacker.getX(), user.getZ() - attacker.getZ());
        }
        Random rand = user.getRandom();
        int i = 20 + 20 * rand.nextInt(3 * level);
        if (attacker.isOnFire()) {
            attacker.clearFire();
        }
        ((LivingEntity) attacker).addEffect(new MobEffectInstance(CHILLED.get(), i, level - 1, false, false));
        if (attacker.level instanceof ServerLevel) {
            for (int j = 0; j < 3 * level; ++j) {
                Utils.spawnParticles(attacker.level, (SimpleParticleType) FROST.get(), attacker.getX() + rand.nextDouble(), attacker.getY() + 1.0D + rand.nextDouble(), attacker.getZ() + rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}

package cofh.ensorcellation.common.event;

import cofh.ensorcellation.common.enchantment.DisplacementEnchantment;
import cofh.ensorcellation.common.enchantment.FireRebukeEnchantment;
import cofh.ensorcellation.common.enchantment.FrostRebukeEnchantment;
import cofh.ensorcellation.common.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.ensorcellation.init.registries.ModEnchantments.*;
import static cofh.lib.util.Utils.getItemEnchantmentLevel;
import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;
import static net.minecraft.world.item.enchantment.Enchantments.*;

@Mod.EventBusSubscriber (modid = ID_ENSORCELLATION)
public class HorseEnchEvents {

    private static final int HORSE_MODIFIER = 3;

    private HorseEnchEvents() {

    }

    @SubscribeEvent
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();

        if (!(entity instanceof AbstractHorse)) {
            return;
        }
        ItemStack armor = entity.getSlot(1).get();
        if (!armor.isEmpty()) {
            // FROST WALKER
            int encFrostWalker = getItemEnchantmentLevel(FROST_WALKER, armor);
            if (event.getSource().equals(entity.damageSources().hotFloor()) && encFrostWalker > 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingHurtEvent(LivingHurtEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();

        if (!(entity instanceof AbstractHorse)) {
            return;
        }
        ItemStack armor = entity.getSlot(1).get();
        if (!armor.isEmpty()) {
            int totalProtection = 0;
            // PROTECTION
            int encProtection = getItemEnchantmentLevel(ALL_DAMAGE_PROTECTION, armor);
            if (encProtection > 0) {
                totalProtection += ALL_DAMAGE_PROTECTION.getDamageProtection(encProtection, source);
            }
            // FIRE PROTECTION
            int encProtectionFire = getItemEnchantmentLevel(FIRE_PROTECTION, armor);
            if (encProtectionFire > 0) {
                totalProtection += FIRE_PROTECTION.getDamageProtection(encProtection, source);
            }
            // FEATHER FALLING
            int encProtectionFall = getItemEnchantmentLevel(FALL_PROTECTION, armor);
            if (encProtectionFall > 0) {
                totalProtection += FALL_PROTECTION.getDamageProtection(encProtection, source);
            }
            // BLAST PROTECTION
            int encProtectionExplosion = getItemEnchantmentLevel(BLAST_PROTECTION, armor);
            if (encProtectionExplosion > 0) {
                totalProtection += BLAST_PROTECTION.getDamageProtection(encProtection, source);
            }
            // PROJECTILE PROTECTION
            int encProtectionProjectile = getItemEnchantmentLevel(PROJECTILE_PROTECTION, armor);
            if (encProtectionProjectile > 0) {
                totalProtection += PROJECTILE_PROTECTION.getDamageProtection(encProtection, source);
            }
            float damageReduction = Math.min(totalProtection * HORSE_MODIFIER, 20.0F);
            event.setAmount(event.getAmount() * (1.0F - damageReduction / 25.0F));

            if (attacker != null) {
                // THORNS
                int encThorns = getItemEnchantmentLevel(THORNS, armor);
                if (ThornsEnchantment.shouldHit(encThorns, entity.getRandom())) {
                    attacker.hurt(entity.damageSources().thorns(entity), ThornsEnchantment.getDamage(encThorns, MathHelper.RANDOM));
                }
                // DISPLACEMENT
                int encDisplacement = getItemEnchantmentLevel(DISPLACEMENT.get(), armor);
                if (DisplacementEnchantment.shouldHit(encDisplacement, entity.getRandom())) {
                    DisplacementEnchantment.onHit(entity, attacker, encDisplacement);
                }
                // FIRE REBUKE
                int encFireRebuke = getItemEnchantmentLevel(FIRE_REBUKE.get(), armor);
                if (FireRebukeEnchantment.shouldHit(encFireRebuke, entity.getRandom())) {
                    FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
                }
                // FROST REBUKE
                int encFrostRebuke = getItemEnchantmentLevel(FROST_REBUKE.get(), armor);
                if (FrostRebukeEnchantment.shouldHit(encFrostRebuke, entity.getRandom())) {
                    FrostRebukeEnchantment.onHit(entity, attacker, encFrostRebuke);
                }
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingUpdateEvent(LivingEvent.LivingTickEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof AbstractHorse)) {
            return;
        }
        ItemStack armor = entity.getSlot(1).get();
        if (!armor.isEmpty()) {
            // FROST WALKER
            int encFrostWalker = getItemEnchantmentLevel(FROST_WALKER, armor);
            if (encFrostWalker > 0) {
                FrostWalkerEnchantment.onEntityMoved(entity, entity.level, entity.blockPosition(), encFrostWalker);
                FrostWalkerEnchantmentImp.freezeNearby(entity, entity.level, entity.blockPosition(), encFrostWalker);
            }
        }
    }

}
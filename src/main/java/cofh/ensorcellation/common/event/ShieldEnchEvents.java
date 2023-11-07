package cofh.ensorcellation.common.event;

import cofh.ensorcellation.common.enchantment.DisplacementEnchantment;
import cofh.ensorcellation.common.enchantment.FireRebukeEnchantment;
import cofh.ensorcellation.common.enchantment.FrostRebukeEnchantment;
import cofh.ensorcellation.common.enchantment.PhalanxEnchantment;
import cofh.ensorcellation.common.enchantment.override.ThornsEnchantmentImp;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.core.util.references.EnsorcIDs.ID_BULWARK;
import static cofh.core.util.references.EnsorcIDs.ID_PHALANX;
import static cofh.ensorcellation.init.registries.ModEnchantments.*;
import static cofh.lib.util.Constants.UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE;
import static cofh.lib.util.Constants.UUID_ENCH_PHALANX_MOVEMENT_SPEED;
import static cofh.lib.util.Utils.getItemEnchantmentLevel;
import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;
import static net.minecraft.world.item.enchantment.Enchantments.THORNS;

@Mod.EventBusSubscriber (modid = ID_ENSORCELLATION)
public class ShieldEnchEvents {

    private ShieldEnchEvents() {

    }

    @SubscribeEvent
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();
        ItemStack stack = entity.getUseItem();

        if (canBlockDamageSource(entity, source) && attacker != null) {
            // THORNS
            int encThorns = getItemEnchantmentLevel(THORNS, stack);
            if (ThornsEnchantmentImp.shouldHit(encThorns, entity.getRandom())) {
                attacker.hurt(entity.damageSources().thorns(entity), ThornsEnchantment.getDamage(encThorns, entity.getRandom()));
            }
            // DISPLACEMENT
            int encDisplacement = getItemEnchantmentLevel(DISPLACEMENT.get(), stack);
            if (DisplacementEnchantment.shouldHit(encDisplacement, entity.getRandom())) {
                DisplacementEnchantment.onHit(entity, attacker, encDisplacement);
            }
            // FIRE REBUKE
            int encFireRebuke = getItemEnchantmentLevel(FIRE_REBUKE.get(), stack);
            if (FireRebukeEnchantment.shouldHit(encFireRebuke, entity.getRandom())) {
                FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
            }
            // FROST REBUKE
            int encFrostRebuke = getItemEnchantmentLevel(FROST_REBUKE.get(), stack);
            if (FrostRebukeEnchantment.shouldHit(encFrostRebuke, entity.getRandom())) {
                FrostRebukeEnchantment.onHit(entity, attacker, encFrostRebuke);
            }
            // BULWARK
            int encBulwark = getItemEnchantmentLevel(BULWARK.get(), stack);
            if (encBulwark > 0 && attacker instanceof Player) {
                Player playerAttacker = (Player) attacker;
                if (playerAttacker.getRandom().nextFloat() < 0.5F) {
                    playerAttacker.getCooldowns().addCooldown(playerAttacker.getMainHandItem().getItem(), 100);
                    attacker.level.broadcastEntityEvent(attacker, (byte) 30);
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
        ItemStack stack = entity.getUseItem();

        AttributeInstance knockbackResAttr = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        AttributeInstance moveSpeedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (knockbackResAttr != null) {
            knockbackResAttr.removeModifier(UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE);
        }
        if (moveSpeedAttr != null) {
            moveSpeedAttr.removeModifier(UUID_ENCH_PHALANX_MOVEMENT_SPEED);
        }
        if (stack.getItem().canPerformAction(stack, ToolActions.SHIELD_BLOCK)) {
            // BULWARK
            int encBulwark = getItemEnchantmentLevel(BULWARK.get(), stack);
            if (knockbackResAttr != null) {
                if (encBulwark > 0) {
                    knockbackResAttr.addTransientModifier(new AttributeModifier(UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE, ID_BULWARK, 1.0D, ADDITION));
                }
            }
            // PHALANX
            int encPhalanx = getItemEnchantmentLevel(PHALANX.get(), stack);
            if (moveSpeedAttr != null) {
                if (encPhalanx > 0) {
                    moveSpeedAttr.addTransientModifier(new AttributeModifier(UUID_ENCH_PHALANX_MOVEMENT_SPEED, ID_PHALANX, PhalanxEnchantment.SPEED * encPhalanx, MULTIPLY_TOTAL));
                }
            }
        }
    }

    // region HELPERS
    private static boolean canBlockDamageSource(LivingEntity living, DamageSource source) {

        Entity entity = source.getDirectEntity();
        if (entity instanceof AbstractArrow) {
            AbstractArrow arrow = (AbstractArrow) entity;
            if (arrow.getPierceLevel() > 0) {
                return false;
            }
        }
        if (!source.is(DamageTypeTags.BYPASSES_SHIELD) && living.isBlocking()) {
            Vec3 vec3d2 = source.getSourcePosition();
            if (vec3d2 != null) {
                Vec3 vec3d = living.getViewVector(1.0F);
                Vec3 vec3d1 = vec3d2.vectorTo(new Vec3(living.getX(), living.getY(), living.getZ())).normalize();
                vec3d1 = new Vec3(vec3d1.x, 0.0D, vec3d1.z);
                return vec3d1.dot(vec3d) < 0.0D;
            }
        }
        return false;
    }
    // endregion
}
package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.DisplacementEnchantment;
import cofh.ensorcellation.enchantment.FireRebukeEnchantment;
import cofh.ensorcellation.enchantment.FrostRebukeEnchantment;
import cofh.ensorcellation.enchantment.PhalanxEnchantment;
import cofh.ensorcellation.enchantment.override.ThornsEnchantmentImp;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.Utils.getItemEnchantmentLevel;
import static cofh.lib.util.constants.Constants.*;
import static cofh.lib.util.references.EnsorcIDs.ID_BULWARK;
import static cofh.lib.util.references.EnsorcIDs.ID_PHALANX;
import static cofh.lib.util.references.EnsorcReferences.*;
import static net.minecraft.enchantment.Enchantments.THORNS;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;

@Mod.EventBusSubscriber(modid = ID_ENSORCELLATION)
public class ShieldEnchEvents {

    private ShieldEnchEvents() {

    }

    @SubscribeEvent
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();
        ItemStack stack = entity.getActiveItemStack();

        if (canBlockDamageSource(entity, source) && attacker != null) {
            // THORNS
            int encThorns = getItemEnchantmentLevel(THORNS, stack);
            if (ThornsEnchantmentImp.shouldHit(encThorns, entity.getRNG())) {
                attacker.attackEntityFrom(DamageSource.causeThornsDamage(entity), ThornsEnchantment.getDamage(encThorns, entity.getRNG()));
            }
            // DISPLACEMENT
            int encDisplacement = getItemEnchantmentLevel(DISPLACEMENT, stack);
            if (DisplacementEnchantment.shouldHit(encDisplacement, entity.getRNG())) {
                DisplacementEnchantment.onHit(entity, attacker, encDisplacement);
            }
            // FIRE REBUKE
            int encFireRebuke = getItemEnchantmentLevel(FIRE_REBUKE, stack);
            if (FireRebukeEnchantment.shouldHit(encFireRebuke, entity.getRNG())) {
                FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
            }
            // FROST REBUKE
            int encFrostRebuke = getItemEnchantmentLevel(FROST_REBUKE, stack);
            if (FrostRebukeEnchantment.shouldHit(encFrostRebuke, entity.getRNG())) {
                FrostRebukeEnchantment.onHit(entity, attacker, encFrostRebuke);
            }
            // BULWARK
            int encBulwark = getItemEnchantmentLevel(BULWARK, stack);
            if (encBulwark > 0 && attacker instanceof PlayerEntity) {
                PlayerEntity playerAttacker = (PlayerEntity) attacker;
                if (playerAttacker.getRNG().nextFloat() < 0.5F) {
                    playerAttacker.getCooldownTracker().setCooldown(playerAttacker.getHeldItemMainhand().getItem(), 100);
                    attacker.world.setEntityState(attacker, (byte) 30);
                }
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        ItemStack stack = entity.getActiveItemStack();

        ModifiableAttributeInstance knockbackResAttr = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        ModifiableAttributeInstance moveSpeedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (knockbackResAttr != null) {
            knockbackResAttr.removeModifier(UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE);
        }
        if (moveSpeedAttr != null) {
            moveSpeedAttr.removeModifier(UUID_ENCH_PHALANX_MOVEMENT_SPEED);
        }
        if (stack.getItem().isShield(stack, entity)) {
            // BULWARK
            int encBulwark = getItemEnchantmentLevel(BULWARK, stack);
            if (knockbackResAttr != null) {
                if (encBulwark > 0) {
                    knockbackResAttr.applyNonPersistentModifier(new AttributeModifier(UUID_ENCH_BULWARK_KNOCKBACK_RESISTANCE, ID_BULWARK, 1.0D, ADDITION));
                }
            }
            // PHALANX
            int encPhalanx = getItemEnchantmentLevel(PHALANX, stack);
            if (moveSpeedAttr != null) {
                if (encPhalanx > 0) {
                    moveSpeedAttr.applyNonPersistentModifier(new AttributeModifier(UUID_ENCH_PHALANX_MOVEMENT_SPEED, ID_PHALANX, PhalanxEnchantment.SPEED * encPhalanx, MULTIPLY_TOTAL));
                }
            }
        }
    }

    // region HELPERS
    private static boolean canBlockDamageSource(LivingEntity living, DamageSource source) {

        Entity entity = source.getImmediateSource();
        if (entity instanceof AbstractArrowEntity) {
            AbstractArrowEntity arrow = (AbstractArrowEntity) entity;
            if (arrow.getPierceLevel() > 0) {
                return false;
            }
        }
        if (!source.isUnblockable() && living.isActiveItemStackBlocking()) {
            Vector3d vec3d2 = source.getDamageLocation();
            if (vec3d2 != null) {
                Vector3d vec3d = living.getLook(1.0F);
                Vector3d vec3d1 = vec3d2.subtractReverse(new Vector3d(living.getPosX(), living.getPosY(), living.getPosZ())).normalize();
                vec3d1 = new Vector3d(vec3d1.x, 0.0D, vec3d1.z);
                return vec3d1.dotProduct(vec3d) < 0.0D;
            }
        }
        return false;
    }
    // endregion
}
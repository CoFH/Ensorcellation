package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.DisplacementEnchantment;
import cofh.ensorcellation.enchantment.FireRebukeEnchantment;
import cofh.ensorcellation.enchantment.FrostRebukeEnchantment;
import cofh.ensorcellation.enchantment.override.FrostWalkerEnchantmentImp;
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

import static cofh.ensorcellation.init.EnsorcEnchantments.*;
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
        ItemStack armor = entity.getSlot(102).get();
        if (!armor.isEmpty()) {
            // FROST WALKER
            int encFrostWalker = getItemEnchantmentLevel(FROST_WALKER, armor);
            if (event.getSource().equals(DamageSource.HOT_FLOOR) && encFrostWalker > 0) {
                event.setCanceled(true);
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
        ItemStack armor = entity.getSlot(102).get();
        if (!armor.isEmpty()) {
            // FROST WALKER
            int encFrostWalker = getItemEnchantmentLevel(FROST_WALKER, armor);
            if (encFrostWalker > 0) {
                FrostWalkerEnchantment.onEntityMoved(entity, entity.level, entity.blockPosition(), encFrostWalker + 2);
                FrostWalkerEnchantmentImp.freezeNearby(entity, entity.level, entity.blockPosition(), encFrostWalker + 2);
            }
        }
    }

}
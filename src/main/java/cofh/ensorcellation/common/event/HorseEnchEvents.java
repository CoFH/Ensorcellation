package cofh.ensorcellation.common.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.Utils.getItemEnchantmentLevel;
import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;
import static net.minecraft.world.item.enchantment.Enchantments.FROST_WALKER;

@Mod.EventBusSubscriber (modid = ID_ENSORCELLATION)
public class HorseEnchEvents {

    private HorseEnchEvents() {

    }

    @SubscribeEvent
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

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
            if (event.getSource().equals(entity.damageSources().hotFloor()) && encFrostWalker > 0) {
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
            }
        }
    }

}
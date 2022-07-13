package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.SoulboundEnchantment;
import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

import static cofh.ensorcellation.init.EnsorcEnchantments.SOULBOUND;
import static cofh.lib.util.Utils.*;
import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;
import static net.minecraft.world.level.GameRules.RULE_KEEPINVENTORY;

@Mod.EventBusSubscriber (modid = ID_ENSORCELLATION)
public class SoulboundEvents {

    private SoulboundEvents() {

    }

    @SubscribeEvent (priority = EventPriority.HIGH)
    public static void handlePlayerDropsEvent(LivingDropsEvent event) {

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (Utils.isFakePlayer(player) || player.level.getGameRules().getBoolean(RULE_KEEPINVENTORY)) {
                return;
            }
            Iterator<ItemEntity> iter = event.getDrops().iterator();
            while (iter.hasNext()) {
                ItemStack stack = iter.next().getItem();
                if (getItemEnchantmentLevel(SOULBOUND.get(), stack) > 0) {
                    if (addToPlayerInventory(player, stack)) {
                        iter.remove();
                    }
                }
            }
        }
    }

    @SubscribeEvent (priority = EventPriority.HIGH)
    public static void handlePlayerCloneEvent(PlayerEvent.Clone event) {

        if (!event.isWasDeath()) {
            return;
        }
        Player player = event.getEntity();
        Player oldPlayer = event.getOriginal();
        if (Utils.isFakePlayer(player) || player.level.getGameRules().getBoolean(RULE_KEEPINVENTORY)) {
            return;
        }
        EnchantmentCoFH soulbound = SOULBOUND.get();

        for (int i = 0; i < oldPlayer.inventory.armor.size(); ++i) {
            ItemStack stack = oldPlayer.inventory.armor.get(i);
            int encSoulbound = getItemEnchantmentLevel(soulbound, stack);
            if (encSoulbound > 0) {
                if (SoulboundEnchantment.permanent) {
                    if (encSoulbound > 1) {
                        removeEnchantment(stack, soulbound);
                        addEnchantment(stack, soulbound, 1);
                    }
                } else if (player.level.random.nextInt(1 + encSoulbound) == 0) {
                    removeEnchantment(stack, soulbound);
                    if (encSoulbound > 1) {
                        addEnchantment(stack, soulbound, encSoulbound - 1);
                    }
                }
                if (addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.armor.set(i, ItemStack.EMPTY);
                }
            }
        }
        for (int i = 0; i < oldPlayer.inventory.items.size(); ++i) {
            ItemStack stack = oldPlayer.inventory.items.get(i);
            int encSoulbound = getItemEnchantmentLevel(soulbound, stack);
            if (encSoulbound > 0) {
                if (SoulboundEnchantment.permanent) {
                    if (encSoulbound > 1) {
                        removeEnchantment(stack, soulbound);
                        addEnchantment(stack, soulbound, 1);
                    }
                } else if (player.level.random.nextInt(1 + encSoulbound) == 0) {
                    removeEnchantment(stack, soulbound);
                    if (encSoulbound > 1) {
                        addEnchantment(stack, soulbound, encSoulbound - 1);
                    }
                }
                if (addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.items.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

}

package cofh.ensorcellation.common.event;

import cofh.ensorcellation.common.enchantment.SoulboundEnchantment;
import cofh.lib.common.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static cofh.core.util.references.EnsorcIDs.ID_SOULBOUND;
import static cofh.ensorcellation.init.registries.ModEnchantments.SOULBOUND;
import static cofh.lib.util.Utils.*;
import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;
import static net.minecraft.nbt.Tag.TAG_COMPOUND;
import static net.minecraft.world.level.GameRules.RULE_KEEPINVENTORY;

@Mod.EventBusSubscriber (modid = ID_ENSORCELLATION)
public class SoulboundEvents {

    private SoulboundEvents() {

    }

    @SubscribeEvent (priority = EventPriority.HIGH)
    public static void handlePlayerDropsEvent(LivingDropsEvent event) {

        if (event.getEntity() instanceof Player player) {
            if (Utils.isFakePlayer(player) || player.level.getGameRules().getBoolean(RULE_KEEPINVENTORY)) {
                return;
            }
            //            Iterator<ItemEntity> iter = event.getDrops().iterator();
            //            while (iter.hasNext()) {
            //                ItemStack stack = iter.next().getItem();
            //                if (getItemEnchantmentLevel(SOULBOUND.get(), stack) > 0) {
            //                    if (addToPlayerInventory(player, stack)) {
            //                        iter.remove();
            //                    }
            //                }
            //            }
            List<ItemStack> soulbound = new ArrayList<>();
            Iterator<ItemEntity> iter = event.getDrops().iterator();
            while (iter.hasNext()) {
                ItemStack stack = iter.next().getItem();
                if (getItemEnchantmentLevel(SOULBOUND.get(), stack) > 0) {
                    soulbound.add(stack);
                    iter.remove();
                }
            }
            if (!soulbound.isEmpty()) {
                ListTag list = new ListTag();
                for (ItemStack item : soulbound) {
                    if (!item.isEmpty()) {
                        list.add(item.save(new CompoundTag()));
                    }
                }
                CompoundTag playerData = player.getPersistentData();
                if (!playerData.contains(Player.PERSISTED_NBT_TAG)) {
                    playerData.put(Player.PERSISTED_NBT_TAG, new CompoundTag());
                }
                CompoundTag persistedTag = playerData.getCompound(Player.PERSISTED_NBT_TAG);
                persistedTag.put(ID_ENSORCELLATION + ":" + ID_SOULBOUND, list);
            }
        }
    }

    @SubscribeEvent (priority = EventPriority.HIGH)
    public static void handlePlayerCloneEvent(PlayerEvent.Clone event) {

        if (!event.isWasDeath()) {
            return;
        }
        Player player = event.getEntity();
        if (Utils.isFakePlayer(player) || player.level.getGameRules().getBoolean(RULE_KEEPINVENTORY)) {
            return;
        }
        EnchantmentCoFH soulbound = SOULBOUND.get();

        CompoundTag playerData = player.getPersistentData();
        if (playerData.contains(Player.PERSISTED_NBT_TAG)) {
            CompoundTag persistedTag = playerData.getCompound(Player.PERSISTED_NBT_TAG);

            ListTag list = persistedTag.getList(ID_ENSORCELLATION + ":" + ID_SOULBOUND, TAG_COMPOUND);
            for (int i = 0; i < list.size(); ++i) {
                ItemStack stack = ItemStack.of(list.getCompound(i));
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
                    addToPlayerInventory(player, stack);
                }
            }
        }
        //        for (int i = 0; i < oldPlayer.inventory.armor.size(); ++i) {
        //            ItemStack stack = oldPlayer.inventory.armor.get(i);
        //            int encSoulbound = getItemEnchantmentLevel(soulbound, stack);
        //            if (encSoulbound > 0) {
        //                if (SoulboundEnchantment.permanent) {
        //                    if (encSoulbound > 1) {
        //                        removeEnchantment(stack, soulbound);
        //                        addEnchantment(stack, soulbound, 1);
        //                    }
        //                } else if (player.level.random.nextInt(1 + encSoulbound) == 0) {
        //                    removeEnchantment(stack, soulbound);
        //                    if (encSoulbound > 1) {
        //                        addEnchantment(stack, soulbound, encSoulbound - 1);
        //                    }
        //                }
        //                if (addToPlayerInventory(player, stack)) {
        //                    oldPlayer.inventory.armor.set(i, ItemStack.EMPTY);
        //                }
        //            }
        //        }
        //        for (int i = 0; i < oldPlayer.inventory.items.size(); ++i) {
        //            ItemStack stack = oldPlayer.inventory.items.get(i);
        //            int encSoulbound = getItemEnchantmentLevel(soulbound, stack);
        //            if (encSoulbound > 0) {
        //                if (SoulboundEnchantment.permanent) {
        //                    if (encSoulbound > 1) {
        //                        removeEnchantment(stack, soulbound);
        //                        addEnchantment(stack, soulbound, 1);
        //                    }
        //                } else if (player.level.random.nextInt(1 + encSoulbound) == 0) {
        //                    removeEnchantment(stack, soulbound);
        //                    if (encSoulbound > 1) {
        //                        addEnchantment(stack, soulbound, encSoulbound - 1);
        //                    }
        //                }
        //                if (addToPlayerInventory(player, stack)) {
        //                    oldPlayer.inventory.items.set(i, ItemStack.EMPTY);
        //                }
        //            }
        //        }
    }

}

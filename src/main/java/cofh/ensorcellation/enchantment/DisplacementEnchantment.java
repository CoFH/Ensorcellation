package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

import static cofh.core.util.references.EnsorcReferences.*;
import static cofh.lib.util.Constants.ARMOR_SLOTS;

public class DisplacementEnchantment extends EnchantmentCoFH {

    public static int chance = 20;
    public static boolean mobsAffectPlayers = false;

    public DisplacementEnchantment() {

        super(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, ARMOR_SLOTS);
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 5 + 10 * (level - 1);
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

        return super.checkCompatibility(ench) && ench != Enchantments.THORNS && ench != FIRE_REBUKE && ench != FROST_REBUKE;
    }

    // region HELPERS
    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        Map.Entry<EquipmentSlot, ItemStack> stack = EnchantmentHelper.getRandomItemWith(DISPLACEMENT, user);
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
            Random rand = user.getRandom();
            int radius = 16 * level;
            int bound = radius * 2 + 1;
            BlockPos pos = new BlockPos(attacker.getX(), attacker.getY(), attacker.getZ());
            BlockPos randPos = pos.offset(-radius + rand.nextInt(bound), rand.nextInt(8), -radius + rand.nextInt(bound));
            if (attacker.level instanceof ServerLevel && attacker.canChangeDimensions() && Utils.teleportEntityTo(attacker, randPos)) {
                for (int j = 0; j < 3 * level; ++j) {
                    Utils.spawnParticles(attacker.level, ParticleTypes.PORTAL, attacker.getX() + rand.nextDouble(), attacker.getY() + 1.0D + rand.nextDouble(), attacker.getZ() + rand.nextDouble(), 1, 0, 0, 0, 0);
                    Utils.spawnParticles(attacker.level, ParticleTypes.PORTAL, randPos.getX() + rand.nextDouble(), randPos.getY() + 1.0D + rand.nextDouble(), randPos.getZ() + rand.nextDouble(), 1, 0, 0, 0, 0);
                }
            }
        }
    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}

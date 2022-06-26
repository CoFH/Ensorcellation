package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Tuple;
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
import java.util.Set;

import static cofh.core.util.references.EnsorcIDs.ID_DISPLACEMENT;
import static cofh.core.util.references.EnsorcIDs.ID_FROST_REBUKE;
import static cofh.ensorcellation.Ensorcellation.ENCHANTMENTS;
import static cofh.lib.util.Constants.ARMOR_SLOTS;

public class FireRebukeEnchantment extends EnchantmentCoFH {

    private static final Set<Tuple<Entity, Integer>> AFFLICTED_ENTITIES = new ObjectOpenHashSet<>();

    public static int chance = 20;
    public static boolean mobsAffectPlayers = false;

    public FireRebukeEnchantment() {

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

        return super.checkCompatibility(ench) && ench != Enchantments.THORNS && ench != ENCHANTMENTS.get(ID_DISPLACEMENT) && ench != ENCHANTMENTS.get(ID_FROST_REBUKE);
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
        RandomSource rand = user.getRandom();
        AFFLICTED_ENTITIES.add(new Tuple<>(attacker, 1 + rand.nextInt(3 * level)));
        if (attacker.level instanceof ServerLevel) {
            for (int j = 0; j < 3 * level; ++j) {
                Utils.spawnParticles(attacker.level, ParticleTypes.FLAME, attacker.getX() + rand.nextDouble(), attacker.getY() + 1.0D + rand.nextDouble(), attacker.getZ() + rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }

    public static boolean shouldHit(int level, RandomSource rand) {

        return rand.nextInt(100) < chance * level;
    }

    public static void setFireToMobs() {

        if (AFFLICTED_ENTITIES.isEmpty()) {
            return;
        }
        for (Tuple<Entity, Integer> entry : AFFLICTED_ENTITIES) {
            entry.getA().setSecondsOnFire(entry.getB());
        }
        AFFLICTED_ENTITIES.clear();
    }
    // endregion
}

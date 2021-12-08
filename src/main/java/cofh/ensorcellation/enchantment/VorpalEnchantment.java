package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;

public class VorpalEnchantment extends EnchantmentCoFH {

    public static int critBase = 5;
    public static int critLevel = 5;
    public static int critDamage = 5;
    public static int headBase = 10;
    public static int headLevel = 10;

    public VorpalEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinCost(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinCost(level) + 50;
    }

    // region HELPERS
    public static void onHit(LivingEntity entity, int level) {

        entity.level.playSound(null, entity.blockPosition(), SoundEvents.PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (entity.level instanceof ServerWorld) {
            for (int i = 0; i < 2 * level; ++i) {
                Utils.spawnParticles(entity.level, ParticleTypes.CRIT, entity.getX() + entity.level.random.nextDouble(), entity.getY() + 1.0D + entity.level.random.nextDouble(), entity.getZ() + entity.level.random.nextDouble(), 1, 0, 0, 0, 0);
            }
            Utils.spawnParticles(entity.level, ParticleTypes.SWEEP_ATTACK, entity.getX() + entity.level.random.nextDouble(), entity.getY() + 1.0D + entity.level.random.nextDouble(), entity.getZ() + entity.level.random.nextDouble(), 1, 0, 0, 0, 0);
        }
    }
    // endregion

}

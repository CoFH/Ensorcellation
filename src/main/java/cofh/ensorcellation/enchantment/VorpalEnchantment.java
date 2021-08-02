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
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    // region HELPERS
    public static void onHit(LivingEntity entity, int level) {

        entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        if (entity.world instanceof ServerWorld) {
            for (int i = 0; i < 2 * level; ++i) {
                Utils.spawnParticles(entity.world, ParticleTypes.CRIT, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            }
            Utils.spawnParticles(entity.world, ParticleTypes.SWEEP_ATTACK, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
        }
    }
    // endregion

}

package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import static cofh.core.init.CoreMobEffects.CHILLED;
import static cofh.core.init.CoreParticles.FROST;

public class FrostAspectEnchantment extends EnchantmentCoFH {

    public FrostAspectEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinCost(int level) {

        return 10 + 20 * (level - 1);
    }

    @Override
    protected int maxDelegate(int level) {

        return super.getMinCost(level) + 50;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != Enchantments.FIRE_ASPECT;
    }

    // region HELPERS
    public static boolean validTarget(Entity entity) {

        return entity instanceof Blaze || entity instanceof MagmaCube;
    }

    public static float getExtraDamage(int level) {

        return level * 2.5F;
    }

    public static void onHit(LivingEntity entity, int level) {

        int i = 80 * level;
        if (entity.isOnFire()) {
            entity.clearFire();
        }
        entity.addEffect(new MobEffectInstance(CHILLED.get(), i, level - 1, false, false));
        if (entity.level instanceof ServerLevel) {
            for (int j = 0; j < 4 * level; ++j) {
                Utils.spawnParticles(entity.level, (SimpleParticleType) FROST.get(), entity.getX() + entity.level.random.nextDouble(), entity.getY() + 1.0D + entity.level.random.nextDouble(), entity.getZ() + entity.level.random.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}

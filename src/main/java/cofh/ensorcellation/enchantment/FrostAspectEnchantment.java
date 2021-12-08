package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.server.ServerWorld;

import static cofh.lib.util.references.CoreReferences.CHILLED;

public class FrostAspectEnchantment extends EnchantmentCoFH {

    public FrostAspectEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
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

        return entity instanceof BlazeEntity || entity instanceof MagmaCubeEntity;
    }

    public static float getExtraDamage(int level) {

        return level * 2.5F;
    }

    public static void onHit(LivingEntity entity, int level) {

        int i = 80 * level;
        if (entity.isOnFire()) {
            entity.clearFire();
        }
        entity.addEffect(new EffectInstance(CHILLED, i, level - 1, false, false));
        if (entity.level instanceof ServerWorld) {
            for (int j = 0; j < 4 * level; ++j) {
                Utils.spawnParticles(entity.level, ParticleTypes.ITEM_SNOWBALL, entity.getX() + entity.level.random.nextDouble(), entity.getY() + 1.0D + entity.level.random.nextDouble(), entity.getZ() + entity.level.random.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}

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
    public int getMinEnchantability(int level) {

        return 10 + 20 * (level - 1);
    }

    @Override
    protected int maxDelegate(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.FIRE_ASPECT;
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
        if (entity.isBurning()) {
            entity.extinguish();
        }
        entity.addPotionEffect(new EffectInstance(CHILLED, i, level - 1, false, false));
        if (entity.world instanceof ServerWorld) {
            for (int j = 0; j < 4 * level; ++j) {
                Utils.spawnParticles(entity.world, ParticleTypes.ITEM_SNOWBALL, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}

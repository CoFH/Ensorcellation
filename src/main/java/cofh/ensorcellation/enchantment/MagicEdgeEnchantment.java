package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.DamageEnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

public class MagicEdgeEnchantment extends DamageEnchantmentCoFH {

    public MagicEdgeEnchantment() {

        super(Rarity.RARE, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
        treasureEnchantment = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.SWEEPING;
    }

    // region HELPERS
    public static float getExtraDamage(int level) {

        return level * 0.5F;
    }

    public static void onHit(LivingEntity entity, int level) {

        if (entity.world instanceof ServerWorld) {
            for (int i = 0; i < 2 * level; ++i) {
                Utils.spawnParticles(entity.world, ParticleTypes.ENCHANT, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
                Utils.spawnParticles(entity.world, ParticleTypes.ENCHANTED_HIT, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}

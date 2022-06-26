package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Shulker;

import static cofh.core.init.CoreMobEffects.ENDERFERENCE;

public class DamageEnderEnchantment extends DamageEnchantmentCoFH {

    public DamageEnderEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 5;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {

        if (target instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) target;
            int i = 100 + user.getRandom().nextInt(40 * level);
            living.addEffect(new MobEffectInstance(ENDERFERENCE.get(), i, 0, false, false));
        }
    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof EnderMan || entity instanceof Endermite || entity instanceof EnderDragon || entity instanceof Shulker;
    }

}

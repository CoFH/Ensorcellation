package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class DamageIllagerEnchantment extends DamageEnchantmentCoFH {

    public DamageIllagerEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.SWORD_OR_AXE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 5;
    }

    // TODO: Revisit if Ravagers and Witches are reclassified in future.
    //    @Override
    //    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
    //
    //        return creatureType == CreatureAttribute.ILLAGER ? getExtraDamage(level) : 0.0F;
    //    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof AbstractRaiderEntity;
    }

}

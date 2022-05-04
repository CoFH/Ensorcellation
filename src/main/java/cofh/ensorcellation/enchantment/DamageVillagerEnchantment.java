package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;

public class DamageVillagerEnchantment extends DamageEnchantmentCoFH {

    public static boolean enableEmeraldDrops = true;

    public DamageVillagerEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.SWORD_OR_AXE_OR_CROSSBOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        maxLevel = 5;
    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof AbstractVillager || entity instanceof IronGolem;
    }

}

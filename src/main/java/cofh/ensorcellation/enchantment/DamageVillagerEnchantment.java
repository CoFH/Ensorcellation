package cofh.ensorcellation.enchantment;

import cofh.core.init.CoreEnchantments;
import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class DamageVillagerEnchantment extends DamageEnchantmentCoFH {

    public static boolean enableEmeraldDrops = true;

    public DamageVillagerEnchantment() {

        super(Rarity.UNCOMMON, CoreEnchantments.Types.SWORD_OR_AXE_OR_CROSSBOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 5;
    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof AbstractVillagerEntity || entity instanceof IronGolemEntity;
    }

}

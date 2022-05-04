//package cofh.ensorcellation.enchantment.nyi;
//
//import cofh.lib.enchantment.EnchantmentCoFH;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.EnchantmentCategory;
//import net.minecraft.enchantment.Enchantments;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraftforge.common.ToolType;
//
//public class ProspectingEnchantment extends EnchantmentCoFH {
//
//    public ProspectingEnchantment() {
//
//        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
//    }
//
//    @Override
//    public int getMinCost(int level) {
//
//        return 15;
//    }
//
//    @Override
//    protected int maxDelegate(int level) {
//
//        return getMinCost(level) + 50;
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack) {
//
//        Item item = stack.getItem();
//        return enable && (item.getToolTypes(stack).contains(ToolType.PICKAXE) || supportsEnchantment(stack));
//    }
//
//    @Override
//    public boolean checkCompatibility(Enchantment ench) {
//
//        return super.checkCompatibility(ench) && ench != Enchantments.SILK_TOUCH;
//    }
//
//}

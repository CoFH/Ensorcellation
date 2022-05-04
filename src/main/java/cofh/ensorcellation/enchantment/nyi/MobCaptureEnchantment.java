//package cofh.ensorcellation.enchantment.nyi;
//
//import cofh.lib.enchantment.EnchantmentCoFH;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.item.FishingRodItem;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.item.enchantment.EnchantmentCategory;
//
//public class MobCaptureEnchantment extends EnchantmentCoFH {
//
//    public static int captureBase = 10;
//    public static int captureLevel = 5;
//
//    public MobCaptureEnchantment() {
//
//        super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.FISHING_ROD, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
//        maxLevel = 2;
//        treasureEnchantment = true;
//    }
//
//    @Override
//    public int getMinCost(int level) {
//
//        return 10 + (level - 1) * 9;
//    }
//
//    @Override
//    protected int maxDelegate(int level) {
//
//        return getMinCost(level) + 15;
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack) {
//
//        Item item = stack.getItem();
//        return enable && (item instanceof FishingRodItem || supportsEnchantment(stack));
//    }
//
//}

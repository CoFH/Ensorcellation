package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import static cofh.core.util.references.CoreReferences.GLOSSED_MAGMA;
import static cofh.lib.util.constants.ModIds.ID_ENSORCELLATION;

public class FrostWalkerEnchantmentImp extends EnchantmentOverride {

    private static boolean freezeLava = true;

    public FrostWalkerEnchantmentImp() {

        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
        maxLevel = 2;
        treasureEnchantment = true;
    }

    @Override
    public int getMinCost(int level) {

        return level * 10;
    }

    @Override
    public int getMaxCost(int level) {

        return getMinCost(level) + 15;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
        }
        return super.canApplyAtEnchantingTable(stack) || stack.getItem() instanceof HorseArmorItem;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {

        return super.checkCompatibility(ench) && ench != Enchantments.DEPTH_STRIDER;
    }

    // region HELPERS
    public static void freezeNearby(LivingEntity living, Level worldIn, BlockPos pos, int level) {

        if (!freezeLava) {
            return;
        }
        if (living.isOnGround() && GLOSSED_MAGMA != null) {
            BlockState state = GLOSSED_MAGMA.defaultBlockState();
            float f = (float) Math.min(16, 2 + level);
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

            for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-f, -1.0D, -f), pos.offset(f, -1.0D, f))) {
                if (blockpos.closerToCenterThan(living.position(), f)) {
                    mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = worldIn.getBlockState(mutable);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = worldIn.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && state.canSurvive(worldIn, blockpos) && worldIn.isUnobstructed(state, blockpos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(living, BlockSnapshot.create(worldIn.dimension(), worldIn, blockpos), Direction.UP)) {
                            worldIn.setBlockAndUpdate(blockpos, state);
                            worldIn.scheduleTick(blockpos, GLOSSED_MAGMA, MathHelper.nextInt(living.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }
    }

    public void setFreezeLava(boolean freezeLava) {

        FrostWalkerEnchantmentImp.freezeLava = freezeLava;
        descriptionId = "enchantment." + (freezeLava ? ID_ENSORCELLATION + ".frost_walker" : "minecraft.frost_walker");
    }
    // endregion
}
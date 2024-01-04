package cofh.ensorcellation.mixin;

import cofh.ensorcellation.common.config.OverrideEnchantmentConfig;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cofh.core.init.CoreBlocks.GLOSSED_MAGMA;

@Mixin (FrostWalkerEnchantment.class)
public abstract class FrostWalkerEnchantmentMixin extends Enchantment {

    public FrostWalkerEnchantmentMixin(Enchantment.Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {

        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!OverrideEnchantmentConfig.improvedFrostWalker) {
            return super.canApplyAtEnchantingTable(stack);
        }
        Item item = stack.getItem();
        return super.canApplyAtEnchantingTable(stack) || item instanceof HorseArmorItem;
    }

    @Inject (
            method = "onEntityMoved(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;I)V",
            at = @At ("TAIL")
    )
    private static void freezeNearby(LivingEntity pLiving, Level pLevel, BlockPos pPos, int pLevelConflicting, CallbackInfo ci) {

        if (!OverrideEnchantmentConfig.lavaFrostWalker) {
            return;
        }
        if (pLiving.onGround()) {
            BlockState state = GLOSSED_MAGMA.get().defaultBlockState();
            int i = Math.min(16, 2 + pLevelConflicting);
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

            for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-i, -1, -i), pPos.offset(i, -1, i))) {
                if (blockpos.closerToCenterThan(pLiving.position(), i)) {
                    mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = pLevel.getBlockState(mutable);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = pLevel.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0;
                        if (isFull && state.canSurvive(pLevel, blockpos) && pLevel.isUnobstructed(state, blockpos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(pLiving, BlockSnapshot.create(pLevel.dimension(), pLevel, blockpos), Direction.UP)) {
                            pLevel.setBlockAndUpdate(blockpos, state);
                            pLevel.scheduleTick(blockpos, GLOSSED_MAGMA.get(), MathHelper.nextInt(pLiving.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }
    }

}

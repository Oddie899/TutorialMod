package me.oddie.fuckayou.blocks.pen15;

import me.oddie.fuckayou.blocks.pen15.entity.pen15BlockEntity;
import me.oddie.fuckayou.init.ParticleInit;
import me.oddie.fuckayou.manasystem.IManaHandler;
import me.oddie.fuckayou.manasystem.ManaStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class Pen15 extends Block implements EntityBlock {

    public Pen15(Properties properties) {
        super(properties);
    }

    public LazyOptional<IManaHandler> cachedHandler;
    public void cumMethod(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult){
        for (int i = 0; i <= 10 * Math.random(); i++) { //This for loop is used to generate a random number of particles at once
            level.addParticle(ParticleInit.Mayo_Particles.get(),
                    blockPos.getX() + 0.5, blockPos.getY() + 2.1, blockPos.getZ() + 0.5, //This controls the position the particle spawns in relation to the block
                    Math.random() - 0.5, 1, Math.random() - 0.5);//While this controls the speed of the particle
        }
    }
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
    {
        if(cachedHandler == null){
            ICapabilityProvider provider = level.getBlockEntity(blockPos);
            cachedHandler = provider.getCapability(ManaStorage.MANA_CAPABILITY);
        }
        cachedHandler.ifPresent((manaHandler) -> {
            if(manaHandler.remove(1)){
                cumMethod(blockState, level, blockPos,player, interactionHand,blockHitResult);
            }
            else{

            }
        });
        return InteractionResult.SUCCESS;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new pen15BlockEntity(pPos, pState);
    }
}

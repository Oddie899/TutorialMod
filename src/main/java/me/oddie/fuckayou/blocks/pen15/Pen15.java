package me.oddie.fuckayou.blocks.pen15;

import me.oddie.fuckayou.blocks.pen15.entity.pen15BlockEntity;
import me.oddie.fuckayou.init.ParticleInit;
import me.oddie.fuckayou.manasystem.IManaHandler;
import me.oddie.fuckayou.manasystem.ManaStorage;
import me.oddie.fuckayou.network.CumPacket;
import me.oddie.fuckayou.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.mojang.patchy.BlockedServers;

public class Pen15 extends Block implements EntityBlock {

    public Pen15(Properties properties) {
        super(properties);
    }

    
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
    {
		BlockEntity blockEntity = level.getBlockEntity(blockPos);
		ICapabilityProvider provider = (ICapabilityProvider) blockEntity;
		var cap = provider.getCapability(ManaStorage.MANA_CAPABILITY);

		if(!level.isClientSide){
			
			cap.ifPresent((manaHandler) -> {
				if(manaHandler.remove(1)){
					var target = PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(blockPos));
					PacketHandler.CHANNEL.send(target, new CumPacket(blockPos));
					
					player.getItemInHand(interactionHand).getCapability(ManaStorage.MANA_CAPABILITY).ifPresent(
						(wandCapability) -> {
							wandCapability.add(1);
						}
					);
				}				
			});
		} 
        
        return InteractionResult.SUCCESS;
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new pen15BlockEntity(pPos, pState);
    }
}

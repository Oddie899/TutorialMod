package me.oddie.fuckayou.network;

import java.util.function.Supplier;

import me.oddie.fuckayou.init.ParticleInit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

class CumPacketHandler {
	public static void cumMethod(Level level, BlockPos blockPos){
        for (int i = 0; i <= 10 * Math.random(); i++) { //This for loop is used to generate a random number of particles at once
            level.addParticle(ParticleInit.Mayo_Particles.get(),
                    blockPos.getX() + 0.5, blockPos.getY() + 2.1, blockPos.getZ() + 0.5, //This controls the position the particle spawns in relation to the block
                    Math.random() - 0.5, 1, Math.random() - 0.5);//While this controls the speed of the particle
        }
    }
}

public class CumPacket {
	BlockPos blockPos;

	public CumPacket(BlockPos blockPos){
		this.blockPos = blockPos;
	}

	public void writeTo(FriendlyByteBuf buf){
		buf.writeBlockPos(blockPos);
	}

	public static CumPacket readFrom(FriendlyByteBuf buf){
		var packet = new CumPacket(buf.readBlockPos());

		return packet;
	}

	

	public void handle(Supplier<NetworkEvent.Context> ctx){
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CumPacketHandler.cumMethod(Minecraft.getInstance().level, blockPos));
		
	}
}

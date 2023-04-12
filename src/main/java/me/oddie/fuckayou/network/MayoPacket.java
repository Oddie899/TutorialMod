// package me.oddie.fuckayou.network;

// import java.util.function.Supplier;

// import net.minecraft.network.FriendlyByteBuf;
// import net.minecraft.world.item.ItemStack;
// import net.minecraftforge.api.distmarker.Dist;
// import net.minecraftforge.fml.DistExecutor;
// import net.minecraftforge.network.NetworkEvent;

// public class MayoPacket {
// 	public ItemStack stack;

// 	public MayoPacket(ItemStack stack){
// 		this.stack = stack;
// 	}

// 	public void writeTo(FriendlyByteBuf buf){
// 		buf.writeItem(this.stack);
// 	}

// 	public static MayoPacket readFrom(FriendlyByteBuf buf){
// 		var packet = new MayoPacket(buf.readItem());
// 		return packet;
// 	}

// 	public void handle(Supplier<NetworkEvent.Context> ctx){
// 		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			
// 		});
// 	}
// }

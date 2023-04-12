package me.oddie.fuckayou;

import com.mojang.logging.LogUtils;
import me.oddie.fuckayou.entities.mayoprojectile.MayoProjectileRenderer;
import me.oddie.fuckayou.init.BlockInit;
import me.oddie.fuckayou.init.EntityInit;
import me.oddie.fuckayou.init.ItemInit;
import me.oddie.fuckayou.init.ParticleInit;
import me.oddie.fuckayou.items.MayoWand;
import me.oddie.fuckayou.manasystem.ManaStorage;
import me.oddie.fuckayou.network.CumPacket;
// import me.oddie.fuckayou.network.MayoPacket;
import me.oddie.fuckayou.network.PacketHandler;
import me.oddie.fuckayou.particles.MayoParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@Mod(FuckaYou.MODID)
public class FuckaYou {
    public static final String MODID = "fuckayou";
    public static final Logger LOGGER = LogUtils.getLogger();




    public FuckaYou() // Digestives are top tier
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ParticleInit.register(modEventBus);
        EntityInit.ENTITY_TYPES.register(modEventBus);
        BlockInit.BLOCK_ENTITIES.register(modEventBus);

		

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }
    public static final CreativeModeTab FuckYou = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return ItemInit.EXAMPLE_BLOCK_ITEM.get().getDefaultInstance();
        }
    };


	@SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

		int id = 0;
		PacketHandler.CHANNEL.messageBuilder(CumPacket.class, id++)
			.encoder(CumPacket::writeTo)
			.decoder(CumPacket::readFrom)
			.consumerMainThread(CumPacket::handle)
			.add();

		// PacketHandler.CHANNEL.messageBuilder(MayoPacket.class, id++)
		// 	.encoder(MayoPacket::writeTo)
		// 	.decoder(MayoPacket::readFrom)
		// 	.consumerMainThread(MayoPacket);
		
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event){
       if(event.getObject().getItem() instanceof MayoWand){

		var opt = LazyOptional.of(() -> new ManaStorage());
		event.addCapability(new ResourceLocation(MODID, "mana"), new ICapabilityProvider() {

			

			@Override
			public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
				return opt.cast();
			}
			
		});
	   }
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            EntityRenderers.register(EntityInit.MAYO_PROJECTILE.get(), MayoProjectileRenderer::new);
        }
        @SubscribeEvent
        public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
            event.register(ParticleInit.Mayo_Particles.get(), MayoParticle.MayoProvider::new);
        }
    }
}

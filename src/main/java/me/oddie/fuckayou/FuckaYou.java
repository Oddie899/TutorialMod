package me.oddie.fuckayou;

import com.mojang.logging.LogUtils;
import me.oddie.fuckayou.entities.mayoprojectile.MayoProjectileRenderer;
import me.oddie.fuckayou.init.BlockInit;
import me.oddie.fuckayou.init.EntityInit;
import me.oddie.fuckayou.init.ItemInit;
import me.oddie.fuckayou.init.ParticleInit;
import me.oddie.fuckayou.particles.MayoParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
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

public class blocks {}
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<BlockEntity> event){
//        if(event.getObject() instanceof pen15BlockEntity){
//            event.addCapability(new ResourceLocation(MODID, "mana_capabilty"), );
//        }
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

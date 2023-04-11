package me.oddie.fuckayou.init;

import me.oddie.fuckayou.FuckaYou;
import me.oddie.fuckayou.datagen.FuckaYouBlockProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FuckaYou.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProviderInit {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(
                true,
                new FuckaYouBlockProvider(event.getGenerator(), FuckaYou.MODID, event.getExistingFileHelper())

        );
        System.out.println("Gathering fucks!");
    }

}

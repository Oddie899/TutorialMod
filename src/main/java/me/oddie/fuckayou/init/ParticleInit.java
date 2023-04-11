package me.oddie.fuckayou.init;

import me.oddie.fuckayou.FuckaYou;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleInit {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, FuckaYou.MODID);

    public static final RegistryObject<SimpleParticleType> Mayo_Particles =
            PARTICLE_TYPES.register("mayo_particles", () -> new SimpleParticleType(true));


    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}

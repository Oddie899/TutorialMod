package me.oddie.fuckayou.init;

import me.oddie.fuckayou.FuckaYou;
import me.oddie.fuckayou.entities.mayoprojectile.MayoProjectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FuckaYou.MODID);

    public static final RegistryObject<EntityType<MayoProjectile>> MAYO_PROJECTILE = ENTITY_TYPES.register("mayo_projectile",
            () -> EntityType.Builder.of((EntityType.EntityFactory<MayoProjectile>) MayoProjectile::new, MobCategory.MISC).build("mayo_projectile"));
}

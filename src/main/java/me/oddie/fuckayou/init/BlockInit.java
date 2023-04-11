package me.oddie.fuckayou.init;

import me.oddie.fuckayou.FuckaYou;
import me.oddie.fuckayou.blocks.pen15.Pen15;
import me.oddie.fuckayou.blocks.pen15.entity.pen15BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FuckaYou.MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Pen15(BlockBehaviour.Properties.of(Material.STONE).strength(1.6f,
                    6.5f).requiresCorrectToolForDrops().noOcclusion().dynamicShape()));

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FuckaYou.MODID);


    public static final RegistryObject<BlockEntityType<?>> pen15BE = BLOCK_ENTITIES.register("pen15be", ()->BlockEntityType.Builder.of((BlockEntityType.BlockEntitySupplier) pen15BlockEntity::new, EXAMPLE_BLOCK.get()).build(null));
}



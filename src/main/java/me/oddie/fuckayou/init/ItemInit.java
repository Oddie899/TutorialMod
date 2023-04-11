package me.oddie.fuckayou.init;

import me.oddie.fuckayou.FuckaYou;
import me.oddie.fuckayou.items.MayoWand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Properties;

import static me.oddie.fuckayou.FuckaYou.FuckYou;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FuckaYou.MODID);

    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
            () -> new BlockItem(BlockInit.EXAMPLE_BLOCK.get(),
                    new Item.Properties().tab(FuckYou)));

    public static final RegistryObject<Item> MAYO_WAND = ITEMS.register("mayo_wand", () -> new MayoWand(new Item.Properties().tab(FuckYou).stacksTo(1)));
}

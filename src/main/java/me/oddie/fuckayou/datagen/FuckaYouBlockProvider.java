package me.oddie.fuckayou.datagen;

import me.oddie.fuckayou.FuckaYou;
import me.oddie.fuckayou.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FuckaYouBlockProvider extends BlockStateProvider {

    public FuckaYouBlockProvider(final DataGenerator gen, String modid, final ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockInit.EXAMPLE_BLOCK.get(),
                models().getExistingFile(new ResourceLocation(FuckaYou.MODID, "example_block")));
    }

}

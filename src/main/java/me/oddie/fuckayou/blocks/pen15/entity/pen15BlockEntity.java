package me.oddie.fuckayou.blocks.pen15.entity;

import me.oddie.fuckayou.init.BlockInit;
import me.oddie.fuckayou.manasystem.IManaHandler;
import me.oddie.fuckayou.manasystem.ManaStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class pen15BlockEntity extends BlockEntity {


    public pen15BlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockInit.pen15BE.get(), blockPos, blockState);
    }
    private final LazyOptional<IManaHandler> manaHandler = LazyOptional.of(() -> new ManaStorage(){
        @Override
        public void onChanged(int mana) {
            super.onChanged(mana);
            setChanged();
        }
    });

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if(cap == ManaStorage.MANA_CAPABILITY){
			return manaHandler.cast();
		}
		return super.getCapability(cap, side);
	}
}

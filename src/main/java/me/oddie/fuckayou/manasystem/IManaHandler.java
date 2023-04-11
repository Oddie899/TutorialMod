package me.oddie.fuckayou.manasystem;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface IManaHandler extends INBTSerializable<CompoundTag> {
    boolean add(int amount);
    boolean remove(int amount);
    int get();
}

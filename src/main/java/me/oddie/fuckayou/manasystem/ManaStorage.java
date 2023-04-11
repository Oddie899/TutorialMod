package me.oddie.fuckayou.manasystem;

import me.oddie.fuckayou.FuckaYou;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.Map;

public class ManaStorage implements IManaHandler{
    int mana = 10;
    int maxMana = 10;
    final String key = ("Mana");
    public static Capability<IManaHandler> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    @Override
    public boolean add(int amount) {
        if(amount + mana <= maxMana) {
            mana += amount;
            onChanged(mana);
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public boolean remove(int amount) {
        if(mana - amount >= 0) {
            mana -= amount;
            onChanged(mana);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int get() {
        return mana;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag fuckinTag = new CompoundTag();
        fuckinTag.putInt(key, this.mana);
        return fuckinTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.mana = nbt.getInt(key);
    }
    public void onChanged(int mana) {

    }
    public ManaStorage(){
        FuckaYou.LOGGER.info("Look we made the capability");
    }

}

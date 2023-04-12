package me.oddie.fuckayou.items;

import me.oddie.fuckayou.entities.mayoprojectile.MayoProjectile;
import me.oddie.fuckayou.manasystem.ManaStorage;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MayoWand extends Item {
    public MayoWand(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if(!pLevel.isClientSide){
			pPlayer.getItemInHand(pUsedHand).getCapability(ManaStorage.MANA_CAPABILITY).ifPresent(
				(cap) -> {
					if(cap.remove(1)){
						MayoProjectile mayoFIRE = new MayoProjectile(pLevel);
						mayoFIRE.setPos(pPlayer.getEyePosition().add(pPlayer.getForward()));
						mayoFIRE.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f,
								1.5f, 1);
						pLevel.addFreshEntity(mayoFIRE);
					}
				}
			);
			
		}

        
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand)); //Minecraft fucks
    }
}

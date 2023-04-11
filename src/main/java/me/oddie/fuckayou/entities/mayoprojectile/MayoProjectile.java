package me.oddie.fuckayou.entities.mayoprojectile;


import me.oddie.fuckayou.init.EntityInit;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;

public class MayoProjectile extends AbstractHurtingProjectile implements ItemSupplier {
    public MayoProjectile(Level pLevel) {
        super(EntityInit.MAYO_PROJECTILE.get(), pLevel);
    }

    public MayoProjectile(EntityType pEntityType, Level pLevel) {
        super(pEntityType,pLevel);

    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount >= 60) {
            this.discard();
        }
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity hitEntity = pResult.getEntity();
        if (hitEntity instanceof LivingEntity) {
            LivingEntity hitLivingEntity = (LivingEntity) hitEntity;
            hitLivingEntity.addEffect(new MobEffectInstance(MobEffect.byId(2), 200));
        }


    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        this.discard();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.SLIME_BALL);
    }
}


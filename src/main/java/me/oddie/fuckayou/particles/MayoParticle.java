package me.oddie.fuckayou.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;


public class MayoParticle extends TextureSheetParticle {


    protected MayoParticle(ClientLevel Level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet, double xd, double yd, double zd) {
        super(Level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.friction = 0.8f;
        this.gravity = 1f;
        this.xd = xd;
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 0.8f;
        this.lifetime = 3000;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;

    }
    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class MayoProvider implements ParticleProvider<SimpleParticleType> {
        SpriteSet sprites;

        public MayoProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx,double dy, double dz) {

            return new MayoParticle(level, x, y, z, sprites, dx,dy,dz);
        }
    }
}


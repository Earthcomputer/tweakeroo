package fi.dy.masa.tweakeroo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import fi.dy.masa.tweakeroo.config.Configs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

@Mixin(targets = "net/minecraft/client/renderer/EntityRenderer$1")
public abstract class MixinEntityRenderer_Predicate
{
    @Inject(method = "apply(Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"), cancellable = true)
    private void ignoreDeadEntities(Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        if (Configs.Disable.DISABLE_DEAD_MOB_TARGETING.getBooleanValue() &&
            entity instanceof EntityLivingBase &&
            ((EntityLivingBase) entity).getHealth() <= 0f)
        {
            cir.setReturnValue(false);
        }
    }
}

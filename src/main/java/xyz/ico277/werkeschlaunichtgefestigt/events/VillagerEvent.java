package xyz.ico277.werkeschlaunichtgefestigt.events;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.ico277.werkeschlaunichtgefestigt.Utils;

@Mod.EventBusSubscriber
public class VillagerEvent {

    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public void onVillagerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) event.getTarget();
            NBTTagCompound villagerData = new NBTTagCompound();
            villager.writeEntityToNBT(villagerData);

            try {
                NBTTagCompound offers = villagerData.getCompoundTag("Offers");
                NBTTagList recipes = offers.getTagList("Recipes", 10); // 10 = NBTTagCompound
                for (int i = recipes.tagCount() - 1; i >= 0; i--) {
                    NBTTagCompound recipe = recipes.getCompoundTagAt(i);
                    NBTTagCompound sellingItem = recipe.getCompoundTag("sell");
                    NBTTagCompound tag = sellingItem.getCompoundTag("tag");

                    if (Utils.checkMending(tag))
                        recipes.removeTag(i);
                }
            } catch (Exception ignored) {
                return;
            }

            // write the modified data to the villager
            villager.readEntityFromNBT(villagerData);
        }
    }

}

package xyz.ico277.werkeschlaunichtgefestigt.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.ico277.werkeschlaunichtgefestigt.Utils;

import java.util.Random;

@Mod.EventBusSubscriber
public class BudderBeiDeFischeEvent {
    private final Random rng = new Random();

    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public void onItemFished(ItemFishedEvent event) {
        int num = rng.nextInt(40);
        System.out.println("rng: " + num);
        if (num == 10) {
            event.setCanceled(true);
        } else {
            for (ItemStack drop : event.getDrops()) {
                if (Utils.checkMending(drop.getTagCompound())) {
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }
}

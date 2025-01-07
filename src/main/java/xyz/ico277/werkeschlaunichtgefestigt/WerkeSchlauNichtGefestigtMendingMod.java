package xyz.ico277.werkeschlaunichtgefestigt;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import xyz.ico277.werkeschlaunichtgefestigt.commands.LizenzCommand;
import xyz.ico277.werkeschlaunichtgefestigt.events.BudderBeiDeFischeEvent;
import xyz.ico277.werkeschlaunichtgefestigt.events.VillagerEvent;

@Mod(modid = WerkeSchlauNichtGefestigtMendingMod.MODID, name = WerkeSchlauNichtGefestigtMendingMod.NAME, version = WerkeSchlauNichtGefestigtMendingMod.VERSION, acceptableRemoteVersions = "*")
public class WerkeSchlauNichtGefestigtMendingMod
{
    public static final String MODID = "werkeschlaunichtgefestigtmending";
    public static final String NAME = "WerkeSchlauNichtGefestigtMending";
    public static final String VERSION = "1.1-release";

    private static Logger logger;
    public static WerkeSchlauNichtGefestigtMendingMod instance;

    public static WerkeSchlauNichtGefestigtMendingMod getInstance() {
        return instance;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        instance = this;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Registriere Events...");
        MinecraftForge.EVENT_BUS.register(new VillagerEvent());
        MinecraftForge.EVENT_BUS.register(new BudderBeiDeFischeEvent());
        logger.info("Events wurden registriert");
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        logger.info("Registriere Commands...");
        event.registerServerCommand(new LizenzCommand());
        logger.info("Commands wurden registriert");
    }
}

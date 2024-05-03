package openkfc.invhudconfigurable.core;

import dlovin.inventoryhud.utils.potions.PotionUtils;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import openkfc.invhudconfigurable.config.CustomConfigHandler;
import openkfc.invhudconfigurable.util.CustomMod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(
        modid = InvHUD_Configurable.MODID,
        name = InvHUD_Configurable.NAME,
        version = InvHUD_Configurable.VERSION,
        dependencies = InvHUD_Configurable.DEPENDENCIES,
        clientSideOnly = true
)
public class InvHUD_Configurable {
    public static final String MODID = "invhud_configurable";
    public static final String NAME = "InvHUD_Configurable";
    public static final String VERSION = "1.0.1";
    public static final String DEPENDENCIES = "required-after:inventoryhud";

    public static Logger logger = LogManager.getLogger(MODID);

    @Mod.Instance(MODID)
    public InvHUD_Configurable instance;

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        logDEBUG("postInit");
        CustomConfigHandler.init();
        writeToPotionUtils(CustomConfigHandler.iconModMap);
    }

    /*Write a map<modid,ModWithPotions> to dlovin.inventoryhud.utils.potions.PotionUtils*/
    public static void writeToPotionUtils(Map<String, CustomMod> mapIn){
        for (Map.Entry<String, CustomMod> entry : mapIn.entrySet()) {
            if(Loader.isModLoaded(entry.getKey())) {
                if(!entry.getValue().haveNamingRule())
                    logWARN("Loaded a no-namingRule mod, may some effects can't display correctly : " + entry.getKey());
                PotionUtils.addMod(entry.getKey(), entry.getValue());
                logINFO("Successfully write to PotionUtils : " + entry.getKey());
            }
        }
    }

    public static void logINFO(String message){
        logger.log(Level.INFO,NAME + " : " + message);
    }

    public static void logDEBUG(String message){
        logger.log(Level.DEBUG,NAME + " : " + message);
    }

    public static void logWARN(String message){
        logger.log(Level.WARN, NAME + " : " + message);
    }

    public static void logERROR(String message){
        logger.log(Level.ERROR,NAME + " : " + message);
    }

}

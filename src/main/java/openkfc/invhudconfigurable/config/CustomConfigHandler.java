package openkfc.invhudconfigurable.config;

import openkfc.invhudconfigurable.core.InvHUD_Configurable;
import openkfc.invhudconfigurable.util.CustomMod;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomConfigHandler {
    private static File configFolder = null;

    private static File iconModFile = null;
    private static final String iconModFileContent = (
            "//(form1) modid, namingRule (%s in namingRule will replaced by the id of the effect) \n" +
            "//(exam1) srparasites, textures/gui/potion_%s.png \n" +
            "//(form2) modid, effectID, effectPath (effectID is the part after the colon in the registry name) \n" +
            "//(exam2) simpledifficulty, cold_resist, textures/potions/resist_cold.png \n" +
            "srparasites, textures/gui/potion_%s.png \n" +
            "simpledifficulty, textures/potions/%s.png \n" +
            "simpledifficulty, cold_resist, textures/potions/resist_cold.png \n" +
            "simpledifficulty, heat_resist, textures/potions/resist_heat.png"
    );
    public static final Map<String, CustomMod> iconModMap = new HashMap<>();

    public static void init(){
        initIconMod();
    }

    /*Try to create the json Folder*/
    private static boolean initDirectory(){
        configFolder = new File("config", InvHUD_Configurable.MODID);
        if(!configFolder.exists() || !configFolder.isDirectory()){
            if(!configFolder.mkdir()){
                InvHUD_Configurable.logERROR("Could not create the jsonFolder.");
                return false;
            }
        }
        return true;
    }

    /*Create&write iconMod.cfg and read it*/
    private static void initIconMod(){
        if(configFolder == null && !initDirectory()) return;
        iconModFile = new File(configFolder,"iconMod.cfg");
        try{
            if(!iconModFile.exists()){
                if(!iconModFile.createNewFile()) InvHUD_Configurable.logERROR("Fail creating iconMod.json file");
                else Files.write(iconModFile.toPath(), iconModFileContent.getBytes(StandardCharsets.UTF_8));
                InvHUD_Configurable.logINFO("Successfully writing iconMod.cfg");
            }
            List<String> list = Files.lines(iconModFile.toPath())
                    .map(String::trim)
                    .filter(s -> !s.startsWith("//"))
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            for(String entry : list){
                String[] entryArray = Arrays.stream(entry.split(",")).map(String::trim).toArray(String[]::new);
                if(entryArray.length == 2) {
                    if(!iconModMap.containsKey(entryArray[0])){
                        iconModMap.put(entryArray[0], new CustomMod(entryArray[0]));
                    }
                    iconModMap.get(entryArray[0]).putNamingRule(entryArray[1]);
                }else if(entryArray.length == 3){
                    if(!iconModMap.containsKey(entryArray[0])){
                        iconModMap.put(entryArray[0], new CustomMod(entryArray[0]));
                    }
                    iconModMap.get(entryArray[0]).putPotion_Path(entryArray[1], entryArray[2]);
                }else{
                    InvHUD_Configurable.logWARN("Illegal line in iconMod.cfg : " + entryArray[0]);
                }
            }
            InvHUD_Configurable.logINFO("Successfully loading iconMod.cfg");
        }catch (Exception ex){
            InvHUD_Configurable.logERROR("Fail initializing iconMod.cfg file : " + ex);
        }
    }

}

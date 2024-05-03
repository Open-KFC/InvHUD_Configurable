package openkfc.invhudconfigurable.util;

import dlovin.inventoryhud.utils.potions.ModWithPotions;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CustomMod extends ModWithPotions {
    private String namingRule = "";
    private final Map<String,String> mapPotion_Path = new HashMap<>();

    public CustomMod(String modid){
        super(false, modid, "");
    }

    @Override
    public ResourceLocation getPotionRes(String id){
        if(this.mapPotion_Path.containsKey(id)){
            return new ResourceLocation(this.modid, this.mapPotion_Path.get(id));
        }else if(haveNamingRule()){
            return new ResourceLocation(this.modid, String.format(this.namingRule, id));
        }else{
            /*The effect that not fount will display a black-purple square*/
            return new ResourceLocation(this.modid, "nothing");
        }
    }

    /*Use for InvHUD_Configurable.writeToPotionUtils*/
    public boolean haveNamingRule(){
        return namingRule != null && !namingRule.isEmpty();
    }

    public void putNamingRule(String namingRuleIn){
        this.namingRule = namingRuleIn;
    }

    public void putPotion_Path(String potionID, String potionPath){
        this.mapPotion_Path.put(potionID, potionPath);
    }

}

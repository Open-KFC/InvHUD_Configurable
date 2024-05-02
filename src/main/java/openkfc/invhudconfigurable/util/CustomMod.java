package openkfc.invhudconfigurable.util;

import dlovin.inventoryhud.utils.potions.ModWithPotions;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CustomMod extends ModWithPotions {
    private String namingRule;
    private final Map<String,String> mapPotion_Path = new HashMap<>();

    public CustomMod(String modid){
        super(false, modid, "");
    }

    @Override
    public ResourceLocation getPotionRes(String id){
        if(this.mapPotion_Path.containsKey(id)){
            return new ResourceLocation(modid, this.mapPotion_Path.get(id));
        }else{
            return new ResourceLocation(modid, String.format(this.namingRule, id));
        }
    }

    public void putNamingRule(String namingRuleIn){
        this.namingRule = namingRuleIn;
    }

    public void putPotion_Path(String potionID, String potionPath){
        this.mapPotion_Path.put(potionID, potionPath);
    }

}

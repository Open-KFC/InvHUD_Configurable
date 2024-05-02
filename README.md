## 简介
InventoryHUD+兼容能力令人蛋疼（例如它并不能正确显示SRP中的状态效果）。  
本模组的作用是允许模组玩家在config\invhud_configurable\iconMod.cfg中定义某个模组的状态效果图片源自哪个目录，并将玩家的定义输送给InvHUD+。  
## 用法
目前（v1.0.0）仅支持对以小图标（而不是把所有状态效果图标都集中在一张图上）形式存储图标的模组进行目录指定。  
在.cfg文件中，你可以以两种格式写入目录指定，一行一条，半角逗号分隔各个字符串：
1. modid, namingRule  
(eg.) srparasites, textures/gui/potion_%s.png  
会以指定的命名规则查找本模组的所有状态效果，%s将会被替换成该状态效果的名称（注册名中冒号后的部分）。
2. modid, effectID, effectPath  
(eg.) simpledifficulty, cold_resist, textures/potions/resist_cold.png  
会单独指定该状态效果的目录，优先级在上一条之上，但若一个模组未指定命名规则，只进行单独效果的目录指定会导致其他效果无法正确显示。

注：  
- 所有目录都是在assets/\<modid>/目录下的。
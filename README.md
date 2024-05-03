## Before That

Please note, after cloning the repository, you need to manually download the InventoryHUD-1.12.2.forge-3.4.4.jar file and place it into a manually created libs folder (refer to build.gradle-line 64). This step is essential to ensure the project runs smoothly, Thank you for your contributions to my development efforts.

克隆仓库后，请下载InventoryHUD-1.12.2.forge-3.4.4.jar并将其置于手动新建的libs文件夹（见build.gradle-line64），麻烦了……

Download Link：https://www.curseforge.com/minecraft/mc-mods/inventory-hud-forge/files/3565980


__This mod is NOT a revised version incorporating InventoryHUD+ features__; instead, it serves as an expansion module for InventoryHUD+ and cannot operate independently.

Want Chinese Description? Over Here: https://www.mcmod.cn/class/14637.html

## Overview

(__Applicable to Minecraft 1.12.2__)The Inventory HUD+ mod's PotionHUD is hardcoded to work with select mods, leading to inaccurate or absent potion icons from others, such as SRP and SimpleDifficulty.

This mod empowers players to dictate in the .cfg file (config/invhud_configurable/iconMod.cfg) the exact locations where Inventory HUD+ should retrieve potion icons for various mods.

## Usage

Presently, this mod supports defining path global path lookup rules for a mod or assigning specific paths for individual potion.

In the .cfg file, structure each line as either a lookup rule or a specific path, separating elements with commas.

1. Defining Lookup Rules:

    - \<modid>, \<lookup rule>

    - e.g.: srparasites, textures/gui/potion_%s.png

    - Here, %s acts as a placeholder for the potion's name (post-colon in its registry name). So assets/srparasites/textures/gui/potion_viral.png serves as the image source for the "srparasites:viral" effect.

2. Specific Paths Assignment:

    - \<modid>, \<potion name>, \<path>

    - e.g.: simpledifficulty, cold_resist, textures/potions/resist_cold.png

    - (Particular paths override general rules.) This assignment InventoryHUD+ to use assets/simpledifficulty/textures/potions/resist_cold.png for the "simpledifficulty:cold_resist" effect.

## Note:

- In the absence of a global lookup rule for a mod but with a defined path for one of its potions, remaining potions from that mod will default to displaying as purple-black placeholder.

- Currently, this customization only applies to mods that manage potion icons as discrete smaller images, not concatenated within a single larger image.

- The auto-generated .cfg file is pre-equipped with support configurations for SRP and SimpleDifficulty.
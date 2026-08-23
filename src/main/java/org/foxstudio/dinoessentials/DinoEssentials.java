package org.foxstudio.dinoessentials;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;

@Mod(DinoEssentials.MODID)
public class DinoEssentials {

    public static final String MODID = "dinoessentials";

    public DinoEssentials() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DinoEssentialsConfig.COMMON_SPEC);
    }
}

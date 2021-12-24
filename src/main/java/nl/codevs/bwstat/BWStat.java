package nl.codevs.bwstat;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BWStat.MODID, version = BWStat.VERSION, clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class BWStat
{
    public static final String MODID = "bwstat";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
        Minecraft.getMinecraft().theWorld.playerEntities.forEach(p -> System.out.println(p.getName()));
    }
}

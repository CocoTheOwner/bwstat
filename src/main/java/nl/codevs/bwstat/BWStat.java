package nl.codevs.bwstat;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import nl.codevs.bwstat.data.BWLobby;

@Mod(modid = BWStat.MODID, version = BWStat.VERSION, clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class BWStat
{
    public static final String MODID = "bwstat";
    public static final String VERSION = "0.1";
    private static final Minecraft MC = Minecraft.getMinecraft();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }

    /**
     * Players in the lobby with stats.
     */
    private static final BWLobby BW_LOBBY = new BWLobby();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void tick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            return;
        }

        if (MC.isGamePaused() || MC.thePlayer == null || MC.theWorld == null) {
            return;
        }

        // TODO: Check is Hypixel

        // Run code every tick
        BW_LOBBY.update(MC.theWorld.playerEntities);
    }
}

package fi.dy.masa.tellme;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import malilib.config.util.ConfigUtils;
import malilib.registry.Registry;
import fi.dy.masa.tellme.command.ClientCommandTellme;
import fi.dy.masa.tellme.command.CommandTellme;
import fi.dy.masa.tellme.reference.Reference;


@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.MOD_VERSION,
        dependencies = "required-after:malilib;required-after:mixinbooter@[8.0,)",
        acceptableRemoteVersions = "*",
        customProperties = {
                @Mod.CustomProperty(k = "license", v = "LGPLv3"),
                @Mod.CustomProperty(k = "issueTrackerUrl", v = "https://github.com/RuiXuqi/tellme/issues")
        }
)
public class TellMe
{
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public static String configDirPath;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if (event.getSide().isClient())
        {
            onClientReady();
        }
        Registry.CLIENT_COMMAND_HANDLER.registerCommand(new ClientCommandTellme());
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandTellme());
    }

    private static void onClientReady()
    {
        configDirPath = ConfigUtils.getConfigDirectory().resolve(Reference.MOD_ID).toFile().getAbsolutePath();
    }
}

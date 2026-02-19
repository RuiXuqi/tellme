package fi.dy.masa.tellme.datadump;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

import fi.dy.masa.tellme.datadump.DataDump.Alignment;
import fi.dy.masa.tellme.datadump.DataDump.Format;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityDump
{
    public static List<String> getFormattedEntityDump(Format format)
    {
        DataDump entityDump = new DataDump(5, format);

        for (Map.Entry<ResourceLocation, EntityEntry> entry : ForgeRegistries.ENTITIES.getEntries())
        {
            Class<? extends Entity> clazz = entry.getValue().getEntityClass();
            String className = clazz.getSimpleName();
            String oldName = EntityList.getTranslationName(entry.getKey());

            entityDump.addData("Minecraft", entry.getKey().toString(), oldName, className, String.valueOf(EntityList.getID(clazz)));
        }

        entityDump.addTitle("Mod name", "Registry name", "Old name", "Entity class name", "ID");
        entityDump.setColumnProperties(4, Alignment.RIGHT, true); // id
        entityDump.setUseColumnSeparator(true);

        return entityDump.getLines();
    }
}

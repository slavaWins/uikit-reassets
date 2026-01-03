package org.slavawins.uikit.menucore;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.slavawins.uikit.Uikit;

import java.util.List;

public class PMetaHelper {

    public static void setPlayerMetadata(Player player, String key, String value) {
        if(player==null) {

            return;
        }
        player.setMetadata(key, new FixedMetadataValue(Uikit.getInstanse(), value));
    }

    public static void remove(Player player, String key) {
        player.removeMetadata(key, Uikit.getInstanse());
    }
    public static String get(Player player, String key) {
        List<MetadataValue> metadata = player.getMetadata(key);
        for (MetadataValue value : metadata) {
            if (value.getOwningPlugin().equals(Uikit.getInstanse())) {
                return value.asString();
            }
        }
        return null;
    }

}

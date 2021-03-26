package me.erik.anotherskyblockmod.util;

import me.erik.anotherskyblockmod.core.Location;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class SBUtils {
    
    public static boolean inSkyblock = false;
    public static boolean inDungeons = false;
    
    static Random random = new Random();
    
    /**
     * Checks if the player is currently playing on the Hypixel network.
     * @return {@code true} if the player is on Hypixel, else {@code false}
     */
    public static boolean isOnHypixel() {
        
        try {
            
            Minecraft mc = Minecraft.getMinecraft();
            
            if (mc == null || mc.theWorld == null || mc.thePlayer == null || mc.isSingleplayer()) return false;
            
            if (mc.thePlayer.getClientBrand() != null && mc.thePlayer.getClientBrand().toLowerCase().contains("hypixel"))
                return true;
            if (mc.getCurrentServerData() != null)
                return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
            
            return false;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    /**
     * Checks if the player is currently playing skyblock.
     */
    public static void checkForSkyblock() {
        
        Minecraft mc = Minecraft.getMinecraft();
        
        if (isOnHypixel()) {
            
            ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            
            if (scoreboardObj != null) {
                
                String scObjName = ScoreboardUtils.cleanSB(scoreboardObj.getDisplayName());
                
                if (scObjName.contains("SKYBLOCK")) {
                    inSkyblock = true;
                    return;
                }
                
            }
            
        }
        
        inSkyblock = false;
        
    }
    
    /**
     * Checks if the player is currently in a dungeon.
     */
    public static void checkForDungeons() {
        
        if (inSkyblock) {
            
            List<String> scoreboard = ScoreboardUtils.getSidebarLines();
            
            for (String s : scoreboard) {
                if (ScoreboardUtils.cleanSB(s).contains("The Catacombs")) {
                    inDungeons = true;
                    return;
                }
            }
            
        }
        
        inDungeons = false;
        
    }
    
    @Nonnull
    public static Location getCurrentLocation() {
        
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null || mc.isSingleplayer() || !isOnHypixel()) return Location.UNKNOWN;
        
        List<String> lines = ScoreboardUtils.getSidebarLines();
        String locString = StringUtils.stripControlCodes(lines.get(3)).replaceAll("[^A-Za-z0-9() ]", "").trim().toUpperCase();
        
        return Location.fromString(locString);
        
    }
    
}

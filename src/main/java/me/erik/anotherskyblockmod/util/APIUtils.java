package me.erik.anotherskyblockmod.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIUtils {
    
    public static JsonObject getJSONResponse(String urlString) {
        
        JsonObject response = new JsonObject();
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        
        try {
            
            URL url = new URL(urlString);
            response = (new JsonParser()).parse(IOUtils.toString(url.openConnection().getInputStream(), StandardCharsets.UTF_8)).getAsJsonObject();
            
        } catch (IOException ex) {
            
            if (player != null) player.addChatMessage(new ChatComponentText("Error while trying to access Hypixel API."));
            System.out.println("Error while trying to access Hypixel API.");
            ex.printStackTrace();
            
        }
        
        return response;
        
    }
    
}

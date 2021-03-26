package me.erik.anotherskyblockmod.keybindings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.List;

public class Keybindings {
    
    public static CustomKeyBind WARP_HOME;
    public static CustomKeyBind WARP_HUB;
    public static CustomKeyBind WARP_DUNGEON_HUB;
    
    public static CustomKeyBind CRAFT;
    public static CustomKeyBind PETS;
    public static CustomKeyBind WARDROBE;
    
    public static CustomKeyBind PLAY_SKYBLOCK;
    public static CustomKeyBind CLEAR_CHAT;
    
    public static List<CustomKeyBind> register() {
        
        final String w = "Warps";
        final String sb = "Skyblock";
        final String m = "Miscellaneous";
        
        WARP_HOME = new CustomKeyBind("warp home", "Home", Keyboard.KEY_H, w);
        WARP_HUB = new CustomKeyBind("warp hub", "Hub", Keyboard.KEY_G, w);
        WARP_DUNGEON_HUB = new CustomKeyBind("warp dungeon_hub", "Dungeon Hub", Keyboard.KEY_B, w);
        
        CRAFT = new CustomKeyBind("craft", "Craft", Keyboard.KEY_X, sb);
        PETS = new CustomKeyBind("viewpetsmenu", "Pets Menu", Keyboard.KEY_O, sb);
        WARDROBE = new CustomKeyBind("wardrobe", "Wardrobe", Keyboard.KEY_R, sb);
        
        PLAY_SKYBLOCK = new CustomKeyBind("play sb", "Play Skyblock", Keyboard.KEY_COMMA, m);
        CLEAR_CHAT = new CustomKeyBind("Clear Chat", Keyboard.KEY_F4, m);
        
        final List<CustomKeyBind> keys = Arrays.asList(WARP_HOME, WARP_HUB, WARP_DUNGEON_HUB, CRAFT, PETS, WARDROBE, PLAY_SKYBLOCK, CLEAR_CHAT);
        
        for (KeyBinding keyBinding : keys) ClientRegistry.registerKeyBinding(keyBinding);
        
        return keys;
        
    }
    
}

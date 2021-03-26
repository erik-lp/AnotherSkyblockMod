package me.erik.anotherskyblockmod.keybindings;

import me.erik.anotherskyblockmod.AnotherSkyblockMod;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class CustomKeyBind extends KeyBinding {
    
    private final boolean isCommand;
    private final String displayName;
    
    private String command;
    
    public CustomKeyBind(String command, String displayName, String subCategory) {
        super(displayName, Keyboard.KEY_NONE, AnotherSkyblockMod.MODNAME + " - " + subCategory);
        this.command = command;
        this.displayName = displayName;
        isCommand = true;
    }
    
    public CustomKeyBind(String command, String displayName, int key, String subCategory) {
        super(displayName, key, AnotherSkyblockMod.MODNAME + " - " + subCategory);
        this.command = command;
        this.displayName = displayName;
        isCommand = true;
    }
    
    public CustomKeyBind(String displayName, int key, String subCategory) {
        super(displayName, key, AnotherSkyblockMod.MODNAME + " - " + subCategory);
        this.displayName = displayName;
        isCommand = false;
    }
    
    public String getCommand() { return command; }
    
    public boolean isCommand() { return isCommand; }
    
}

package me.erik.anotherskyblockmod.listeners;

import me.erik.anotherskyblockmod.AnotherSkyblockMod;
import me.erik.anotherskyblockmod.keybindings.CustomKeyBind;
import me.erik.anotherskyblockmod.keybindings.Keybindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyListener {
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        
        if (Minecraft.getMinecraft().thePlayer == null) return;
        
        for (CustomKeyBind keyBinding : AnotherSkyblockMod.keyBindings) {
            
            if (keyBinding.isPressed()) {
                
                if (keyBinding.isCommand()) Minecraft.getMinecraft().thePlayer.sendChatMessage("/" + keyBinding.getCommand());
                else if (keyBinding.equals(Keybindings.CLEAR_CHAT)) Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
                
            }
            
        }
        
    }
    
}

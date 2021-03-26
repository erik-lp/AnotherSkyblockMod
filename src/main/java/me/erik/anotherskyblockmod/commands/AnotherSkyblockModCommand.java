package me.erik.anotherskyblockmod.commands;

import club.sk1er.mods.core.ModCore;
import me.erik.anotherskyblockmod.AnotherSkyblockMod;
import me.erik.anotherskyblockmod.core.graphics.gui.LocationEditGui;
import me.erik.anotherskyblockmod.util.APIUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.List;

public final class AnotherSkyblockModCommand extends CommandBase {
    
    @Override
    public String getCommandName() { return "anotherskyblockmod"; }
    
    @Override
    public List<String> getCommandAliases() { return Collections.singletonList("asm"); }
    
    @Override
    public String getCommandUsage(ICommandSender sender) { return "/" + this.getCommandName(); }
    
    @Override
    public int getRequiredPermissionLevel() { return 0; }
    
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        
        if (!(sender instanceof EntityPlayerSP)) return;
        EntityPlayerSP player = (EntityPlayerSP) sender;
        
        if (args.length == 0) {
            ModCore.getInstance().getGuiHandler().open(AnotherSkyblockMod.CONFIG.gui());
            return;
        }
        
        switch (args[0].toLowerCase()) {
            
            case "setkey":
                
                if (args.length != 2) {
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Please provide a valid Hypixel API key!"));
                    return;
                }
                
                new Thread(() -> {
                    
                    String key = args[1];
                    
                    if (APIUtils.getJSONResponse("https://api.hypixel.net/key?key=" + key).get("success").getAsBoolean()) {
                        AnotherSkyblockMod.CONFIG.apiKey = key;
                        AnotherSkyblockMod.CONFIG.markDirty();
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Updated your API key to " + key));
                        AnotherSkyblockMod.CONFIG.writeData();
                    } else player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Please provide a valid Hypixel API key!"));
                    
                }).start();
                
                break;
                
            case "config":
                ModCore.getInstance().getGuiHandler().open(AnotherSkyblockMod.CONFIG.gui());
                break;
                
            case "locations":
                ModCore.getInstance().getGuiHandler().open(new LocationEditGui());
                break;
                
            default:
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid command! (/" + getCommandName() + " help for more info)"));
            
        }
        
    }
    
}

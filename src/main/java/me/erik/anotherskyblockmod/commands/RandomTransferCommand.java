package me.erik.anotherskyblockmod.commands;

import me.erik.anotherskyblockmod.util.ArrayUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomTransferCommand extends CommandBase {
    
    public static boolean gettingParty = false;
    public static int delimiter = 0;
    public static boolean transferring = false;
    
    public static Thread partyThread = null;
    
    public static List<String> party = new ArrayList<>();
    
    @Override
    public String getCommandName() {
        return "randomtransfer";
    }
    
    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("rt");
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        
        if (!(sender instanceof EntityPlayerSP)) return;
        EntityPlayerSP player = (EntityPlayerSP) sender;
        
        party.clear();
        
        partyThread = new Thread(() -> {
            
            try {
                player.sendChatMessage("/pl");
                
                gettingParty = true;
                while (gettingParty) {
                    Thread.sleep(10);
                }
                
                if (party.size() == 0) return;
                
                player.sendChatMessage("/p transfer " + ArrayUtils.getRandom(party));
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        });
        
        partyThread.start();
        
    }
    
}

package me.erik.anotherskyblockmod.listeners;

import me.erik.anotherskyblockmod.AnotherSkyblockMod;
import me.erik.anotherskyblockmod.commands.RandomTransferCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener {
    
    private static final Pattern PARTY_JOIN_PATTERN = Pattern.compile("^You joined .* party.$");
    private static final Pattern DUNGEON_FINDER_JOIN_PATTERN = Pattern.compile("^Dungeon Finder > (\\w+) joined the dungeon group! \\(([A-Za-z]+) Level (\\d+)\\)$");
    private static final Pattern PARTY_LEAVE_PATTERN = Pattern.compile("^You left the party\\.$");
    private static final Pattern PARTY_DISBAND_PATTERN_1 = Pattern.compile("^The party was disbanded because all invites expired and the party was empty$");
    private static final Pattern PARTY_DISBAND_PATTERN_2 = Pattern.compile("^.* has disbanded the party!$");
    private static final Pattern PARTY_KICK_PATTERN = Pattern.compile("^You have been kicked from the party by .*$");
    
    private static final Pattern PARTY_START_PATTERN = Pattern.compile("^Party Members \\((\\d+)\\)$");
    private static final Pattern LEADER_PATTERN = Pattern.compile("^Party Leader: (?:\\[.+?] )?(\\w+) \u25CF$");
    private static final Pattern MEMBERS_PATTERN = Pattern.compile(" (?:\\[.+?] )?(\\w+) \u25CF");
    
    @SubscribeEvent
    public void onPartyJoinOrLeave(ClientChatReceivedEvent event) {
        
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        
        boolean autoChatP = AnotherSkyblockMod.CONFIG.autoJoinPartyChat;
        boolean autoChatA = AnotherSkyblockMod.CONFIG.autoJoinAllChat;
        
        if (event.type != 2) {
            
            String msg = StringUtils.stripControlCodes(event.message.getUnformattedText());
            
            Matcher join1 = PARTY_JOIN_PATTERN.matcher(msg);
            Matcher join2 = DUNGEON_FINDER_JOIN_PATTERN.matcher(msg);
            Matcher leave1 = PARTY_LEAVE_PATTERN.matcher(msg);
            Matcher leave2 = PARTY_DISBAND_PATTERN_1.matcher(msg);
            Matcher leave3 = PARTY_DISBAND_PATTERN_2.matcher(msg);
            Matcher leave4 = PARTY_KICK_PATTERN.matcher(msg);
            
            if (join1.matches() && autoChatP) {
                player.sendChatMessage("/chat p");
            } else if (join2.matches() && autoChatP) {
                if (msg.split(" ")[3].trim().equals(player.getName())) player.sendChatMessage("/chat p");
            } else if ((leave1.matches() || leave2.matches() || leave3.matches() || leave4.matches()) && autoChatA) {
                player.sendChatMessage("/chat a");
            }
            
        }
        
    }
    
    @SubscribeEvent
    public void onPartyList(ClientChatReceivedEvent event) {
        
        if (event.type != 2) {
            
            String msg = StringUtils.stripControlCodes(event.message.getUnformattedText());
            
            if (RandomTransferCommand.gettingParty) {
                
                if (msg.contains("-----")) {
                    
                    switch (RandomTransferCommand.delimiter) {
                        case 0:
                            System.out.println("Get Party Delimiter Cancelled");
                            RandomTransferCommand.delimiter++;
                            event.setCanceled(true);
                            return;
                        case 1:
                            System.out.println("Done querying party");
                            RandomTransferCommand.gettingParty = false;
                            RandomTransferCommand.delimiter = 0;
                            event.setCanceled(true);
                    }
                    
                } else if (msg.startsWith("Party M") || msg.startsWith("Party Leader")) {
                    
                    EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
                    
                    Matcher partyStart = PARTY_START_PATTERN.matcher(msg);
                    Matcher leader = LEADER_PATTERN.matcher(msg);
                    Matcher members = MEMBERS_PATTERN.matcher(msg);
                    
                    if (partyStart.matches() && Integer.parseInt(partyStart.group(1)) == 1) {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Can't transfer the party to yourself!"));
                        RandomTransferCommand.partyThread.interrupt();
                    } else if (leader.matches() && !(leader.group(1).equals(player.getName()))) {
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You are not party leader."));
                        RandomTransferCommand.partyThread.interrupt();
                    } else {
                        while (members.find()) {
                            String partyMember = members.group(1);
                            if (!partyMember.equals(player.getName())) RandomTransferCommand.party.add(partyMember);
                        }
                    }
                    
                    event.setCanceled(true);
                    
                }
                
            }
            
        }
        
    }
    
}

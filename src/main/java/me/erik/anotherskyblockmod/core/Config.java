package me.erik.anotherskyblockmod.core;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.*;

import java.io.File;

public class Config extends Vigilant {
    
    public Config() {
        super(new File("./config/anotherskyblockmod/config.toml"));
        initialize();
    }
    
    @Property(
            type = PropertyType.TEXT,
            name = "Hypixel API Key",
            description = "Your Hypixel API Key (can be obtained by doing /api new).\nSet this with /anotherskyblockmod setkey <key>.",
            category = "General"
    )
    public String apiKey = "";
    
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto-Join party chat",
            description = "Automatically switches to party chat when joining a party.",
            category = "Social"
    )
    public boolean autoJoinPartyChat;
    
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto-Join all chat",
            description = "Automatically switches to all chat when leaving a party.",
            category = "Social"
    )
    public boolean autoJoinAllChat;
    
}

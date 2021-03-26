package me.erik.anotherskyblockmod;

import me.erik.anotherskyblockmod.commands.AnotherSkyblockModCommand;
import me.erik.anotherskyblockmod.commands.RandomTransferCommand;
import me.erik.anotherskyblockmod.core.Config;
import me.erik.anotherskyblockmod.core.graphics.gui.GuiManager;
import me.erik.anotherskyblockmod.keybindings.CustomKeyBind;
import me.erik.anotherskyblockmod.keybindings.Keybindings;
import me.erik.anotherskyblockmod.listeners.ChatListener;
import me.erik.anotherskyblockmod.listeners.KeyListener;
import me.erik.anotherskyblockmod.listeners.PlayerListener;
import me.erik.anotherskyblockmod.util.SBUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = AnotherSkyblockMod.MODID, name = AnotherSkyblockMod.MODNAME, version = AnotherSkyblockMod.VERSION, clientSideOnly = true)
public class AnotherSkyblockMod {
    
    public static final String MODID = "anotherskyblockmod";
    public static final String MODNAME = "AnotherSkyblockMod";
    public static final String VERSION = "1.0";
    public static final List<CustomKeyBind> keyBindings = new ArrayList<>();
    
    public static Config CONFIG = new Config();
    public static GuiManager GUIMANAGER;
    
    public static File modDir;
    
    public static boolean usingLabymod = false;
    public static boolean usingNEU = false;
    
    public static int ticks = 0;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        
        modDir = new File(event.getModConfigurationDirectory(), "anotherskyblockmod");
        
        if (!modDir.exists()) {
            boolean success = modDir.mkdirs();
            if (!success) System.out.println("Failed to create configuration directory.");
        }
        
        GUIMANAGER = new GuiManager();
        
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
        
        CONFIG.preload();
        
        ClientCommandHandler.instance.registerCommand(new AnotherSkyblockModCommand());
        ClientCommandHandler.instance.registerCommand(new RandomTransferCommand());
        
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new KeyListener());
        MinecraftForge.EVENT_BUS.register(new PlayerListener());
        
        keyBindings.addAll(Keybindings.register());
        
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        usingLabymod = Loader.isModLoaded("labymod");
        usingNEU = Loader.isModLoaded("notenoughupdates");
    }
    
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        
        if (event.phase != TickEvent.Phase.START) return;
        if (Minecraft.getMinecraft().thePlayer == null) {
            ticks = 0;
            return;
        }
        
        if (ticks % 20 == 0) { // every second
            SBUtils.checkForSkyblock();
            SBUtils.checkForDungeons();
        }
        
        ticks++;
        
    }
    
}
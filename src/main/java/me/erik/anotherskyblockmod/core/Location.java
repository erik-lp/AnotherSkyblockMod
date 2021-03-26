package me.erik.anotherskyblockmod.core;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Getter
public enum Location {
    
    AUCTION_HOUSE(1, "Auction House"),
    BANK(1, "Bank"),
    BAZAAR(1, "Bazaar Alley"),
    CANVAS_ROOM(1, "Canvas Room"),
    COAL_MINE(1, "Coal Mine"),
    COLOSSEUM(1, "Colosseum"),
    COLOSSEUM_ARENA(1, "Colosseum Arena"),
    DUEL_ZONE(1, "Duel Zone"),
    FARM(1, "Farm"),
    FASHION_SHOP(1, "Fashion Shop"),
    FISHERMANS_HUT(1, "Fisherman's Hut"),
    FLOWER_HOUSE(1, "Flower House"),
    FOREST(1, "Forest"),
    GRAVEYARD(1, "Graveyard"),
    HIGH_LEVEL(1, "High Level"),
    LIBRARY(1, "Library"),
    MOUNTAIN(1, "Mountain"),
    RUINS(1, "Ruins"),
    TAVERN(1, "Tavern"),
    VILLAGE(1, "Village"),
    WILDERNESS(1, "Wilderness"),
    WIZARD_TOWER(1, "Wizard Tower"),
    
    BIRCH_PARK(2, "Birch Park"),
    SPRUCE_WOODS(2, "Spruce Woods"),
    SAVANNA_WOODLAND(2, "Savanna Woodland"),
    DARK_THICKET(2, "Dark Thicket"),
    JUNGLE_ISLAND(2, "Jungle Island"),
    
    GOLD_MINE(3, "Gold Mine"),
    
    DEEP_CAVERNS(4, "Deep Caverns"),
    GUNPOWDER_MINES(4, "Gunpowder Mines"),
    LAPIS_QUARRY(4, "Lapis Quarry"),
    PIGMAN_DEN(4, "Pigmen's Den"),
    SLIMEHILL(4, "Slimehill"),
    DIAMOND_RESERVE(4, "Diamond Reserve"),
    OBSIDIAN_SANCTUARY(4, "Obsidian Sanctuary"),
    
    DWARVEN_MINES(5, "Dwarven Mines"),
    DWARVEN_VILLAGE(5, "Dwarven Village"),
    THE_FORGE(5, "The Forge"),
    FORGE_BASIN(5, "Forge Basin"),
    LAVA_SPRINGS(5, "Lava Springs"),
    PALACE_BRIDGE(5, "Palace Bridge"),
    ROYAL_PALACE(5, "Royal Palace"),
    ROYAL_MINES(5, "Royal Mines"),
    ARISTOCRAT_PASSAGE(5, "Aristocrat Passage"),
    HANGING_COURT(5, "Hanging Court"),
    CLIFFSIDE_VEINS(5, "Cliffside Veins"),
    RAMPARTS_QUARRY(5, "Rampart's Quarry"),
    DIVANS_GATEWAY(5, "Divan's Gateway"),
    FAR_RESERVE(5, "Far Reserve"),
    GOBLIN_BURROWS(5, "Goblin Burrows"),
    UPPER_MINES(5, "Upper Mines"),
    MINERS_GUILD(5, "Miner's Guild"),
    GREAT_ICE_WALL(5, "Great Ice Wall"),
    MIST(5, "The Mist"),
    CC_MINECARTS_CO(5, "C&C Minecarts Co."),
    GRAND_LIBRARY(5, "Grand Library"),
    BARRACKS_OF_HEROES(5, "Barracks Of Heroes"),
    
    THE_BARN(6, "The Barn"),
    
    MUSHROOM_DESERT(7, "Mushroom Desert"),
    
    SPIDERS_DEN(8, "Spider's Den"),
    
    BLAZING_FORTRESS(9, "Blazing Fortress"),
    
    THE_END(10, "The End"),
    DRAGONS_NEST(10, "Dragon's Nest"),
    
    JERRY_POND(11, "Jerry Pond"),
    JERRYS_WORKSHOP(12, "Jerry's Workshop"),
    
    DUNGEON_HUB(13, "Dungeon Hub"),
    
    THE_CATACOMBS(14, "The Catacombs"),
    
    ISLAND(15, "Your Island"),
    GUEST_ISLAND(15, "'s Island"),
    
    NONE(16, "None"),
    
    UNKNOWN(-1, "Unknown");
    
    private final int area;
    private final String scoreboardName;
    
    Location(int area, String scoreboardName) {
        this.area = area;
        this.scoreboardName = scoreboardName;
    }
    
    public boolean isSameIsland(Location otherLocation) { return this.getArea() == otherLocation.getArea(); }
    
    @Nonnull
    public static Location fromString(String locationString) {
        for (Location loc : Location.values())
            if (loc.getScoreboardName().equals(locationString)) return loc;
        return Location.UNKNOWN;
    }
    
}

package me.erik.anotherskyblockmod.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardUtils {
    
    public static String cleanSB(String scoreboard) {
        
        StringBuilder cleaned = new StringBuilder();
        
        for (char c : StringUtils.stripControlCodes(scoreboard).toCharArray())
            if ((int) c > 20 && (int) c < 127) cleaned.append(c);
        
        return cleaned.toString();
        
    }
    
    public static List<String> getSidebarLines() {
        
        List<String> lines = new ArrayList<>();
        
        World world = Minecraft.getMinecraft().theWorld;
        if (world == null) return lines;
        
        Scoreboard scoreboard = world.getScoreboard();
        if (scoreboard == null) return lines;
        
        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
        if (objective == null) return lines;
        
        Collection<Score> scores = scoreboard.getSortedScores(objective);
        List<Score> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .collect(Collectors.toList());
        
        scores = list.size() > 15 ? Lists.newArrayList(Iterables.skip(list, scores.size() - 15)) : list;
        
        for (Score score : scores) {
            ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            lines.add(ScorePlayerTeam.formatPlayerName(team, score.getPlayerName()));
        }
        
        return lines;
        
    }
    
}

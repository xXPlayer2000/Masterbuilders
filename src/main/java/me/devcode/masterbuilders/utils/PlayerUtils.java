package me.devcode.masterbuilders.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;

@Getter
public class PlayerUtils {

    private List<Player> players = new ArrayList<>();
    private List<Player> specs = new ArrayList<>();
    private HashMap<Player, Plot> playerPlot = new HashMap<>();


    public void addPlayer(Player player) {
        if(!players.contains(player)) {
            players.add(player);
        }
    }

    public void addSpec(Player player) {
        if(!specs.contains(player)) {
            specs.add(player);
        }
    }

    public void setPlot(Player player, Plot plot) {
        playerPlot.put(player, plot);
    }

    public Plot getPlot(Player player) {
        if(playerPlot.containsKey(player)) {
            return playerPlot.get(player);
        }
        return null;
    }

}

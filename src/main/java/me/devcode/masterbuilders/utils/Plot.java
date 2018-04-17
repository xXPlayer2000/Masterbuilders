package me.devcode.masterbuilders.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plot {

    private Location spawnLocation, firstLocation, secondLocation;
    private Player player;
    private Cuboid cuboid;

    public Plot(Location spawnLocation, Location firstLocation, Location secondLocation) {
        this.spawnLocation = spawnLocation;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.cuboid = new Cuboid(firstLocation, secondLocation);
    }

    public void teleportPlayer() {
        this.player.teleport(spawnLocation);
    }

    public boolean isInPlot(Location location) {
        return cuboid.containsLocation(location);
    }

}

package me.devcode.masterbuilders.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
public class Plots {
    private File file = new File("plugins/Masterbuilders", "plots.yml");
    private FileConfiguration fileConfiguration;
    public List<Plot> plots = new ArrayList<>();
    public Plots() {

    }

    public void loadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void loadPlots() {
    fileConfiguration.getConfigurationSection("Plots.").getKeys(false).forEach(s ->  {
       World world = Bukkit.getWorld(fileConfiguration.getString("Plots." + s + ".World"));
        double x = fileConfiguration.getDouble("Plots." + s + ".X");
        double y = fileConfiguration.getDouble("Plots." + s + ".Y");
        double z = fileConfiguration.getDouble("Plots." + s + ".Z");
        double x2 = fileConfiguration.getDouble("Plots." + s + ".X2");
        double y2 = fileConfiguration.getDouble("Plots." + s + ".Y2");
        double z2 = fileConfiguration.getDouble("Plots." + s + ".Z2");
        if(getSpawnLocation(s)!= null) {
            Plot plot = new Plot(getSpawnLocation(s), new Location(world, x, y, z), new Location(world, x2, y2, z2));
            plots.add(plot);
        }
    });
    }

    public Location getSpawnLocation(String s) {
        if(fileConfiguration.getString("PlotSpawns." + s + ".World") == null)
            return null;
            World world = Bukkit.getWorld(fileConfiguration.getString("PlotSpawns." + s + ".World"));
            double x = fileConfiguration.getDouble("PlotSpawns." + s + ".X");
            double y = fileConfiguration.getDouble("PlotSpawns." + s + ".Y");
            double z = fileConfiguration.getDouble("PlotSpawns." + s + ".Z");
            return new Location(world, x, y, z);
    }


}

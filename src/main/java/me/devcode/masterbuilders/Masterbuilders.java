package me.devcode.masterbuilders;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.devcode.masterbuilders.listeners.JoinListener;
import me.devcode.masterbuilders.listeners.PlotListeners;
import me.devcode.masterbuilders.utils.CountdownHandler;
import me.devcode.masterbuilders.utils.PlayerUtils;
import me.devcode.masterbuilders.utils.Plots;

@Getter
@Setter
public class Masterbuilders extends JavaPlugin{

    @Getter
    private static Masterbuilders instance;
    private Plots plots;
    private PlayerUtils playerUtils;
    private int minPlayers = 1;
    private CountdownHandler countdownHandler;
    @Override
    public void onEnable() {
    instance = this;
        plots = new Plots();
       // plots.getFile().mkdir();
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if(!plots.getFile().exists())
            loadFile("plots.yml");

        playerUtils = new PlayerUtils();
        countdownHandler = new CountdownHandler();
        countdownHandler.onLobby();
        plots.loadConfig();
        plots.loadPlots();
        registerEvents();
    }

    public void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new PlotListeners(), this);
    }

    @SneakyThrows
    public void loadFile(String file) {
        File t = new File(this.getDataFolder(), file);
        System.out.println("Writing new file: " + t.getAbsolutePath());

        t.createNewFile();
        FileWriter out = new FileWriter(t);
        InputStream is = getClass().getResourceAsStream("/" + file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            out.write(line + "\n");
        }
        System.out.println(line);
        out.flush();
        is.close();
        isr.close();
        br.close();
        out.close();

    }

}

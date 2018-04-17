package me.devcode.masterbuilders.utils;

import org.apache.commons.lang.math.IntRange;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.IntStream;

import lombok.Getter;
import lombok.Setter;
import me.devcode.masterbuilders.Masterbuilders;

public class CountdownHandler {
    @Getter
    @Setter
    private boolean started = false;
    @Getter
    private int timer = 61;
    public void onLobby() {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(!started) {
                    if(Bukkit.getOnlinePlayers().size() >= Masterbuilders.getInstance().getMinPlayers()) {
                        setStarted(true);
                    }else{
                        Bukkit.getOnlinePlayers().forEach(player ->
                            TitleApi.endTitel(player, "§cEs Fehlen noch §e" +(Masterbuilders.getInstance().getMinPlayers()-Bukkit.getOnlinePlayers().size())));

                    }
                }else{
                    if(Bukkit.getOnlinePlayers().size() < Masterbuilders.getInstance().getMinPlayers()) {
                        setStarted(false);
                        timer = 61;
                        return;
                    }
                    if(timer <= timer) {
                        timer--;
                        Bukkit.getOnlinePlayers().forEach(player -> {
                           player.setLevel(timer);
                            player.setExp(timer/60);
                        });
                    }
                    if(timer == 60 || timer == 30 || timer == 10 || timer <= 5 && timer >0) {
                        Bukkit.getOnlinePlayers().forEach(player ->
                                TitleApi.sendTitel(player, "§a" + timer));
                    }
                    if(timer == 0) {
                        IntStream.range(0, Masterbuilders.getInstance().getPlots().getPlots().size()).forEach(i ->{
                            Plot plot =  Masterbuilders.getInstance().getPlots().getPlots().get(i);
                            Player player = Masterbuilders.getInstance().getPlayerUtils().getPlayers().get(i);
                            plot.setPlayer(player);
                            plot.teleportPlayer();
                            Masterbuilders.getInstance().getPlayerUtils().setPlot(player, plot);
                                });


                    }
                }
            }
        }.runTaskTimer(Masterbuilders.getInstance(), 0,20);
    }

}

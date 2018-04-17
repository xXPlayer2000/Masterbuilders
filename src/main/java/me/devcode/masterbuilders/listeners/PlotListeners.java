package me.devcode.masterbuilders.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.devcode.masterbuilders.Masterbuilders;
import me.devcode.masterbuilders.utils.Plot;

public class PlotListeners implements Listener{

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(Masterbuilders.getInstance().getPlayerUtils().getPlot(e.getPlayer()) != null) {
            Plot plot = Masterbuilders.getInstance().getPlayerUtils().getPlot(e.getPlayer());
            if(!plot.isInPlot(e.getBlock().getLocation())) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("§cDu bist nicht in deinem Plot.");
            }else{
                e.getPlayer().sendMessage("Test");
            }
        }else{
            e.getPlayer().sendMessage("Testttt");
        }
    }

}

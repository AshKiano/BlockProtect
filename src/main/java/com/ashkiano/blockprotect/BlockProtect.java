package com.ashkiano.blockprotect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BlockProtect extends JavaPlugin implements Listener {

    private List<String> protectedBlocks;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        protectedBlocks = this.getConfig().getStringList("protected-blocks");
        getServer().getPluginManager().registerEvents(this, this);

        // Initialize Metrics for plugin analytics
        Metrics metrics = new Metrics(this, 19302);

        System.out.println("Thank you for using the BlockProtect plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://paypal.me/josefvyskocil");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (protectedBlocks.contains(event.getBlock().getType().toString())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You cannot break this block!");
        }
    }
}
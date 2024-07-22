package org.awaken.pickupmobs.handlers;

import org.awaken.pickupmobs.Pickupmobs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class MobsHandler implements Listener
{
    Entity carriedMob = null;
    public MobsHandler(Pickupmobs plugin)
    {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMobInteract(PlayerInteractEntityEvent event)
    {

        Entity entity = event.getRightClicked();
        if (checkMob(entity))
        {
            Bukkit.getLogger().info("Friendly mob is interacted with");
            carriedMob = entity;
            entity.remove();
            PlayerInventory inventory = event.getPlayer().getInventory();
            // TODO: Find a better way to lock player's inventory
            for (int i = 0; i < 9; i++)
            {
                event.getPlayer().getInventory().setItem(i, new ItemStack(Material.BARRIER));
                // TODO: Restoring player's hotbar
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        if (carriedMob != null)
        {
            event.setCancelled(true);
            // TODO: Placing the carried mob
        }
    }

    protected boolean checkMob(Entity mob)
    {
        // Checking if the mob is friendly
        return switch (mob.getType())
        {
            case COW, CHICKEN, PIG, SHEEP, RABBIT, HORSE, CAT, WOLF, FROG -> true;
            default -> false;
        };
    }
}

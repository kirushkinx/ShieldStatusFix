package ru.kirushkinx.shieldstatusfix;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ShieldStatusFix extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("ShieldStatusFix enabled");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player victim = (Player) event.getEntity();
        if (!victim.isBlocking()) return;

        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            ItemStack weapon = attacker.getInventory().getItemInMainHand();

            if (weapon != null && weapon.getType().name().endsWith("_AXE")) {
                sendShieldDisablePacket(victim);
            }
        }
    }

    private void sendShieldDisablePacket(Player player) {
        WrapperPlayServerEntityStatus packet = new WrapperPlayServerEntityStatus(
                player.getEntityId(),
                (byte) 30 // disable shield status
        );

        for (Player onlinePlayer : getServer().getOnlinePlayers()) {
            if (onlinePlayer.getWorld().equals(player.getWorld()) &&
                    onlinePlayer.getLocation().distance(player.getLocation()) <= 128) {
                PacketEvents.getAPI().getPlayerManager()
                        .sendPacket(onlinePlayer, packet);
            }
        }
    }
}

package bungeeglobalchat.bungeeglobalchat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeGlobalChat extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @Override
    public void onDisable() {
    }

    public void onChat(ChatEvent event) {
        if (!event.isCommand() && event.getSender() instanceof ProxiedPlayer) {
            String message = event.getMessage();
            String name = ((ProxiedPlayer) event.getSender()).getName();
            String serverName = ((ProxiedPlayer) event.getSender()).getServer().getInfo().getName();

            TextComponent formattedMessage = new TextComponent("[" + serverName + "] <" + name + "> :" + message);
            formattedMessage.setColor(ChatColor.GRAY);

            getProxy().broadcast(formattedMessage);

            // Cancel the chat event so that the message is not sent locally
            event.setCancelled(true);
        }
    }
}

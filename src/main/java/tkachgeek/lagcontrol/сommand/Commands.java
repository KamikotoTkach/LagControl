package tkachgeek.lagcontrol.сommand;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Commands {
  public static JavaPlugin plugin;

  public static void load(JavaPlugin plugin) {
    Commands.plugin = plugin;

    CommandWorker commandWorker = new CommandWorker(new save(), true);
    plugin.getCommand("LagControl").setExecutor(commandWorker);
    commandWorker.addCommand("top", new top());

    plugin.getCommand("LagControl").setTabCompleter(new CommandCompleter(commandWorker));

  }
}

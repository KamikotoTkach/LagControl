package tkachgeek.lagcontrol.config;

import org.bukkit.plugin.java.JavaPlugin;

public class MainCfg extends ConfigInstance {
  public MainCfg(String name, JavaPlugin plugin) {
    load(name, plugin);
  }
}

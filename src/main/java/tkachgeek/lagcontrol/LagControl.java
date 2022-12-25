package tkachgeek.lagcontrol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tkachgeek.lagcontrol.config.Cfg;
import tkachgeek.lagcontrol.сommand.Commands;

public final class LagControl extends JavaPlugin {

  @Override
  public void onEnable() {
    Commands.load(this);
    Cfg.load(this);
    Bukkit.getPluginManager().registerEvents(new EventListener(), this);

  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}

package tkachgeek.lagcontrol.сommand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tkachgeek.lagcontrol.Comparators;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class top implements CommandInterface {
  static Component space = Component.space();
  static int columnLen = 4;
  
  static String getColor(int primary, int entered) {
    if (primary == Math.abs(entered))
      if (entered < 0) return "#0C5DA5";
      else return "#FF4F00";
    return "#043A6B";
  }
  
  static String padAndFormatValue(double value) {
    return cutOrPad(formatValue(value));
  }
  
  static String formatValue(double value) {
    String suffix = "";
    if (value >= 1000000) {
      value /= 1000000.0;
      suffix = "M";
    } else if (value >= 1000) {
      value /= 1000.0;
      suffix = "K";
    }
    return Math.round(value) + suffix;
  }
  
  static String cutOrPad(String text) {
    return cutOrPad(text, columnLen);
  }
  
  static String cutOrPad(String text, int len) {
    if (text.length() > len) {
      return text.substring(0, len - 1) + "#";
    } else if (text.length() < len) {
      return "_".repeat(len - text.length()) + text;
    }
    return text;
  }
  
  @Override
  public HashMap<Integer, List<String>> getCompleter() {
    HashMap<Integer, List<String>> completer = new HashMap<>();
    completer.put(1, Arrays.asList("<Количество строк>", "5", "10", "15", "30", "45"));
    completer.put(2, Arrays.asList("<Сортировка по столбцу>", "1", "2", "3", "4", "5", "6", "7"));
    return completer;
  }
  
  @Override
  public boolean execute(CommandSender sender, Command command, String commandLabel, String[] args) {
    int column = 100;
    int length = 10;
    try {
      column = Integer.parseInt(args[2]);
    } catch (Exception ignored) {
    }
    
    try {
      length = Integer.parseInt(args[1]);
    } catch (Exception ignored) {
    }
    
    int currColumn = 1;
    AtomicInteger order = new AtomicInteger(1);
    
    Component num = Component.text(cutOrPad("№", 3))
       .color(TextColor.fromCSSHexString("#FFC200"));
    
    Component h0 = Component.text(cutOrPad("ALL"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (100 == column ? -100 : 100)))
       .hoverEvent(HoverEvent.showText(Component.text("Total values")))
       .color(TextColor.fromCSSHexString(getColor(100, column)));
    
    Component h1 = Component.text(cutOrPad("RT"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Redstone ticks")))
       .color(TextColor.fromCSSHexString(getColor(currColumn++, column)));
    
    Component h2 = Component.text(cutOrPad("PM"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Piston moves")))
       .color(TextColor.fromCSSHexString(getColor(currColumn++, column)));
    
    Component h3 = Component.text(cutOrPad("FB"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Falling blocks")))
       .color(TextColor.fromCSSHexString(getColor(currColumn++, column)));
    
    Component h4 = Component.text(cutOrPad("IM"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Item moves")))
       .color(TextColor.fromCSSHexString(getColor(currColumn++, column)));
    
    Component h5 = Component.text(cutOrPad("SE"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Spawned entities")))
       .color(TextColor.fromCSSHexString(getColor(currColumn++, column)));
    
    Component h6 = Component.text(cutOrPad("DT"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Dispenser ticks")))
       .color(TextColor.fromCSSHexString(getColor(currColumn++, column)));
    
    Component h7 = Component.text(cutOrPad("BC"))
       .clickEvent(ClickEvent.runCommand("/lc top " + length + " " + (currColumn == column ? -currColumn : currColumn)))
       .hoverEvent(HoverEvent.showText(Component.text("Blocks changed")))
       .color(TextColor.fromCSSHexString(getColor(currColumn, column)));
    
    sender.sendMessage(num.append(space).append(h0).append(space).append(h1).append(space).append(h2).append(space).append(h3).append(space).append(h4).append(space).append(h5).append(space).append(h6).append(space).append(h7));
    
    Comparators.getTop(length, column).forEach((key, value) -> {
      Component orderComponent = Component.text(cutOrPad(String.valueOf(order.getAndIncrement()), 3))
         .color(TextColor.fromCSSHexString("#FFC200"))
         .clickEvent(ClickEvent.runCommand("/tp " + key.getX() * 16 + " 100 " + key.getZ() * 16))
         .hoverEvent(HoverEvent.showText(Component.text("Tp " + key.getWorld().getName() + " X:" + key.getX() * 16 + " Z:" + key.getZ() * 16)));
      
      Component col0 = Component.text(padAndFormatValue(value.total))
         .color(TextColor.fromCSSHexString("#679FD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.total)));
      
      Component col1 = Component.text(padAndFormatValue(value.redstoneTicks))
         .color(TextColor.fromCSSHexString("#408DD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.redstoneTicks)));
      
      Component col2 = Component.text(padAndFormatValue(value.pistonMoves))
         .color(TextColor.fromCSSHexString("#679FD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.pistonMoves)));
      
      Component col3 = Component.text(padAndFormatValue(value.fallingBlocks))
         .color(TextColor.fromCSSHexString("#408DD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.fallingBlocks)));
      
      Component col4 = Component.text(padAndFormatValue(value.itemMoves))
         .color(TextColor.fromCSSHexString("#679FD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.itemMoves)));
      
      Component col5 = Component.text(padAndFormatValue(value.spawnedEntities))
         .color(TextColor.fromCSSHexString("#408DD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.spawnedEntities)));
      
      Component col6 = Component.text(padAndFormatValue(value.dispenseTick))
         .color(TextColor.fromCSSHexString("#679FD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.dispenseTick)));
      
      Component col7 = Component.text(padAndFormatValue(value.blockChanges))
         .color(TextColor.fromCSSHexString("#408DD2"))
         .hoverEvent(HoverEvent.showText(Component.text(value.blockChanges)));
      
      sender.sendMessage(
         orderComponent.append(space)
            .append(col0).append(space)
            .append(col1).append(space)
            .append(col2).append(space)
            .append(col3).append(space)
            .append(col4).append(space)
            .append(col5).append(space)
            .append(col6).append(space)
            .append(col7));
    });
    return true;
  }
}

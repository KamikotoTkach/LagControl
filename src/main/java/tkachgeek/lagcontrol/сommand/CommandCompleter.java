package tkachgeek.lagcontrol.сommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CommandCompleter implements TabCompleter {
  List<String> list;
  CommandWorker commandWorker;

  public CommandCompleter(CommandWorker commandWorker) {
    this.commandWorker = commandWorker;
    this.list = commandWorker.getCommands();
  }
  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (args.length == 1) return list;

    HashMap<Integer, List<String>> completer = commandWorker.commands.get(args[0].toLowerCase()).getCompleter();

      if(completer.containsKey(args.length-1)) {
        if(args[args.length-1].length()<1) return completer.get(args.length-1);
        List<String> filteredList = new ArrayList<>();

        for (String str : completer.get(args.length - 1)) {
          if(str.startsWith(args[args.length-1].toUpperCase())) {
            filteredList.add(str);
          }
        }
        return filteredList;
      }

    return new ArrayList<>();
  }
}

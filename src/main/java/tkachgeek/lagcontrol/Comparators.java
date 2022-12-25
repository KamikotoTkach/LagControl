package tkachgeek.lagcontrol;

import org.bukkit.Chunk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Comparators {
  public static HashMap<Integer, Comparator<ChunkData>> comparators = new HashMap<>();

  static {
    comparators.put(100, Comparator.comparingInt(o -> o.total));
    comparators.put(1, Comparator.comparingInt(o -> o.redstoneTicks));
    comparators.put(2, Comparator.comparingInt(o -> o.pistonMoves));
    comparators.put(3, Comparator.comparingInt(o -> o.fallingBlocks));
    comparators.put(4, Comparator.comparingInt(o -> o.itemMoves));
    comparators.put(5, Comparator.comparingInt(o -> o.spawnedEntities));
    comparators.put(6, Comparator.comparingInt(o -> o.dispenseTick));
    comparators.put(7, Comparator.comparingInt(o -> o.blockChanges));
  }

  public static HashMap<Chunk, ChunkData> getTop(int length, int sortColumn) {
    return ChunkDataWorker.chunks.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(sortColumn < 0 ? comparators.get(Math.abs(sortColumn)) : comparators.get(sortColumn).reversed()))
            .limit(length)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }
}

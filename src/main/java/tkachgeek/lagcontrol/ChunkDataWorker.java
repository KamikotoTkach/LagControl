package tkachgeek.lagcontrol;

import org.bukkit.Chunk;

import java.util.WeakHashMap;

public abstract class ChunkDataWorker {
  public static WeakHashMap<Chunk, ChunkData> chunks = new WeakHashMap<>();

  static void setInitialData(Chunk chunk) {
    if(!chunks.containsKey(chunk)) {
      chunks.put(chunk, new ChunkData());
    }
  }
  static void addRedstoneTick(Chunk chunk) {
    setInitialData(chunk);
    chunks.get(chunk).redstoneTicks += 1;
    chunks.get(chunk).total += 1;
  }
  static void addFallingBlock(Chunk chunk) {
    setInitialData(chunk);
    chunks.get(chunk).fallingBlocks += 1;
    chunks.get(chunk).total += 1;
  }
  static void addPistonMove(Chunk chunk) {
    setInitialData(chunk);
    chunks.get(chunk).pistonMoves += 1;
    chunks.get(chunk).total += 1;
  }
  static void addItemMove(Chunk chunk) {
    if(chunk == null) return;
    setInitialData(chunk);
    chunks.get(chunk).itemMoves += 1;
    chunks.get(chunk).total += 1;
  }
  static void addSpawnedEntity(Chunk chunk) {
    setInitialData(chunk);
    chunks.get(chunk).spawnedEntities += 1;
    chunks.get(chunk).total += 1;
  }
  static void addDispencerTick(Chunk chunk) {
    setInitialData(chunk);
    chunks.get(chunk).dispenseTick += 1;
    chunks.get(chunk).total += 1;
  }

  static void addBlockChange(Chunk chunk) {
    setInitialData(chunk);
    chunks.get(chunk).blockChanges += 1;
    chunks.get(chunk).total += 1;
  }
}

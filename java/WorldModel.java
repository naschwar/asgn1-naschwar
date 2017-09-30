import processing.core.PImage;

import java.util.*;

final class WorldModel
{
   private int numRows;
   private int numCols;
   private Background background[][];
   private Entity occupancy[][];
   private Set<Entity> entities;
   private static final int ORE_REACH = 1;


   public WorldModel(int numRows, int numCols, Background defaultBackground)
   {
      this.numRows = numRows;
      this.numCols = numCols;
      this.background = new Background[numRows][numCols];
      this.occupancy = new Entity[numRows][numCols];
      this.entities = new HashSet<>();

      for (int row = 0; row < numRows; row++)
      {
         Arrays.fill(this.background[row], defaultBackground);
      }
   }

   public int getNumRows(){
      return numRows;
   }

   public int getNumCols() {
      return numCols;
   }

   public Set<Entity> getEntities(){
      return entities;
   }


   public Optional<Entity> findNearest(Point pos,
                                       EntityKind kind)
   {
      List<Entity> ofType = new LinkedList<>();
      for (Entity entity : entities)
      {
         if (entity.getKind() == kind)
         {
            ofType.add(entity);
         }
      }
      return nearestEntity(ofType, pos);
   }


   private static Optional<Entity> nearestEntity(List<Entity> entities,
                                                Point pos)
   {
      if (entities.isEmpty())
      {
         return Optional.empty();
      }
      else
      {
         Entity nearest = entities.get(0);
         int nearestDistance = nearest.position.distanceSquared(pos);

         for (Entity other : entities)
         {
            int otherDistance = other.position.distanceSquared(pos);

            if (otherDistance < nearestDistance)
            {
               nearest = other;
               nearestDistance = otherDistance;
            }
         }

         return Optional.of(nearest);
      }
   }

   public Optional<Point> findOpenAround(Point pos)
   {
      for (int dy = -ORE_REACH; dy <= ORE_REACH; dy++)
      {
         for (int dx = -ORE_REACH; dx <= ORE_REACH; dx++)
         {
            Point newPt = new Point(pos.x + dx, pos.y + dy);
            if (withinBounds(newPt) &&
                    !isOccupied(newPt))
            {
               return Optional.of(newPt);
            }
         }
      }

      return Optional.empty();
   }

   public Optional<PImage> getBackgroundImage(Point pos)
   {
      if (withinBounds(pos))
      {
         return Optional.of(this.getBackgroundCell(pos).getCurrentImage());
      }
      else
      {
         return Optional.empty();
      }
   }

   public void moveEntity(Entity entity, Point pos)
   {
      Point oldPos = entity.position;
      if (withinBounds(pos) && !pos.equals(oldPos))
      {
         setOccupancyCell(oldPos, null);
         removeEntityAt(pos);
         setOccupancyCell(pos, entity);
         entity.position = pos;
      }
   }
   private void setBackgroundCell(Point pos,
                                        Background background)
   {
      this.background[pos.y][pos.x] = background;
   }

   public void setBackground(Point pos,
                             Background background)
   {
      if (withinBounds(pos))
      {
         setBackgroundCell(pos, background);
      }
   }
   private Entity getOccupancyCell(Point pos)
   {

      return occupancy[pos.y][pos.x];
   }

   public void removeEntity(Entity entity)
   {
      removeEntityAt(entity.position);
   }





   public void addEntity(Entity entity)
   {
      if (withinBounds(entity.position))
      {
         setOccupancyCell(entity.position, entity);
         entities.add(entity);
      }
   }

   private void removeEntityAt(Point pos)
   {
      if (withinBounds(pos) && getOccupancyCell(pos) != null)
      {
         Entity entity = getOccupancyCell(pos);

         /* this moves the entity just outside of the grid for
            debugging purposes */
         entity.position = new Point(-1, -1);
         entities.remove(entity);
         setOccupancyCell(pos, null);
      }
   }
   public Optional<Entity> getOccupant(Point pos)
   {
      if (isOccupied(pos))
      {
         return Optional.of(getOccupancyCell(pos));
      }
      else
      {
         return Optional.empty();
      }
   }
   public boolean isOccupied(Point pos)
   {
      return withinBounds(pos) &&
              getOccupancyCell(pos) != null;
   }
   private boolean withinBounds(Point pos)
   {
      return pos.y >= 0 && pos.y < numRows &&
              pos.x >= 0 && pos.x < numCols;
   }

   private void setOccupancyCell(Point pos,
                                Entity entity)
   {
      occupancy[pos.y][pos.x] = entity;
   }
   public void tryAddEntity(Entity entity)
   {
      if (isOccupied(entity.position))
      {
         // arguably the wrong type of exception, but we are not
         // defining our own exceptions yet
         throw new IllegalArgumentException("position occupied");
      }

      addEntity(entity);
   }

   private Background getBackgroundCell(Point pos)
   {
      return background[pos.y][pos.x];
   }


}

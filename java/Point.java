import processing.core.PImage;

import java.util.List;
import java.util.Optional;


final class Point
{
   public final int x;
   public final int y;

   public static final String QUAKE_ID = "quake";
   public static final int QUAKE_ACTION_PERIOD = 1100;
   public static final int QUAKE_ANIMATION_PERIOD = 100;


   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }


   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)
   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y;
   }

   public int hashCode()
   {
      int result = 17;
      result = result * 31 + x;
      result = result * 31 + y;
      return result;
   }
   public boolean adjacent(Point p2)
   {
      return (x == p2.x && Math.abs(y - p2.y) == 1) ||
              (y == p2.y && Math.abs(x - p2.x) == 1);
   }
   public int distanceSquared(Point p2)
   {
      int deltaX = x - p2.x;
      int deltaY = y - p2.y;

      return deltaX * deltaX + deltaY * deltaY;
   }
   private Optional<Entity> nearestEntity(List<Entity> entities,
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
   public Entity createQuake(List<PImage> images)
   {
      return new Entity(EntityKind.QUAKE, QUAKE_ID, this, images,
              0, 0, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
   }
}

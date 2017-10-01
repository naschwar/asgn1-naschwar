import java.util.List;
import processing.core.PImage;

final class Background
{
   private final String id;
   private final List<PImage> images;
   private int imageIndex;



   public Background(String id, List<PImage> images)
   {
      this.id = id;
      this.images = images;
   }

   private Entity createOre(Point position, int actionPeriod,
                                  List<PImage> images)
   {
      return new Entity(EntityKind.ORE, id, position, images, 0, 0,
              actionPeriod, 0);
   }
   public PImage getCurrentImage()
   {
      if (this instanceof Background)
      {
         return ((Background)this).images
                 .get(((Background)this).imageIndex);
      }
      else
      {
         throw new UnsupportedOperationException(
                 String.format("getCurrentImage not supported for %s",
                         this));
      }
   }







}

import java.util.List;
import processing.core.PImage;

final class Background
{
   public String id;
   public List<PImage> images;
   public int imageIndex;



   public Background(String id, List<PImage> images)
   {
      this.id = id;
      this.images = images;
   }

   public Entity createOre(Point position, int actionPeriod,
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

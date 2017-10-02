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

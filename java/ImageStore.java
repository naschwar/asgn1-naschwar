import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;

final class ImageStore
{
   private Map<String, List<PImage>> images;
   private List<PImage> defaultImages;
   public static final int COLOR_MASK = 0xffffff;
   ImageStore(PImage defaultImage)
   {
      this.images = new HashMap<>();
      defaultImages = new LinkedList<>();
      defaultImages.add(defaultImage);
   }
   public List<PImage> getImageList(String key)
   {
      return images.getOrDefault(key, defaultImages);
   }
   private static void setAlpha(PImage img, int maskColor, int alpha)
   {
      int alphaValue = alpha << 24;
      int nonAlpha = maskColor & COLOR_MASK;
      img.format = PApplet.ARGB;
      img.loadPixels();
      for (int i = 0; i < img.pixels.length; i++)
      {
         if ((img.pixels[i] & COLOR_MASK) == nonAlpha)
         {
            img.pixels[i] = alphaValue | nonAlpha;
         }
      }
      img.updatePixels();
   }

   public Map<String, List<PImage>> getImages(){
      return images;
   }
}

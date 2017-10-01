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


   public Map<String, List<PImage>> getImages(){
      return images;
   }
}

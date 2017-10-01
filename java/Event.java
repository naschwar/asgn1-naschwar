import java.util.LinkedList;
import java.util.List;

final class Event
{
   private final Action action;
   private final long time;
   private final Entity entity;


   public Event(Action action, long time, Entity entity)
   {
      this.action = action;
      this.time = time;
      this.entity = entity;
   }
   public long getTime(){
      return time;
   }

   public Action getAction(){
      return action;
   }

   public Entity getEntity(){
      return entity;

}

   }






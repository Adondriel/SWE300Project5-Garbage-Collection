

import java.util.Date;

public class SavePoint {
	   private final String content;
	   private Date actionTime;

	   public SavePoint(String content) {
	      this.content = content;
	      this.actionTime = new Date();
	   }

	   @Override
	   public String toString() {
	      return SavePoint.class.getSimpleName() 
	             + " [content : " + this.content + ", actionTime : " + this.actionTime;
	   }
}

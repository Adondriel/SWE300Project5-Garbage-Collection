

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class weakGC {
	public static void main(String[] args) {

	      List<WeakReference<SavePoint>> savePoints = new ArrayList<WeakReference<SavePoint>>();
	      WeakReference<List<WeakReference<SavePoint>>> weakList = new WeakReference<List<WeakReference<SavePoint>>>(
	            savePoints);
	      
	      List<SavePoint> hardRefSavePoints = new ArrayList<SavePoint>();
	      

	      SavePoint savePoint = null;
	      Set<SavePoint> savePointsToHold = new LinkedHashSet<SavePoint>();
	      for (int i = 0; i < 5; i++) {
	         savePoint = new SavePoint("This is savepoint No " + i);
	         SavePoint savePoint2 = new SavePoint("This is savepoint No " + i);

	         weakList.get().add(new WeakReference<SavePoint>(savePoint));
	         hardRefSavePoints.add(savePoint2);
	      }
	      // the last savepoint is a strong reference
	      savePointsToHold.add(savePoint);

	      final Runtime RUNTIME = Runtime.getRuntime();
	      System.out.println("total memory : " + RUNTIME.totalMemory() + ", free memory : " + RUNTIME.freeMemory());

	      for (int i = 0; i < 5; i++) {
		         System.out.println("Weak List Before GC Value at location " + i + " is " + weakList.get().get(i).get());
		         System.out.println("Strong List Before GC Value at location " + i + " is " + hardRefSavePoints.get(i));

		      }
	      System.out.println("Calling GC...");
	      RUNTIME.gc();
	      System.out.println("total memory : " + RUNTIME.totalMemory() + ", free memory : " + RUNTIME.freeMemory());
	      for (int i = 0; i < 5; i++) {
	         System.out.println("Weak List After GC Value at location " + i + " is " + weakList.get().get(i).get());
	         System.out.println("Strong List After GC Value at location " + i + " is " + hardRefSavePoints.get(i));
	      }
	      int end = 0;
	   }
}

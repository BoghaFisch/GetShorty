import java.util.PriorityQueue;


public class Dungeon {

	private PriorityQueue<Intersection> intersections;
	private Intersection[] intersectionMap;
	
	Dungeon(Intersection[] intersectionMap)
	{
		this.intersectionMap = intersectionMap;
	}
	/**
	 * 
	 * Uses a modified version of Dijkstras algorithm (multiplication instead of addition)
	 * to calculate the largest possible size at the last intersection.
	 * 
	 * @return The largest possible size at the last intersection.
	 */
	public float getMinimumShrink()
	{
		intersections = new PriorityQueue<Intersection>();
		Intersection neighbour;
		float altSize;
		
		Intersection v = intersectionMap[0];
		intersections.add(v);
		
		while (!intersections.isEmpty())
		{
			v = intersections.poll();
			for (Corridor u : v.getCorridors())
			{
				neighbour = u.getOtherIntersection(v);
				altSize = v.getCurrentSize()*u.getFactor();
				
				if (altSize > neighbour.getCurrentSize())
				{
					neighbour.setCurrentSize(altSize);
					intersections.add(neighbour);
				}
			}
		}
		float sizeOfLastIntersection = intersectionMap[intersectionMap.length - 1].getCurrentSize();
		return sizeOfLastIntersection;
	}
}

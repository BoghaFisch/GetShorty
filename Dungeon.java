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
		
		// Pick starting node
		Intersection v = intersectionMap[0];
		
		// Add to PriorityQueue
		intersections.add(v);
		
		// While there are still intersections left to visit
		while (!intersections.isEmpty())
		{
			// Pick the one with smallest distance to the starting intersection
			v = intersections.poll();

			// For each neighbour, calculate new size.
			for (Corridor u : v.getCorridors())
			{
				neighbour = u.getOtherIntersection(v);
				altSize = v.getCurrentSize()*u.getFactor();
				
				// Update size and path if better than old.
				if (altSize > neighbour.getCurrentSize())
				{
					neighbour.setCurrentSize(altSize);
					intersections.add(neighbour);
				}
			}
		}
		// Return the largest possible size for when passing the last intersection
		float sizeOfLastIntersection = intersectionMap[intersectionMap.length - 1].getCurrentSize();
		return sizeOfLastIntersection;
	}
}

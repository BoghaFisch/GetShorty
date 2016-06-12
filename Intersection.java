import java.util.ArrayList;
import java.util.HashMap;


public class Intersection implements Comparable<Intersection> {

	private int nr;
	private ArrayList<Corridor> corridors;
	/**
	 * The Intersection from which the currently best route to this intersection 
	 * comes from.
	 */
	private int parent;
	
	/**
	 * Largest possible size at this intersection, with the current knowledge.
	 * Measured in percent of starting size.
	 */
	private float currentSize;
	
	Intersection(int nr, float initialSize)
	{
		this.nr = nr;
		corridors = new ArrayList<Corridor>();
		currentSize = initialSize;
	}
	public int getNr()
	{
		return nr;
	}
	public float getCurrentSize()
	{
		return currentSize;
	}
	public void setCurrentSize(float currentSize)
	{
		this.currentSize = currentSize;
	}
	public int compareTo(Intersection other)
	{
		return (currentSize > other.getCurrentSize()) ? -1 : 1;
	}
	public void addCorridor(Corridor corr)
	{
		corridors.add(corr);
	}
	public ArrayList<Corridor> getCorridors()
	{
		return corridors;
	}
}

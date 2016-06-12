
public class Corridor {

	private Intersection from, to;
	private float factor;
	
	Corridor(Intersection from, Intersection to, float factor)
	{
		this.from = from;
		this.to = to;
		this.factor = factor;
	}
	public Intersection getOtherIntersection(Intersection thisIntersection)
	{
		return (thisIntersection.getNr()==from.getNr()) ? to : from;
	}
	public float getFactor()
	{
		return factor;
	}
}

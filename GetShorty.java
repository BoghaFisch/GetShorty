import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;


public class GetShorty {
	/**
	 * Reads the input and returns the set of dungeons to escape.
	 * @return The set of dungeons to escape
	 * @throws IOException
	 */
	public static ArrayList<Dungeon> readInput() throws IOException
	{
		ArrayList<Dungeon> dungeons = new ArrayList<Dungeon>();
		Corridor corr;
		Intersection[] intersections;
		boolean inputDone = false;
		String line = "";
		String[] lineComponents;
		int numOfIntersec, numOfCorr;
		int from, to;
		float f;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		// While there are dungeons left to create, loop
		while (!inputDone)
		{
			// Read header
			line = br.readLine();
			if (line.equals("0 0"))
			{
				// If this was the last line -> break
				break;
			}
			else
			{
				// Else read header fields
				lineComponents = line.split(" ");
				numOfIntersec = Integer.parseInt(lineComponents[0]);
				numOfCorr = Integer.parseInt(lineComponents[1]);
			}
			// Create Intersections
			intersections = new Intersection[numOfIntersec];
			intersections[0] = new Intersection(0, 1);
			for (int i = 1; i < numOfIntersec; i++)
			{
				intersections[i] = new Intersection(i, Float.NEGATIVE_INFINITY);
			}
			
			// Create Corridors. A corridor is represented as two intersections being neighbours.
			for (int i = 0; i < numOfCorr; i++)
			{
				lineComponents = br.readLine().split(" ");
				from = Integer.parseInt(lineComponents[0]);
				to = Integer.parseInt(lineComponents[1]);
				f = Float.parseFloat(lineComponents[2]);
				
				// Undirected. Possible to move both ways in a corridor.
				corr = new Corridor(intersections[from], intersections[to], f);
				intersections[from].addCorridor(corr);
				intersections[to].addCorridor(corr);
			}
			dungeons.add(new Dungeon(intersections));
		}
		return dungeons;
	}
	
	public static void main(String[] args)
	{
		try 
		{
			PrintWriter writer = new PrintWriter(System.out);
			ArrayList<Dungeon> dungeons = readInput();
			
			for (Dungeon d : dungeons)
			{
				writer.printf(Locale.US, "%.4f%n", d.getMinimumShrink());
				writer.flush();
//				System.out.format(Locale.US, "%.4f%n", d.getMinimumShrink());
			}

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}

package nqueens;

import java.util.ArrayList;

public class QueensSolver2D
{

	public static ArrayList<ArrayList<Integer>> generateSolutions(int m, int n)
	{
		ArrayList<ArrayList<Integer>> globalSolutions = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < n; i++)
		{
			permute(i, m, n, new ArrayList<Integer>(), globalSolutions);
		}
		return globalSolutions;
	}

	private static void permute(int i, int m, int n,
			ArrayList<Integer> partialSolution,
			ArrayList<ArrayList<Integer>> globalSolutions)
	{
		partialSolution.add(i);
		
		if (partialSolution.size() == m)
		{
			globalSolutions.add(partialSolution);
			return;
		}

		for (int j = 0; j < n; j++)
		{
			if (!partialSolution.contains(j) && isValidOrder(j, partialSolution))
				permute(j, m, n, (ArrayList<Integer>) partialSolution.clone(),
						globalSolutions);
		}
	}

	public static boolean isValidOrder(int i, ArrayList<Integer> partial)
	{
		boolean natrualOrder = naturalOrderCheck(i, partial);

		return !natrualOrder;
	}

	private static boolean naturalOrderCheck(Integer currentNumber,
			ArrayList<Integer> partial)
	{
		for (int i = 0; i < partial.size(); i++)
		{
			if (Math.abs(partial.size() - i) == Math.abs(currentNumber
					- partial.get(i)))
			{
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args)
	{
		int m = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);

		if (m > n)
		{
			int temp = m;
			m = n;
			n = temp;
		}

		ArrayList<ArrayList<Integer>> globalSolutions = generateSolutions(m, n);
		
		long i = 1;
		for (ArrayList<Integer> solution : globalSolutions)
		{
			System.out.println("#" + i++ + "> " + solution);
		}
	}
	
}

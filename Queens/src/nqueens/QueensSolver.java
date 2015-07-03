package nqueens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QueensSolver
{

	private static long visitedNodesCounter;
	private static long allNodesCounter;

	public static ArrayList<ArrayList<Integer>> generateSolutions(int n)
	{
		ArrayList<ArrayList<Integer>> globalSolutions = new ArrayList<>();

		for (int i = 0; i < n; i++)
		{
			permuteWithConstraints(i, n, new ArrayList<Integer>(),
					globalSolutions);
		}

		return globalSolutions;
	}

	private static void permuteWithConstraints(int i, int n,
			ArrayList<Integer> partialSolution,
			ArrayList<ArrayList<Integer>> globalSolutions)
	{
		partialSolution.add(i);
		++visitedNodesCounter;

		if (partialSolution.size() == n)
		{
			globalSolutions.add(partialSolution);
			return;
		}

		for (int j = 0; j < n; j++)
		{
			if (!partialSolution.contains(j)
					&& isValidOrder(j, partialSolution))
				permuteWithConstraints(j, n,
						(ArrayList<Integer>) partialSolution.clone(),
						globalSolutions);
		}
	}

	private static boolean isValidOrder(int i, ArrayList<Integer> partial)
	{
		boolean natrualOrder = checkNaturalOrder(i, partial);

		return !natrualOrder;
	}

	private static boolean checkNaturalOrder(Integer currentNumber,
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

	public static ArrayList<ArrayList<Integer>> generatePermutations(int n)
	{
		ArrayList<ArrayList<Integer>> globalSolutions = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < n; i++)
		{
			permute(i, n, new ArrayList<Integer>(), globalSolutions);
		}
		return globalSolutions;
	}

	private static void permute(int i, int n, ArrayList<Integer> partial,
			ArrayList<ArrayList<Integer>> globalSolutions)
	{
		partial.add(i);
		allNodesCounter++;

		if (partial.size() == n)
		{
			globalSolutions.add(partial);
			return;
		}

		for (int j = 0; j < n; j++)
		{
			if (!partial.contains(j))
				permute(j, n, (ArrayList<Integer>) partial.clone(),
						globalSolutions);
		}
	}

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		int n = Integer.parseInt(args[0]);
		ArrayList<ArrayList<Integer>> globalSolutions = generateSolutions(n);
		generatePermutations(n);
		long end = System.currentTimeMillis();

		long i = 1;
		for (ArrayList<Integer> solution : globalSolutions)
		{
			System.out.println("#" + i++ + "> " + solution);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
		System.out.println("\nTime to generate solutions = "
				+ sdf.format(new Date(end - start)));
		System.out.println("For n = " + n + ", Number of solutions = "
				+ globalSolutions.size());
		System.out.println("For n = " + n + ", visitedNodesCounter = "
				+ visitedNodesCounter);
		System.out.println("For n = " + n + ", allNodesCounter = "
				+ allNodesCounter);
		System.out.println("For n = " + n + ", pruning rate = "
				+ (allNodesCounter - visitedNodesCounter)
				/ (double) allNodesCounter);
	}

}

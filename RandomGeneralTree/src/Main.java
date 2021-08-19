// Created by Mehmet Bahadir Askin on 30/04/2021

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		// parameters
		int nodeCount = 0;
		int maxBranchFactor = 0;
		int minKeySize = 0;
		int maxKeySize = 0;
		
		// other variables used
		Scanner scanner = new Scanner(System.in);
		String out = "";
		
		// getting parameters from user. all of them must be more than 0 and "maxKeySize" cannot be less than "minKeySize"
		System.out.println("Enter node count of the tree:");
		nodeCount = scanner.nextInt();
		System.out.println("Enter maximum branching factor of the tree:");
		maxBranchFactor = scanner.nextInt();
		System.out.println("Enter minimum key size of the tree:");
		minKeySize = scanner.nextInt();
		System.out.println("Enter maximum key size of the tree:");
		maxKeySize = scanner.nextInt();
		
		// generate random tree
		GeneralTreeNode root = GeneralTreeMethods.treeGenerator(nodeCount, maxKeySize, minKeySize, maxBranchFactor);
		
		// print the tree and clean unnecessary commas to fit in JSON array format
		out = GeneralTreeMethods.printTree(root);
		out = GeneralTreeMethods.outputCleaner(out);
		GeneralTreeMethods.saveTree("out.txt", out);
		scanner.close();
	}
}

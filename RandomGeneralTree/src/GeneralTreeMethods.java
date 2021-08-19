// Created by Mehmet Bahadir Askin on 30/04/2021

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GeneralTreeMethods 
{
	// initialize tree with the root named "myroot"
	private static GeneralTree initializeTree() 
	{
		GeneralTreeNode root = new GeneralTreeNode("myroot", new ArrayList<GeneralTreeNode>());
		GeneralTree tree = new GeneralTree(root);
		return tree;
	}
	
	// add given node to tree recursively
	private static void addNode(GeneralTreeNode root, String key, int maxBranchFactor) 
	{
		// 1st base case, if it is a leaf node, add node as first child of the subtree
		if (root.getChilds().size() == 0) 
		{
			root.getChilds().add(0, new GeneralTreeNode(key, new ArrayList<GeneralTreeNode>()));
			return;
		}
		
		// if child count of subtree is between 0 and max branch factor
		else if (root.getChilds().size() > 0 && root.getChilds().size() < maxBranchFactor) 
		{
			// select one child with the index between 0 and size of the subtree randomly
			int random = randomNumber(root.getChilds().size() + 1, 0);
			
			// 2nd base case, if the empty node is selected, create a new node and add new node to this position
			if(random == root.getChilds().size()) 
			{
				root.getChilds().add(random, new GeneralTreeNode(key, new ArrayList<GeneralTreeNode>()));
				return;
			}
			
			// if a non-empty node is selected forward to this node recursively
			else 
			{
				addNode(root.getChilds().get(random), key, maxBranchFactor);
			}
		}
		
		// if the subtree is full, select one child randomly and forward to this node recursively
		else if (root.getChilds().size() == maxBranchFactor) 
		{
			int random = randomNumber(maxBranchFactor, 0);
			addNode(root.getChilds().get(random), key, maxBranchFactor);
		}
	}
	
	// random number generator in the range [min, max)
	private static int randomNumber(int max, int min) 
	{
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	// random key generator
	private static String randomKey(int keySize) 
	{
		String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < keySize) 
        { 	
        	// length of the random key.
            int index = (int) (random.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	// print tree in JSON array format (preorder) recursively
	public static String printTree(GeneralTreeNode root) 
	{
		int i = 0;
		String result = "";
		
		// base case, if a leaf node, return the key
		if (root.getChilds().size() == 0) 
		{
			return ("\"" + root.getKey() + "\",");
		}
		
		// if not a leaf node, add key to result string
		result = result + ("[\"" + root.getKey() + "\",");
		
		// then traverse all subtrees recursively
		while (i < root.getChilds().size()) 
		{
			result = result + printTree(root.getChilds().get(i));
			i++;
		}
		result = result + ("],");
		return result;
	}

	// clean unnecessary commas from the output
	public static String outputCleaner(String out) 
	{
		out = out.replace(",]", "]");
		out = out.substring(0, out.length()-1);
		return out;
	}
	
	public static GeneralTreeNode treeGenerator(int nodeCount, int maxKeySize, int minKeySize, int maxBranchFactor) 
	{
		int keySize = 0;
		String randomKey = "";
		ArrayList<String> keyList = new ArrayList<String>();
		
		// tree initialization
		GeneralTree myGeneralTree = initializeTree();
		GeneralTreeNode root = myGeneralTree.getRoot();
		
		// add given number of nodes
		for (int i = 1; i <= nodeCount; i++) 
		{
			// generate individual random keys between maxKeySize and minKeySize
			do 
			{
				keySize = randomNumber(maxKeySize + 1, minKeySize);
				randomKey = randomKey(keySize);
			} while(keyList.contains(randomKey));
			keyList.add(randomKey);
			
			// call addNode method to add a node with a "randomKey" to a random position in the tree
			addNode(root, randomKey, maxBranchFactor);
		}
		return root;
	}
	
	public static void saveTree(String filePath, String out) 
	{
		try
		{
			FileWriter myWriter = new FileWriter(filePath);
			System.out.println("A random general tree was generated in JSON array format and saved in: " + filePath);
			myWriter.write(out);
			myWriter.close();
		} 
		catch (IOException e)
		{
		    System.out.println("An error occurred while saving the tree in: " + filePath);
		    e.printStackTrace();
		}
	}
}

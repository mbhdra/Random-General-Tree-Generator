// Created by Mehmet Bahadir Askin on 30/04/2021

public class GeneralTree 
{
	private GeneralTreeNode root;
	
	public GeneralTree(GeneralTreeNode root)
	{
		super();
		this.root = root;
	}

	public GeneralTreeNode getRoot() 
	{
		return root;
	}

	public void setRoot(GeneralTreeNode root) 
	{
		this.root = root;
	}
}

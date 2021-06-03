// Created by Mehmet Bahadir Askin on 30/04/2021

import java.util.ArrayList;

public class GeneralTreeNode 
{
	private String key;
	ArrayList<GeneralTreeNode> childs;
	
	public GeneralTreeNode(String key, ArrayList<GeneralTreeNode> childs) 
	{
		super();
		this.key = key;
		this.childs = childs;
	}

	public String getKey() 
	{
		return key;
	}

	public void setKey(String key) 
	{
		this.key = key;
	}

	public ArrayList<GeneralTreeNode> getChilds() 
	{
		return childs;
	}

	public void setChilds(ArrayList<GeneralTreeNode> childs) 
	{
		this.childs = childs;
	}
}

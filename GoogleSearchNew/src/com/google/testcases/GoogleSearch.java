package com.google.testcases;


import org.testng.annotations.Test;

import com.google.wrappers.GenericWrappers;

public class GoogleSearch extends GenericWrappers {

	public static void main(String args[]) {
		GoogleSearch GS = new GoogleSearch();
		GS.GSearch();
	}
	
	@Test
	public void GSearch() {	
		String[][] SearchData = getData();
		for(int row =0 ; row < SearchData.length; ++row)
		{
			 for(int column =0; column<SearchData[row].length;++column)
			 {
				invokeApp("Chrome", "https://www.google.com");
			    System.out.print(SearchData[row][column]);		
				enterByXpath("//*[@id=\'tsf\']/div[2]/div/div[1]/div/div[1]/input", SearchData[row][column]);
				clickByXpath("//*[@id=\'tsf\']/div[2]/div/div[3]/center/input[1]");
				closeBrowser();
				closeAllBrowsers();
			 }
		}
		
	}
}

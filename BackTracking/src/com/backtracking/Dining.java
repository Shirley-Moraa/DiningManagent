package com.backtracking;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Dining {
	public static int counter = 0;
	public static int glutenCounter = 0;
	public static int peanutCounter = 0;
	public static int foodCostPerRecipe = 0;
	
	// our output - return a budget under $100 with 3 recipes containing one non-gluten and one non - peanut
	public static List<List<String>> variables(ArrayList<Integer> budget, ArrayList<String> nutrition, List<Recipes> recipes){
    	// create a list of lists to form an arrayList
 
    	List<List<String>> result = new ArrayList<>();
    	List<List<Integer>> partialRes = new ArrayList<>();
    	
        findSol( budget, nutrition,  recipes, partialRes, result);
    	System.out.println("######################### RESULT ##################################");
	    System.out.println(partialRes);
    	System.out.println(result);
    	System.out.println("*************************************************************************");
    	return result;
    }
	
	public static boolean isValid(Integer budgetElem, String nutElem, Recipes recipes) {
		// check if the values entered do not conflict with the constraints,
		// true is, doesn't break the constraints

		if (budgetElem <= 100 && counter < 3) {
			if (nutElem.equals("Non-gluten") || nutElem.equals("Non-protein")) {
				if (nutElem.equals("Non-gluten")) {
					glutenCounter++;
				} else {
					if (nutElem.equals("Non-protein")) {
						peanutCounter++;
					}
				}
				// calculate the cost of food till now
				// foodCostPerRecipe = Recipe.

			}
			//System.out.println("Full recipe:" + Recipe);

			// add code to remove recipe if there is already a recipe gluten and peanut free

			System.out.println(budgetElem + " :  " + nutElem + recipes + "Value of counter " + counter);
			// increment counter
			counter = counter + 1;
			return true;
		}
		return false;
	}
	
    
	public static List<String> findSol(ArrayList<Integer>budget,
			ArrayList<String> nutrition,
			List<Recipes> recipes, 
			List<List<Integer>> partialRes,
			List<List<String>> result) {
		
		 if(budget == null || recipes == null) {
			 System.out.println("cannot compute! please enter the required variables");
			 // if we find our solution add it to result and display solution,exit the program
		 }else {
			 //Budget.size() == bSize && nutrition.size() == nSize && Recipe.size() == nRecipe
			 if(budget.size() == 0 ) {
				 System.out.println("We have reached the end of each variable");
		
			 }else {
				 
				 /*
	//==========================================================================================================			 
				 for(int i = 0; i < budget.size(); i++) {
					 for(int j= 0; j < nutrition.size(); j ++) {
						 for(int k = 0; k < recipes.size() ;k++) {
							partialRes.add(Integer.toString(budget.get(i)));
							partialRes.add(nutrition.get(j));
							//partialRes.add(recipes.get(k));
							
							 result.add(new ArrayList<>(partialRes));
							// System.out.println("Partial res: " + partialRes);
							 if(isValid(budget.get(i),nutrition.get(j),recipes.get(k))) {
								// findSol( Budget, nutrition, Recipe, partialRes, result );
							 }
							 partialRes.remove(k);
						 }
						 partialRes.remove(j);
					 }
					 partialRes.remove(i);
				 }
				 
//==================================================================================================
		 	*/	 	
		   // try and create combinations of the recipes , then pass them to isValid(), to check constraint satisfaction
                    for(int i=0 ; i < recipes.size(); i++) {
                    	System.out.println(recipes.get(i).display());
                    }
			// get all possible combinations for the recipes	 
                    List<List<Integer>> recCombinations = combine(5,3); 
                    System.out.println(recCombinations);
            
            // loop through each combination first then;        
            // send them to isValid function to check if cost of each < $100 and contains(one non-gluten ,1 non-peanut)        
			for(int n = 0; n < recCombinations.size(); n ++) {
				 if(isValid(recCombinations.get(n),recipes)){
						System.out.println("*********** BEFORE *********");
					    System.out.println(partialRes);
					    partialRes.add(recCombinations.get(n));
					    System.out.println(partialRes);
					    for(int p = 0 ; p < partialRes.size(); p++) {
					    	// how do we add three recipes as one option using the numbers?
					    	
					    }
	
					    
						System.out.println("Does not violate the constraints: ");
						//result.add(new ArrayList<>(partialRes));
						//look for a way to return the combinations or the indexes of the recipes;
					} else {
						System.out.println("cannot be uploaded,/e43Z does not meet nutritional needs");
					}
			}
                   
//---------------------------------------------------------------------------------------------------------		 		 
			 }
		 }
		 
		// create function to check if its valid
		// how do we go through all the domains of our variables?, if invalid we backtrack
		
		return nutrition;
	}
	
	private static boolean isValid(List<Integer> list, List<Recipes> recipes) {
		// TODO Auto-generated method stub
		int total = 0; // gets cost of each combination
		int nonP = 0;
		int nonG = 0;
		
		System.out.println("\n ************************Combinations*****************************");
		System.out.println(list);
		
		for(int i = 0 ; i<list.size(); i++) {
			  int current = list.get(i);
			  System.out.println(recipes.get(current-1).display());
			// get cost of each and add the total, should be less than $100
			  total += recipes.get(current-1).findCost();
		      System.out.println(total);
		      
		   // check if it meets the nutrition value
		    //  System.out.println("N:  " + recipes.get(current-1).display());
		 // create a temp variable to hold the recipe
		    List<String> temp = recipes.get(current-1).getIngredients();
		  //  System.out.println("store the temporary: " +temp);
		    
					if(temp.contains("non-gluten")  ) {
					    	nonG++;		
					 }else {
						 if(temp.contains("non-peanut")) {
							 nonP++;
						 }
					 }   
		} 
		// check the cost
		if(total < 100) {
		    	 System.out.println("\ncost is below 100---> " + total);
			     // return true;
		    	 // check if it meets the nutrition value
                  if((nonG == 1) && nonP == 1) { 
                	  System.out.println("\nBalanced diet\n");
                	  return true;
                  }
		     }else {
		    	 System.out.println("ABOVE 100!!!!!!!!!!!!!!!!!!:--> " + total); 
		    	
				
			}
		
		return false;
	}

	public static List<List<Integer>> combine(int n, int k) {
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = combine(n - 1, k - 1);
        result.forEach(e -> e.add(n));
        result.addAll(combine(n - 1, k));
        return result;
    }
	
	public static void main(String[] args) {
	ArrayList<Integer> Budget = new ArrayList<>();
	ArrayList<String> Nutrition = new ArrayList<>();
    List<Recipes> recipeList = new ArrayList<Recipes>();
    
    List<String> ingredientsRec1 = new ArrayList<>();
    ingredientsRec1.add("Onions");
    ingredientsRec1.add("Tomatoes");
    ingredientsRec1.add("beans");
    ingredientsRec1.add("non-gluten");
    Recipes newRec = new Recipes(ingredientsRec1, 25, "Recipe One");
    recipeList.add(newRec);
 
    List<String> ingredientsRec2 = new ArrayList<>();
    ingredientsRec2.add("Watermelon"); 
    ingredientsRec2.add("Corn"); 
    ingredientsRec2.add("chickpeas"); 
    ingredientsRec2.add("non-peanut");
    Recipes newingredientsRec2 = new Recipes(ingredientsRec2, 35, "Recipe Two");
    recipeList.add(newingredientsRec2);
       
    List<String> ingredientsRec3 = new ArrayList<>();
    ingredientsRec3.add("meat");
    ingredientsRec3.add("rice");
    ingredientsRec3.add("veggies");
    Recipes newRec3 = new Recipes(ingredientsRec3, 41, "Recipe Three");
    recipeList.add(newRec3);
     
    List<String> ingredientsRec4 = new ArrayList<>();
    ingredientsRec4.add("Pears");
    ingredientsRec4.add("Turkey");
    ingredientsRec4.add("maize");
    Recipes newRec4 = new Recipes(ingredientsRec4, 9, "Recipe Four");
    recipeList.add(newRec4);
    
    List<String> Rec5 = new ArrayList<>();
    Rec5.add("Milk");
    Rec5.add("Potatoes");
    Rec5.add("peas");
    Recipes newRec5 = new Recipes(Rec5, 10, "Recipe five");
    recipeList.add(newRec5);

 
	// values for budget
	Budget.add(100);
	
	//values for nutrition
	Nutrition.add("non-gluten");
	Nutrition.add("non-peanut");

	// call the function
	List<List<String>> newList = variables(Budget, Nutrition, recipeList);
	}

}

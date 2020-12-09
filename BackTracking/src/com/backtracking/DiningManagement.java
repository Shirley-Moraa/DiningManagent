package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DiningManagement {
	
    //METHOD 1 Key Components to consider
	public static List<List<String>> keyComponents(ArrayList<Integer> budget, ArrayList<String> nutrition, List<Recipes> recipes){
    	
		// create a list of lists to form an arrayList
    	List<List<String>> result = new ArrayList<>();
    	List<List<Integer>> partialRes = new ArrayList<>();
    	
       result =  findSolution( budget, nutrition,  recipes, partialRes, result);
    	System.out.println("######################### RESULT ##################################");
	    System.out.println(partialRes);
    	System.out.println(result);
    	System.out.println("*************************************************************************");
    	return result;
    }
	
	//METHOD 2 : isValid(), check if the combinations of recipes meets the constraints(<3, has non-gluten & non-protein)
	private static boolean isValid(List<Integer> list, List<Recipes> recipes) {
		// TODO Auto-generated method stub
		int total = 0; // gets cost of each combination, 
		int nonProtein = 0; 
		int nonGluten = 0;

		System.out.println("\n ************************Combinations*****************************");
		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {
			int current = list.get(i);
			System.out.println(recipes.get(current - 1).display());
			//System.out.println(recipes.get(current - 1).getTitle());
			// get cost of each and add the total, should be less than $100
			total += recipes.get(current - 1).findCost();
			System.out.println(total);

			// create a temp variable to hold the recipe
			List<String> temp = recipes.get(current - 1).getIngredients();
			if (temp.contains("non-gluten")) {
				nonGluten++;
			} else {
				if (temp.contains("non-peanut")) {
					nonProtein++;
				}
			}
		}
		// check the cost
		if (total < 100) {
			System.out.println("\ncost is below 100---> " + total);
			// return true;
			// check if it meets the nutrition value
			if ((nonGluten == 1) && nonProtein == 1) {
				System.out.println("\nBalanced diet\n");
				return true;
			}
		} else {
			System.out.println("ABOVE 100!!!!!!!!!!!!!!!!!!:--> " + total);
		}

		return false;
	}
	
	//METHOD 3 : finds the solution: returns recipes meeting the constraints(return the titles only)
	public static List<List<String>> findSolution(ArrayList<Integer> budget, ArrayList<String> nutrition, 
			List<Recipes> recipes,
			List<List<Integer>> partialRes, List<List<String>> result) {

		if (budget == null || recipes == null) {
			System.out.println("cannot compute! please enter the required variables");
		} else {
				for (int i = 0; i < recipes.size(); i++) {
					System.out.println(recipes.get(i).display());
				}
				// get all possible combinations for the recipes
				List<List<Integer>> recCombinations = combine(5, 3);
				System.out.println(recCombinations);

				// loop through each combination,send them to isValid function
				for (int n = 0; n < recCombinations.size(); n++) {
					if (isValid(recCombinations.get(n), recipes)) {
						System.out.println("*********** LIST OF COMBINATIONS *********");
						partialRes.add(recCombinations.get(n));	
			            System.out.println(partialRes);
			            System.out.println("The resultsss: "+ result);
						int j = 0; 
                        for(List innerlist: partialRes) {
                        	System.out.println(innerlist);
                        	List<String> titles = new ArrayList<>();
                        	for(Object i : innerlist) {
                        		// System.out.println(i);
                        		 int current = (int) i;
                			     System.out.println(recipes.get(current - 1).getTitle());
                        		 
                			     String recipeTitle = recipes.get(current - 1).getTitle();
                			     
                			     titles.add(recipeTitle);
                			    // System.out.println(j);
                			    System.out.println("final" + titles);
                			   
                        		 j++;
                        	}
                        	
                        	
                        	System.out.println("\n round 1"+result);
                        	result.add(titles);
                        	System.out.println("------------\n"+result);
                        } 
						
					//	System.out.println(result);
						System.out.println("Does not violate the constraints: ");
						// result.add(new ArrayList<>(partialRes));
						// look for a way to return the combinations or the indexes of the recipes;
					} else {
						System.out.println("cannot be uploaded, does not meet nutritional needs");
					}
				}
		}

		// create function to check if its valid
		// how do we go through all the domains of our variables?, if invalid we
		// backtrack

		return result;
	}
	
	//METHOD 4: combine
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
		// Create the variables
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
		List<List<String>> newList = keyComponents(Budget, Nutrition, recipeList);
        
	}

}

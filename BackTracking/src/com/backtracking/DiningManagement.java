package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DiningManagement {
	
    //METHOD 1 Key Components to consider
	public static List<List<String>> keyComponents(ArrayList<Integer> budget, ArrayList<String> nutrition, List<Recipes> recipes){
    	List<List<String>> result = new ArrayList<>();  	
        findSolution( budget, nutrition,  recipes, result);
    	return result;
    }
	
	//METHOD 2 : isValid(), check if the combinations of recipes meets the constraints(<3, has non-gluten & non-protein)
	private static boolean isValid(List<Integer> list, List<Recipes> recipes) {
		int totalCost = 0; 
		int nonProtein = 0; 
		int nonGluten = 0;

		for (int i = 0; i < list.size(); i++) {
			int current = list.get(i);
			// get cost of each and add the total, should be less than $100
			totalCost += recipes.get(current - 1).findCost();

			// create a temporary variable to hold the recipe
			List<String> temp = recipes.get(current - 1).getIngredients();
			if (temp.contains("non-gluten")) {
				nonGluten++;
			} else {
				if (temp.contains("non-peanut")) {
					nonProtein++;
				}
			}
		}
		// check if budget and nutrition requirements are met
		if (totalCost < 100) {
			return ((nonGluten == 1) && nonProtein == 1);
		}
		return false;
	}
	
  //METHOD: Retrieves the recipe titles
	public static List<String> GetRecipeTitles(List<Integer> innerlist, List<Recipes> recipes) {
		List<String> titles = new ArrayList<>();
		for(Integer current : innerlist) {
		     String recipeTitle = recipes.get(current - 1).getTitle();
		     titles.add(recipeTitle);
		}
		return titles;
	}

	
	//METHOD 3 : finds the solution: returns recipes meeting the constraints(return the titles only)
	public static List<List<String>> findSolution(ArrayList<Integer> budget, ArrayList<String> nutrition, 
			List<Recipes> recipes, List<List<String>> result) {

		if (budget == null || recipes == null) {
			System.out.println("cannot compute! please enter the required variables");
		} else {
				for (int i = 0; i < recipes.size(); i++) {
					System.out.println(recipes.get(i).display());
			        System.out.println(recipes.size());
				}
				// get all possible combinations for the recipes
				List<List<Integer>> recCombinations = combine(5, 3);
				// loop through each combination,send them to isValid function
				for (int n = 0; n < recCombinations.size(); n++) {
					if (isValid(recCombinations.get(n), recipes)) {
			            System.out.println("Result Before: "+ result);
			            //System.out.println(partialRes);
						List<Integer> innerList = recCombinations.get(n);
                        System.out.println("GetRecipes Input: " + innerList);
                        List<String> titles = GetRecipeTitles(innerList, recipes);
                        result.add(titles);
			            System.out.println("Result After: "+ result);
					} else {
						System.out.println("cannot be uploaded, does not meet nutritional needs");
					}
				}
		}
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
        List<List<Integer>> resultCombinations = combine(n - 1, k - 1);
        resultCombinations.forEach(e -> e.add(n));
        resultCombinations.addAll(combine(n - 1, k));
        return resultCombinations;
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
        System.out.println("Recipes to be considered with our budget: " + newList);
	}

}

package com.backtracking;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//N-Queens Problem: Given a chess board having N * N cells, we need to place N queens in such a way that 
//no queen is attacked by any other queen. 
//A queen can attack horizontally, vertically and diagonally.
public class NQueens {

    public static List<List<Integer>> nQueens(int n){
    	// create a list of lists to form an arrayList
    	List<List<Integer>> result = new ArrayList<>();
    	solveNQueens(n,0, new ArrayList<Integer>(),result);
    	return result;
    }
    
    public static boolean isValid(List<Integer> colPlacements) {
    	int rowId = colPlacements.size() -1;
    	for(int i = 0 ; i < rowId ; i++) {
    		int diff = Math.abs(colPlacements.get(i) - colPlacements.get(rowId));
    		if(diff == 0 ||  diff == rowId - i) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public static void solveNQueens(int n, int row, List<Integer> colPlacements, List<List<Integer>> result) {
    	if(row == n) {
    		result.add(new ArrayList<>(colPlacements));
    	}else {
    		for(int col = 0 ; col < n; col++) {
    			colPlacements.add(col);
    			if(isValid(colPlacements)) {
    				solveNQueens(n, row+1,colPlacements,result);
    			}
    			colPlacements.remove(colPlacements.size()-1);
    		}
    	}
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
		// TODO Auto-generated method stub
		int n = 4;
		int row = 0 ;
		System.out.println("4 by 4 grid");
		
		List<List<Integer>> res= nQueens(n);
		//System.out.println(res);
		System.out.println(combine(5,3));
	}

}
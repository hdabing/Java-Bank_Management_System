package test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test2 {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		Random random = new Random();
		
		while(set.size() < 10){
			set.add(random.nextInt(999999999));
		}
		
		for (Integer integer : set) {
			System.out.println(set);
		}
		
		
		
	}
	
}

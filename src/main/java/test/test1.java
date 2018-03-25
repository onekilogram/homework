package test;

import java.lang.reflect.Method;

public class test1 {

	class Sm{
		String s = "a";
		String m = s ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ahha 

		//ggg
		//hh
		
		
		
		
		
	}
    
	public void Method1(Sm sm){
		sm.m =sm.s;
		sm.s=sm.s+sm.s;
	}
	public void Method2(Sm sm){
		sm.s=sm.s+sm.m;
	}
}

package EvaluateExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Evaluate {
	public static double operate(char op, double num2, double num1) {
		switch(op) {
		case '*':
			return num1*num2;
		case '/':
			if(num2==0) {
				throw new ArithmeticException();
			}else {
				return num1/num2;
			}
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '^':
			return Math.pow(num1, num2);
		}
		return 0;
	}
	
	public static int precedence(char op) {
		if(op=='(') {
			return -1;
		}
		if(op=='^') {
			return 4;
		}
		if(op=='*' || op=='/') {
			return 1;
		}
		return 0;
}
	public static double evaluateString(String str) {
		Stack<Double> values=new Stack<Double>();
		Stack<Character> ops=new Stack<Character>();
		char[] array=str.toCharArray();
		for(int i=0;i<array.length;i++) {
			if(array[i]==' ') {
				continue;
			}
			if(array[i]>='0' && array[i]<='9') {
				StringBuffer buf=new StringBuffer();
				while(i<array.length && array[i]>='0' && array[i]<='9' ) {
					buf.append(array[i++]);
				}
				values.push(Double.parseDouble(buf.toString()));
			}else if(array[i]=='(') {
				ops.push(array[i]);
			}
			else if(array[i]==')') {
				while(ops.peek()!='(')
				values.push(operate(ops.pop(), values.pop(),values.pop()));
				ops.pop();
			}
			else if(Arrays.asList('*', '/', '+', '-', '^').contains(array[i])) {
				while(!ops.empty() && precedence(ops.peek())>=precedence(array[i])) {
					values.push(operate(ops.pop(), values.pop(),values.pop()));
				}
				ops.push(array[i]);
			}
		}
		while(!ops.empty()) {
			values.push(operate(ops.pop(), values.pop(),values.pop()));
		}
		return values.pop();
	}
	
	public static void main(String[] args ) {
		String x = "123  , sd      ds  sp    ";
		String[] y = x.split("\\s+");
		for(String i: y)
		System.out.println(i);
		System.out.println(y.length);
			
//			System.out.println(evaluateString("10 ^ 2 * 6")); 
//	        System.out.println(evaluateString("100 * 2 + 12")); 
//	        System.out.println(evaluateString("100 * ( 2 + 12 )")); 
//	        System.out.println(evaluateString("100 * ( 2 + 12 ) / 14")); 
	}
}

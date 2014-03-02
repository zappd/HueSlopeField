package com.colors.parser;

import java.security.InvalidKeyException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
	volatile static int end;
	volatile static boolean changed;

	private static String PEMDAS = "-+/*^";

	private String 		mEquation 	= null;
	private EvalNode	mRoot 		= null;
	private EvalNode[]	mHeap		= null;

	private int	mMaxParenDepth = 0;

	public Parser(String equation) throws ParserException {
		mEquation = " " + equation;
		mRoot = new EvalNode();
		
		if (parensMatch()) {
			addParens();
			mEquation = mEquation.replaceAll(" ", "");
			buildEvalTree(mRoot, mEquation);
			mHeap = new EvalNode[(int)Math.pow(2, heapDepth(mRoot) + 1)];
			buildEvalHeap(mRoot, 0);
			System.out.println("Derp");
		} else {
			throw new ParserException(ParserException.MISMATCHED_PARENS_ERROR);
		}
	}

	private void addParens() {
		int parenDepth = 0;

		for (int m = mMaxParenDepth; m >= 0 ; m--) {
			int index = 0;
			while (index < mEquation.length()) {
				char c = mEquation.charAt(index);
				switch (c) {
				case '(':
					parenDepth++;
					index++;
					break;
				case ')':
					parenDepth--;
					index++;
					break;
				}

				if (parenDepth == m) {
					String prefix = mEquation.substring(0, index);
					String middix = parenthify(index);
					String suffix = mEquation.substring(end);

					if (changed) {
						mEquation = prefix + middix + suffix;
					} else {	
						index = end;
					}
				} else {
					index++;
				}
			}	
		}
	}

	private String parenthify(int start) {
		end = start;
		changed = false;

		int parenDepth = 1;
		String sub = "";
		ArrayList<String> operands  = new ArrayList<String>();
		ArrayList<String> operators = new ArrayList<String>();

		while (parenDepth > 0 && end < mEquation.length()) {
			char c = mEquation.charAt(end);
			switch (c) {
				case '(':
					parenDepth++;
					break;
				case ')':
					parenDepth--;
					break;
			}

			if (parenDepth == 1 && Character.toString(c).matches("[-+/^*]")) {
				operands.add(new String(sub));
				operators.add(Character.toString(c));
				sub = "";
				end++;
			} else if (parenDepth > 0) {
				sub = sub + c;
				end++;
			}
		}

		if (operators.size() == 1 && operands.size() == 1) {
			return null;
		}

		operands.add(sub);
		operators.add("");
		String[] _operands  =  operands.toArray(new String[operands.size()]);
		String[] _operators =  operators.toArray(new String[operators.size()]);
		String original = mEquation.substring(start, end);

		int maxIndex = 0;
		int maxChar = '-';

		for (int i = 0; i < _operators.length; i++) {
			if (PEMDAS.indexOf(_operators[i]) > PEMDAS.indexOf(maxChar)) {
				maxChar = _operators[i].toCharArray()[0];
				maxIndex = i;
			}
		}
		
		if (_operands.length == 1) {
			return _operands[0];
		}		

		String _s = "";
		for (int i = 0; i < _operators.length; i++) {
			_s = _s + ((maxIndex == i)?"(":"") + _operands[i] + ((maxIndex == (i - 1))?")":"") + _operators[i];
		}

		_s = " " + _s.replaceAll(" ", "");

		changed = !_s.equals(original);
		return _s;
	}	

	private boolean parensMatch() {
		int parenDepth = 0;

		for (int i = 0; i < mEquation.length(); i++) {
			switch (mEquation.charAt(i)) {
			case '(':
				parenDepth++;
				if (parenDepth > mMaxParenDepth) 
					mMaxParenDepth = parenDepth;

				break;
			case ')':
				parenDepth--;
				break;
			}
		}

		return (parenDepth == 0);
	}

	public Double evaluate(double x, double y) {
		HashMap<String,Double> map = new HashMap<String, Double>();
		map.put("x", x);
		map.put("y", y);

		for (int i = mHeap.length - 1; i >= 0; i--) {
			if (mHeap[i] == null) continue;
			if (getChildIndex(i, 2) >= mHeap.length) continue;
			if (mHeap[i].function == null) continue;

			map.put("a", (mHeap[getChildIndex(i, 1)] == null)?null:mHeap[getChildIndex(i, 1)].value);
			map.put("b", (mHeap[getChildIndex(i, 2)] == null)?null:mHeap[getChildIndex(i, 2)].value);

			EvalNode n = mHeap[i];
			n.value = n.function.evaluate(map);
		}

		return mHeap[0].value;
	}

	private void buildEvalTree(EvalNode node, String eq) throws ParserException {
		int parenDepth = 0;

		if (Pattern.compile("[+-/^*]", Pattern.CASE_INSENSITIVE).matcher(eq).find()) {
			for (int i = 0; i < eq.length(); i++) {
				char c = eq.charAt(i);

				switch(c) {
				case '(':
					parenDepth++;
					break;
				case ')':
					parenDepth--;
					break;
				case '+': case '-': case '*': case '/': case '^':
					if (parenDepth == 0) {
						String end1 = eq.substring(0, i);
						String end2 = eq.substring(i + 1);

						try {
							node.function = new Function("" + c, new String[]{"a", "b"});
						} catch (InvalidKeyException e) {
							throw new ParserException(ParserException.UNKNOWN_FUNCTION_ERROR + ": " + c);
						}

						node.children = new EvalNode[]{new EvalNode(), new EvalNode()};

						buildEvalTree(node.children[0], end1);
						buildEvalTree(node.children[1], end2);
					}				
				default:
					break;
				}			
			}

			if (node.children == null) {
				if (eq.startsWith("(") && eq.endsWith(")")) {
					String temp = eq.substring(1, eq.length() - 1);
					buildEvalTree(node, temp);
				} else {
					String func = eq.substring(0, eq.indexOf('('));
					String arg = eq.substring(func.length() + 1, eq.length() - 1);

					try {
						node.function = new Function(func, new String[]{"a"});
					} catch (InvalidKeyException e) {
						throw new ParserException(ParserException.UNKNOWN_FUNCTION_ERROR + ": " + func);
					}

					node.children = new EvalNode[]{new EvalNode()};
					buildEvalTree(node.children[0], arg);
				}
			}
		} else {
			if (eq.startsWith("(") && eq.endsWith(")")) {
				String temp = eq.substring(1, eq.length() - 1);
				buildEvalTree(node, temp);
			} else if (eq.matches("[0-9.]+") && eq.split(".").length <= 2) {
				node.value = Double.parseDouble(eq);
			} else if (eq.matches("[xy]")) {
				try {
					node.function = new Function("id", new String[]{eq});
				} catch (InvalidKeyException e) {
					System.exit(0);
				}
			} else {
				String func = eq.substring(0, eq.indexOf('('));
				String arg = eq.substring(func.length() + 1, eq.length() - 1);

				try {
					node.function = new Function(func, new String[]{"a"});
				} catch (InvalidKeyException e) {
					System.out.println("Invalid Function: " + func);
					System.exit(0);
				}

				node.children = new EvalNode[]{new EvalNode()};
				buildEvalTree(node.children[0], arg);
			}
		}
	}

	private void buildEvalHeap(EvalNode node, int index) {
		if (node != null) {
			mHeap[index] = node;
			if (node.children != null) {
				buildEvalHeap(node.children[0], getChildIndex(index, 1));
				buildEvalHeap((node.children.length == 1)?null:node.children[1], getChildIndex(index, 2));
			}
		}
	}

	private int getChildIndex(int parentIndex, int childPosition) {
		return 2*parentIndex + childPosition;
	}

	private int heapDepth(EvalNode node) {
		if (node == null)
			return 0;
		if (node.children == null)
			return 1;

		return 1 + Math.max(heapDepth(node.children[0]), (node.children.length == 1)?0:heapDepth(node.children[1]));
	}

	private class EvalNode {
		public Function 	function = null;
		public EvalNode[] 	children = null;
		public Double 		value 	 = null;
	}
}

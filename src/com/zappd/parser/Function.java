package com.zappd.parser;

import java.security.InvalidKeyException;
import java.util.HashMap;

public class Function {
	public static final String ID_STRING	= "ID";
	public static final String ABS_STRING 	= "ABS";
	public static final String EXP_STRING 	= "EXP";
	public static final String SQRT_STRING 	= "SQRT";
	public static final String LOG_STRING 	= "LOG";
	public static final String LOG10_STRING = "LOG10";
	public static final String SIN_STRING 	= "SIN";
	public static final String COS_STRING 	= "COS";
	public static final String TAN_STRING 	= "TAN";
	public static final String ASIN_STRING 	= "ASIN";
	public static final String ACOS_STRING 	= "ACOS";
	public static final String ATAN_STRING 	= "ATAN";
	public static final String ADD_STRING	= "+";
	public static final String SUB_STRING	= "-";
	public static final String MULT_STRING	= "*";
	public static final String DIV_STRING	= "/";
	public static final String POW_STRING	= "^";

	public static final int ID		= 0;
	public static final int ABS 	= 1;
	public static final int EXP 	= 2;
	public static final int SQRT 	= 3;
	public static final int LOG 	= 4;
	public static final int LOG10 	= 5;
	public static final int SIN 	= 6;
	public static final int COS 	= 7;
	public static final int TAN 	= 8;
	public static final int ASIN 	= 9;
	public static final int ACOS 	= 10;
	public static final int ATAN 	= 11;
	
	public static final int ADD		= 12;
	public static final int SUB		= 13;
	public static final int MULT	= 14;
	public static final int DIV		= 15;
	public static final int POW		= 16;

	private static final HashMap<String, Integer> mFunctionMap;
	static {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(ID_STRING,    ID);
		map.put(ABS_STRING,   ABS);
		map.put(EXP_STRING,   EXP);
		map.put(SQRT_STRING,  SQRT);
		map.put(LOG_STRING,   LOG);
		map.put(LOG10_STRING, LOG10);
		map.put(SIN_STRING,   SIN);
		map.put(COS_STRING,   COS);
		map.put(TAN_STRING,   TAN);
		map.put(ASIN_STRING,  ASIN);
		map.put(ACOS_STRING,  ACOS);
		map.put(ATAN_STRING,  ATAN);
		map.put(ADD_STRING,	  ADD);
		map.put(SUB_STRING,   SUB);
		map.put(MULT_STRING,  MULT);
		map.put(DIV_STRING,   DIV);
		map.put(POW_STRING,   POW);
		mFunctionMap = map;
	}

	public int function = 0;
	public String[] vars = null;

	public Function(String key, String... vars) throws InvalidKeyException {
		if (!mFunctionMap.containsKey(key.toUpperCase())) {
			throw new InvalidKeyException();
		}
		
		this.function = (int) mFunctionMap.get(key.toUpperCase());	
		this.vars = vars;
	}

	public double evaluate(HashMap<String, Double> map) {
		switch (function) {
		case  ID:
			return map.get(vars[0]);
		case  ABS:
			return Math.abs(map.get(vars[0]));
		case  EXP:
			return Math.exp(map.get(vars[0]));
		case  SQRT:
			return Math.sqrt(map.get(vars[0]));
		case  LOG:
			return Math.log(map.get(vars[0]));
		case  LOG10:
			return Math.log10(map.get(vars[0]));
		case  SIN:
			return Math.sin(map.get(vars[0]));
		case  COS:
			return Math.cos(map.get(vars[0]));
		case  TAN:
			return Math.tan(map.get(vars[0]));
		case  ASIN:
			return Math.asin(map.get(vars[0]));
		case  ACOS:
			return Math.acos(map.get(vars[0]));
		case  ATAN:
			return Math.atan(map.get(vars[0]));
		case  ADD:
			return map.get(vars[0]) + map.get(vars[1]);
		case  SUB:
			return map.get(vars[0]) - map.get(vars[1]);
		case  MULT:
			return map.get(vars[0]) * map.get(vars[1]);
		case  DIV:
			return map.get(vars[0]) / map.get(vars[1]);
		case POW:
			return Math.pow(map.get(vars[0]), map.get(vars[1]));
		default:
			return 0;
		}
	}
}

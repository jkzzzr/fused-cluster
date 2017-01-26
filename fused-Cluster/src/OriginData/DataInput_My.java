package OriginData;
import java.util.ArrayList;
import java.util.HashMap;


public class DataInput_My {

	//原始数据：词项列表：qid - doc
	public static HashMap<Integer, ArrayList<Integer>> Input_QD= 
			new HashMap<Integer,ArrayList<Integer>>();
	//所有文档的词项列表： doc - wordcount
	public static  HashMap<Integer, HashMap<Integer, Integer>> Input_D_W = 
			new HashMap<Integer, HashMap<Integer,Integer>>();
	//原始的得分列表：qid - doc - score
	public static HashMap<Integer, HashMap<Integer, Double>> Input_dS = 
			new HashMap<Integer, HashMap<Integer,Double>>();
}

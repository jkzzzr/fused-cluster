package fuse.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import OriginData.DataInput_My;
import filter.structure.Centroid;
import fuse.compute.PreResult;

public class ScoreBuilder {
	/**
	 *  P（c|q）<br>某类别在查询下的得分<br>qid - collection - score
	 */
	public static HashMap<Integer, ArrayList<Double>> pcq = new HashMap<Integer, ArrayList<Double>>();
/*	*//**
	 * 融合得分 qid - docid - score
	 *//*
	public static HashMap<Integer, HashMap<Integer, Double>> fusescore_Origin = new HashMap<Integer, HashMap<Integer,Double>>();	
*/	
	/*P(d|c) <br>qid - doc - collection - score
	 */
	public static HashMap<Integer, HashMap<Integer,ArrayList<Double>>> pdc = new HashMap<Integer, HashMap<Integer,ArrayList<Double>>>();

	
	
	
	
	

	public static ArrayList<Double> getPcq(Integer qid) {
		ArrayList<Double> result = null;
		if (!pcq.containsKey(qid)){
			result = createPcq(qid);
			pcq.put(qid, result);
		}else {
			result = pcq.get(qid);
		}
		return result;
	}

	/**
	 * @param qid
	 * @return collection - score
	 */
	private static ArrayList<Double> createPcq(Integer qid) {
		HashMap<Integer, Double> originresult = DataInput_My.Input_dS.get(qid);
		ArrayList<Centroid> alist = PreResult.Q_CentrMap.get(qid);
		ArrayList<Double> result = new ArrayList<Double>();
		double sumValue = 0.0;
		for (int i = 0; i < alist.size(); i++){
			Centroid centroid = alist.get(i);
			ArrayList<Integer> tempDocList = centroid.doclist;
			double multiValue = 0.0;
			for (Integer tempdoc : tempDocList){
				double tempscore = originresult.get(tempdoc);
				if (tempscore != 0){
					multiValue *=tempscore;
				}
			}
			result.add(i, multiValue);
			sumValue +=multiValue;
		}
		for (int i = 0; i < result.size(); i++){
			double dd = result.get(i);
			result.set(i, dd/sumValue);
		}
		return result;
		
	}

	public static HashMap<Integer, Double> getFusescore_Origin(Integer qid) {
		HashMap<Integer, Double> result = DataInput_My.Input_dS.get(qid);
		return result;
	}

	public synchronized static  HashMap<Integer,ArrayList<Double>> getPdc(Integer qid) {
		 HashMap<Integer,ArrayList<Double>> result = null;
		if (pdc.containsKey(qid)){
			result = pdc.get(qid);
		}else {
			result = createPdc(qid);
			pdc.put(qid, result);
		}
		return result;
	}

	/**
	 * 
	 * @param qid
	 * @return docid - arraylist<score>
	 */
	private static  HashMap<Integer,ArrayList<Double>> createPdc(Integer qid) {
		HashMap<Integer,ArrayList<Double>> result = new HashMap<Integer, ArrayList<Double>>();
		ArrayList<Centroid> alist = PreResult.Q_CentrMap.get(qid);
		HashMap<Integer, HashMap<Integer, Double>> siMap = PreResult.Q_SimMap.get(qid);
		//所有文档列表
		Set<Integer> docSet = siMap.keySet();
		
		for (Integer docid : docSet){
			//便利所有文档，计算所有文档的分子
			ArrayList<Double> tempresult = new ArrayList<Double>();
			//对每篇文档，都要计算他和其他所有类别的得分
			for (int centrNo = 0; centrNo < alist.size(); centrNo++){
				Centroid centroid = alist.get(centrNo);
				ArrayList<Integer> docInCen = centroid.doclist;
				double sumValue = 0.0;
				for (Integer docInteger : docInCen){
					//!!!!特别注意：类别中的文档总是在前面！！！
					sumValue += siMap.get(docInteger).get(docid);
				}
				tempresult.add(centrNo, sumValue);
			}
			result.put(docid, tempresult);
		}
		//计算分母
		ArrayList<Double> cenSum = new ArrayList<Double>();
		for (int i = 0; i < alist.size(); i++){
			cenSum.add(i, 0.0);
		}
		//便利所有文档，统计它与各个数据集之间的距离，并记录总和
		for (Entry<Integer,ArrayList<Double>> entry : result.entrySet()){
			ArrayList<Double> tempal = entry.getValue();
			for (int i = 0; i < tempal.size(); i++){
				double dd = cenSum.get(i);
				dd += tempal.get(i);
				cenSum.set(i, dd);
			}
		}
		//对每个都出一分母
		for (Integer docidInLoop : result.keySet()){
			ArrayList<Double> tempArrayList = result.get(docidInLoop);
			for (int i = 0; i < tempArrayList.size(); i++){
				double tempValue = tempArrayList.get(i)/cenSum.get(i);
				tempArrayList.set(i, tempValue);
			}
			//因为总是引用的，所以就没必要加上这一句了,即get得到的对象，就是引用
			//result.put(docidInLoop, tempArrayList);
		}
		return result;
	}
	
	
}

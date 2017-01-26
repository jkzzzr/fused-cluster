package filter.structure;

import java.util.HashMap;
import java.util.Map.Entry;

import OriginData.IDF;

public class Doc_Miu {
	public HashMap<Integer, Double> WordProbability = new HashMap<Integer, Double>();
	public double ARFA = 0.0;
	public Integer docid = 0;
	public int miu = 0;
	public int qid = 0;
	public Doc_Miu(Doc_Origin doc, int miu1,int q){
		this.miu = miu1;
		this.qid = q;
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		double fenmu = miu;
		for (Entry<Integer, Integer> entry: doc.WordCount.entrySet()){
			Integer word = entry.getKey();
			Integer count = entry.getValue();
			fenmu +=count;
			double proOnCollection = getWordProOnCollection(word);
			double fenzi = count + miu * proOnCollection;
			result.put(word, fenzi);
		}
/*		for (Entry<Integer, Double> entry : result.entrySet()){
			double value = entry.getValue() / fenmu;
			result.put(entry.getKey(), value);
		}*/
		final double fenmu2 = fenmu;
		result.forEach((o1,o2)->{
			o2 /= fenmu2;
			result.put(o1, o2);
		});
		ARFA = miu/fenmu;
	}
	public Integer getWordProOnCollection(Integer i){
		int wordcountoncollection = IDF.wordCountOnCollection.get(this.qid).get(i);
		return wordcountoncollection;
	}
	public double get(Integer key){
		if (WordProbability.containsKey(key)){
			return WordProbability.get(key);
		}else {
			return ARFA;
		}
	}
}

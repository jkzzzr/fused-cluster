package filter.structure;

import java.util.HashMap;

import OriginData.DataInput_My;

public class Doc_Origin {

	public HashMap<Integer, Integer> WordCount = new HashMap<Integer, Integer>();
	public Integer docid = 0;
	public Doc_Origin(Integer doc){
		this.docid = doc;
		WordCount = getdoc(doc);
	}
	private HashMap<Integer, Integer> getdoc(Integer integer){
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>(
				DataInput_My.Input_D_W.get(integer));
		return result;
	}
}

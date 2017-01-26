package filter.structure;

import java.util.HashMap;

public class Doc_Origin_Builder {

	public HashMap<Integer, Doc_Origin> docOriginList = new HashMap<Integer, Doc_Origin>();

	public Doc_Origin get(Integer key){
		if (docOriginList.containsKey(key)){
			return docOriginList.get(key);
		}else {
			Doc_Origin doc_Origin = new Doc_Origin(key);
			this.docOriginList.put(key, doc_Origin);
			return doc_Origin;
		}
	}
/*	public void put(Integer key, Doc_Origin value){
		docOriginList.put(key, value);
	}*/
}

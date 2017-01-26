package filter.structure;

//由于相似度是越大越好，所以排序顺序需要变以下
public class Data implements Comparable<Data>{
	public Integer docid;
	public double score;
	public Data(){
		docid = 0;
		score = 0.0;
	}
	public Data(Integer doc , double s){
		docid = doc;
		score = s;
	}
	
	@Override
	public int compareTo(Data d) {
		int result = 0;
		if (this.score > d.score){
			result = 1;
		}else if (this.score == d.score){
			//防止分数一样的情况下，添加TreeSet中的时候，忽略掉了
			if (this.docid > d.docid){
				result = 1;
			}else if (this.docid < d.docid){
				result = -1;
			}else {
				result =0;
			}
		}else {
			result = -1;
		}
		return -result;
	}
}

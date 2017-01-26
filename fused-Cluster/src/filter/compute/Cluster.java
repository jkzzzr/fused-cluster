package filter.compute;

import java.util.ArrayList;
import java.util.TreeSet;

import OriginData.DataInput_My;
import filter.structure.ClusterList;
import filter.structure.Data;


public class Cluster {
	//每个类中包含的文档数量
	public static int THETA = 10;
	
	
	public int qid = 0;
	//已经计算出来的相似度结果
	public  ComputeSim csim = null;
	//文档列表
	public ArrayList<Integer> doclist = new ArrayList<Integer>();
	//****************************
	//结果存储在：：：
	//存储分类信息的类
	public ClusterList clusterList = null;
	
	public Cluster(int q){
		this.qid = q;
		csim = new ComputeSim(this.qid);
		doclist = DataInput_My.Input_QD.get(q);
		clusterList = new ClusterList(q);
	}
	
	/**
	 * 让所有文档找到他对应的类别，并初始化所有类,同时保存记录的距离别<br>
	 * 结果：1、将所有文档分好了，所属的类别，存放在：ClusterList.doc_CenMap  (类型是：HashMap<Integer, Integer>)<br>
	 *  	<tt> 2、记录下了所有类别<tt>、存放在：ClusterList.cenlist  (类型是：ArrayList<Centroid>)<br>
	 * 	<tt>3、记录下了所有文档相似度计算的得分<tt>、存放在：ComputeSim.simMap类型是：HashMap<String, Double> <br>
	 */
	public void run(){
		
		//遍历所有文档
		for (int i = 0; i < doclist.size(); i++){
			Integer docitem = doclist.get(i);
			TreeSet<Data> datalist = new TreeSet<Data>();
			//每个文档要计算出它与其他文档之间的距离
			for (int j = 0; j < doclist.size(); j++){
				//不能包含自身
				if (j ==i){
					continue;
				}
				Integer docitem2 = doclist.get(j);
				Double sim = this.csim.getSim(docitem, docitem2);
				Data tempData = new Data(docitem2, sim);
				datalist.add(tempData);
			}
			this.clusterList.add(docitem, datalist);
		}
		
	}
}

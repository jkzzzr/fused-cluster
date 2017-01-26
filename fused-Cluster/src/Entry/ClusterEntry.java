package Entry;

import filter.compute.Cluster;
import filter.compute.ComputeSim;

/**
 * 将所有文档分类的入口
 * @author lee
 *
 */
public class ClusterEntry {

	/**
	 * 让所有文档找到他对应的类别，并初始化所有类,同时保存记录的距离别<br>
	 * 结果：1、将所有文档分好了，所属的类别，存放在：ClusterList.doc_CenMap  (类型是：HashMap<Integer, Integer>)<br>
	 *  	<tt> 2、记录下了所有类别<tt>、存放在：ClusterList.cenlist  (类型是：ArrayList<Centroid>)<br>
	 * 	<tt>3、记录下了所有文档相似度计算的得分<tt>、存放在：ComputeSim.simMap类型是：HashMap<String, Double> <br>
	 */
	public static void main(String args[]){
		int qid = 0;
		Cluster cluster = new Cluster(qid);
		cluster.run();
		
		
		
	}
}

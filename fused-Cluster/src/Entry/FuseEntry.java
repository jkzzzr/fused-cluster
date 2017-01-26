package Entry;

import java.util.HashMap;

import fuse.compute.ClusterFuse;

public class FuseEntry {

	public static void main(String[] args) {
		int qid = 1;
		ClusterFuse clusterFuse = new ClusterFuse(qid);
		clusterFuse.run();
		HashMap<Integer, Double> result = clusterFuse.result;
	}

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question5 {

	// time: O(E^2), where E = number of edges in the graph
	// space: O(E^2), where E = number of edges in the graph
	public static void printPathsOf5(Vertex u, Vertex w) {
		HashMap<ArrayList<Vertex>, Integer> pathsWithWeight = new HashMap<>();
		pathsFromUtoW(u, w, new ArrayList<>(List.of(u)), 0, pathsWithWeight);
		for (ArrayList<Vertex> path : pathsWithWeight.keySet()) {
			if (pathsWithWeight.get(path) != null && pathsWithWeight.get(path) == 5) {
				System.out.println(path);
			}
		}
	}

	// time: O(E^2), where E = number of edges in the graph
	// space: O(E^2), where E = number of edges in the graph
	public static void pathsFromUtoW(Vertex u, Vertex w, ArrayList<Vertex> curPath, int curWeight, HashMap<ArrayList<Vertex>, Integer> pathsWithWeight) {
		if (curPath.get(curPath.size() - 1).equals(w)) {
			pathsWithWeight.put(curPath, curWeight);
		} else if (!u.adjList.isEmpty()) {
			for (Vertex v : u.adjList.keySet()) {
				// only simple paths: no repeat vertices
				if (!curPath.contains(v)) {
					ArrayList<Vertex> newPath = new ArrayList<>(curPath);
					newPath.add(v);
					pathsFromUtoW(v, w, newPath, curWeight + u.adjList.get(v), pathsWithWeight);
				}
			}
		}
	}

	public static void main(String[] args) {
		Vertex u = new Vertex("u");
		Vertex w = new Vertex("w");
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		u.adjList.put(w, 5);
		u.adjList.put(a, 3);
		u.adjList.put(b, 1);
		u.adjList.put(c, 4);
		w.adjList.put(c, 2);
		a.adjList.put(b, 2);
		a.adjList.put(c, 1);
		a.adjList.put(w, 3);
		b.adjList.put(w, 0);
		c.adjList.put(w, 1);
		printPathsOf5(u, w);
	}

	// adjacency list implementation: each Vertex contains hash map
	// keys are adjacent vertices
	// values are integer weight of the edge to that vertex
	private static class Vertex {
		public String name;
		public HashMap<Vertex, Integer> adjList = new HashMap<>();
		public Vertex(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}

}

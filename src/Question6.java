import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.images.Resolutions;
import org.graphstream.ui.swing.util.SwingFileSinkImages;

import java.io.IOException;
import java.util.ArrayList;

public class Question6 {

	// time: O(n), where n = s.length
	// space: O(V + E), where V = # of vertices in the graph and E = # of edges in the graph
	public static void drawGraph(String s) throws IOException {
		ArrayList<String> nodes = new ArrayList<>();
		ArrayList<Integer> nums = new ArrayList<>();
		for (char c : s.toCharArray()) {
			if (Character.isAlphabetic(c)) nodes.add("" + c);
			if (Character.isDigit(c)) nums.add(Integer.parseInt("" + c));
		}

		System.setProperty("org.graphstream.ui", "swing");
		Graph graph = new MultiGraph("graph");
		String styleSheet = """
			graph {
				padding: 40px;
			}
			node {
				size: 0.05%, 0.05%;
				shape: circle;
				fill-color: #FFFFFF;
				stroke-mode: plain;
				stroke-width: 2;
				text-mode: normal;
				text-size: 50;
				text-offset: 0, -12;
			}
		""";
		graph.setAttribute("ui.stylesheet", styleSheet);

		for (String str : nodes) {
			graph.addNode(str);
		}

		// using Math.floorMod() instead of % because floorMod() returns correct, positive result
		//     for modulo operation, whereas % returns the remainder, which can be negative
		// https://stackoverflow.com/q/4412179
		for (int i = 0; i < nums.size(); i++) {
			// IdAlreadyInUseException thrown if we try to add an edge identical to a current edge
			// in this case, ignore the exception and move on; the edge is not added
			try {
				int index1 = Math.floorMod(i + nums.get(i), nums.size());
				graph.addEdge(nodes.get(i) + nodes.get(index1), nodes.get(i), nodes.get(index1), true);
				int index2 = Math.floorMod(i - nums.get(i), nums.size());
				graph.addEdge(nodes.get(i) + nodes.get(index2), nodes.get(i), nodes.get(index2), true);
			} catch (IdAlreadyInUseException ignored) {}
		}

		for (Node node : graph) {
			node.setAttribute("ui.label", node.getId());
		}

		graph.display();

		FileSinkImages img = new SwingFileSinkImages(FileSinkImages.OutputType.PNG, Resolutions.HD1080);
		img.setStyleSheet(styleSheet);
		img.setLayoutPolicy(FileSinkImages.LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
		img.setQuality(FileSinkImages.Quality.HIGH);
		img.writeAll(graph, "graph.png");
	}

	public static void main(String[] args) throws IOException {
		drawGraph("[ (I, 2) , (A, 5) , (E, 4) , (F,1) , (T, 2) , (S, 3) ]");
	}

}

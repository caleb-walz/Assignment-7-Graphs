public class Question4 {

	// time: O(V^2), where V = number of vertices in graph (O((V^2)/2) = O(V^2))
	// space: O(V), where V = number of vertices in graph
	public static boolean isDirectedGraph(int[][] matrix) {
		// check if matrix is symmetrical along diagonal
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 0; j < i; j++) {
				if (matrix[j][i] != matrix[i][j]) return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
				{ 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 1, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		System.out.println("Is directed? " + isDirectedGraph(matrix));
		matrix = new int[][] {
				{ 0, 1, 0, 0, 0, 1, 0, 0 },
				{ 1, 0, 1, 0, 0, 1, 0, 0 },
				{ 0, 1, 0, 1, 1, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 0 }
		};
		System.out.println("Is directed? " + isDirectedGraph(matrix));
	}

}

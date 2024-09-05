import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class BFSAnimation extends Application {
    private Map<Integer, Circle> nodes = new HashMap<>();
    private Set<Integer> visitedNodes = new HashSet<>();
    private Queue<Integer> traversalOrder = new LinkedList<>();

    private void addNode(int value, double x, double y) {
        Circle node = new Circle(x, y, 20);
        node.setFill(Color.LIGHTBLUE);
        node.setStroke(Color.BLACK);
        nodes.put(value, node);
    }

    private void addEdge(int from, int to) {
        Line edge = new Line(
                nodes.get(from).getCenterX(),
                nodes.get(from).getCenterY(),
                nodes.get(to).getCenterX(),
                nodes.get(to).getCenterY()
        );
        edge.setStroke(Color.BLACK);
        edge.setStrokeWidth(2);
        edge.toBack(); // Ensure edges are drawn behind nodes
        root.getChildren().add(edge);
    }

    private void animateTraversal() {
        Timeline timeline = new Timeline();
        Duration duration = Duration.seconds(1);

        for (int nodeValue : traversalOrder) {
            KeyFrame keyFrame = new KeyFrame(duration, e -> {
                Circle node = nodes.get(nodeValue);
                node.setFill(Color.YELLOW);
                visitedNodes.add(nodeValue);
            });
            timeline.getKeyFrames().add(keyFrame);

            // Pause between nodes
            duration = duration.add(Duration.seconds(1));
        }

        timeline.play();
    }

    private Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) {
        addNode(0, 50, 50);
        addNode(1, 150, 50);
        addNode(2, 250, 150);
        addNode(3, 150, 250);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(3, 3);

        traversalOrder.addAll(Arrays.asList(2, 0, 3, 1)); // Adjust traversal order as needed

        animateTraversal();

        root.getChildren().addAll(nodes.values());

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.setTitle("BFS Traversal Animation");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

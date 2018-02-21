package robotgame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{

	private Group gameOverGroup;
	private AnimationTimer checkGameOverAT;


	@Override
	public void start(Stage primaryStage) throws Exception {
		gameOverGroup = new Group(); //TODO: Create red text that says game over.
		Group root = new MapInterpreter();

		Scene scene = new Scene(root,1200,600);
		primaryStage.setScene(scene);
		primaryStage.show();

		checkGameOverAT = new AnimationTimer() {
			@Override
			public void handle(long now) {
				checkGameOver(primaryStage, gameOverGroup);
			}
		};

		checkGameOverAT.start();

	}
	private void checkGameOver(Stage primaryStage, Group gameOverGroup){
		if(Robot.gameOver){
			primaryStage.setScene(new Scene(gameOverGroup, 1200, 600));
			checkGameOverAT.stop();
		}
	}

	public static void Main(String[] args) {
		launch(args);
	}


}

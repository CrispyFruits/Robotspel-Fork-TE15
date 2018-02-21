package blocks;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class KeyBlock extends Block implements Collectible{
	
	private DoorBlock door;
	
	public KeyBlock(double square_size){
		super(square_size);
		getBackground().setFill(new ImagePattern(new Image("key.png")));
		
		
	}
	
	public void setDoor(DoorBlock d){
		this.door = d;
	}
	
	public void openDoor(){
		this.door.open();
	}
}

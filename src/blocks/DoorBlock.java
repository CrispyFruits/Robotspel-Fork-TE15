package blocks;
import javafx.scene.paint.Color;

public class DoorBlock extends Block{
	
	public DoorBlock(double square_size){
		super(square_size);
		getBackground().setFill(Color.SADDLEBROWN);
	}
	
	protected void open(){
		this.setVisible(false);
		;
	}
	
}

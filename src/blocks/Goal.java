package blocks;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Goal extends Block{
    public Goal(double square_size) {
        super(square_size);
        getBackground().setFill(new ImagePattern(new Image("chicken.jpg")));
    }
}

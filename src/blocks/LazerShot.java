package blocks;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import robotgame.MapInterpreter;


public class LazerShot extends Block{
    private double SQUARE_SIZE;

    public LazerShot(double square_size){
        super(square_size);
        this.SQUARE_SIZE = square_size;
        getBackground().setWidth(6);
        getBackground().setHeight(1);

        getBackground().setFill(Color.RED);


         setTranslateX(square_size/2);
         setTranslateY(square_size/2);






    }
    // checkCollision när block tas obbort
    private boolean checkBlockCollision(Block b) {

        double minX = b.getXPos();
        double minY = b.getYPos();

        double height = b.getSize();
        double width = b.getSize();

        boolean collision = collides(minX, minY, width, height);

        return collision;
    }

    private boolean checkCollision(Node n) {

        if (n == null || n.equals(this) || !n.isVisible() || n instanceof NotCollidable) {
            return false; // Ignore n
        }

        if (n instanceof Block) {
            if(checkBlockCollision((Block) n)){

                return true;
            }
        }

        return false;
    }

    private boolean collides(double minX, double minY, double nodeWidth, double nodeHeight) {

        double xPos = this.getTranslateX();
        double yPos = this.getTranslateY();
        Parent parent = this.getParent();
        while (!parent.getStyleClass().toString().equals("root")) {
            xPos += parent.getTranslateX();
            yPos += parent.getTranslateY();
            parent = parent.getParent();
        }

        xPos += Math.cos(Math.toRadians(270 + getRotate())) * SQUARE_SIZE;
        yPos += Math.sin(Math.toRadians(270 + getRotate())) * SQUARE_SIZE;

        double width = this.getBoundsInLocal().getWidth();
        double height = this.getBoundsInLocal().getHeight();

        // System.out.println(xPos + " " + yPos + " " + width + " " + height);
        // System.out.println(minX + " " + minY + " " + nodeWidth + " " +
        // nodeHeight);

        Rectangle r1 = new Rectangle(xPos, yPos, width, height);

        return r1.intersects(minX, minY, nodeWidth, nodeHeight);

    }

}

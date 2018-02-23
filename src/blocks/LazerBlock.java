package blocks;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LazerBlock extends Block{

    private int xDir;
    private int yDir;

    private boolean on = true;

    private ArrayList<LazerShot> shots = new ArrayList<LazerShot>();

    public LazerBlock(double square_size, int dir) {
        super(square_size);
        this.getBackground().setFill(Color.BLACK);

       if(dir == 1){
            xDir = 0;
            yDir = 1;
        }

        else if(dir == 2){
           xDir = -1;
           yDir = 0;
       }

       else if(dir == 3){
           xDir = 0;
           yDir = -1;
       }

       else if(dir == 4){
           xDir = 1;
           yDir = 0;
       }

       this.setOnMouseClicked(event -> this.turnOff());




        AnimationTimer shoot = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(on) {
                    shot();
                }

                for(int i = 0 ; i < shots.size() ; i++){
                    LazerShot s = shots.get(i);
                    s.setTranslateY(s.getTranslateY()+yDir);
                    s.setTranslateX(s.getTranslateX()+xDir);

                    if( s.getTranslateY() > 313 || !on){
                        s = null;
                        shots.remove(i);
                        i--;

                    }
                }
            }
        };

        shoot.start();
    }

    public void turnOff(){
        this.on = false;
        this.getChildren().clear();

    }

    public void turnOn(){
        this.on = true;
    }

    private void shot(){
        LazerShot shot = new LazerShot(45);
        shots.add(shot);
        this.getChildren().add(shot);

    }

}

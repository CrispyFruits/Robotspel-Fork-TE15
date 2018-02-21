package blocks;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import robotgame.MapInterpreter;

import java.util.ArrayList;

public class LazerBlock extends Block{

    private int xDir;
    private int yDir;

    private boolean on;

    private ArrayList<LazerShot> shots = new ArrayList<LazerShot>();

    public LazerBlock(double square_size, int dir) {
        super(square_size);
        this.getBackground().setFill(Color.BLACK);
       turnOn();
       if(dir == 1){
            xDir = 0;
            yDir = 1;
        }

        setOnMouseClicked(event->{

            turnOff();
        });

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

                    if(s.getTranslateX() < 0 || s.getTranslateY() > 313 ){ // TODO
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

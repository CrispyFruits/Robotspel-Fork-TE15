package robotgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import blocks.*;
import javafx.scene.Group;

/**
 * 
 * @author Joakim
 */
public class MapInterpreter extends Group {
	public final double SQUARE_SIZE = 45;
	public static ArrayList<Integer> keyValues = new ArrayList<>();
	public  static ArrayList<Integer> doorValues = new ArrayList<>();
	public static KeyBlock key;


	public MapInterpreter() throws FileNotFoundException {


	readWallBlocks();
	readAllBlock();


	}

	public void readAllBlock() throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("level1.txt"));

		int y = -1;
		ArrayList<Integer> robotCoords = new ArrayList<Integer>();
		ArrayList<Integer> keyCoords = new ArrayList<Integer>();
		ArrayList<Integer> doorCoords = new ArrayList<>();


		while (fileReader.hasNextLine()) {
			y++;
			String line = fileReader.nextLine();
			char[] blocks = line.toCharArray();
			int temp = 0;
			for (int x = 0; x < blocks.length; x++) {
				char block = blocks[x];
				Block b = null;
				switch (block) {



					case 'R': // ROBOT
						robotCoords.add(x);
						robotCoords.add(y);
						break;

					case 'K':
						temp++;x++; // ignore the number after
						keyCoords.add(x);
						keyCoords.add(y);
						keyCoords.add(Character.getNumericValue(blocks[x]));
						break;


					case 'D':
						temp++;x++; // ignore the number after
						doorCoords.add(x);
						doorCoords.add(y);
						//System.out.println((blocks[x]));
						doorCoords.add(Character.getNumericValue(blocks[x]));
						break;

					case 'L':
						temp++;x++;
						b = new LazerBlock(SQUARE_SIZE,Character.getNumericValue(blocks[x]));
						break;

					case 'G':
						b = new Goal(SQUARE_SIZE);
						break;

					default:
						b = null;
						break;

				}

				if (b != null ) {
					b.setTranslateX((x-temp) * SQUARE_SIZE - 1);
					b.setTranslateY(y * SQUARE_SIZE - 1);
					this.getChildren().add(b);
				}



			}


		}

		DoorBlock[] doors = new DoorBlock[doorCoords.size()/3];

		for (int i = 0; i < doorCoords.size(); i += 3) {
			DoorBlock door = new DoorBlock(SQUARE_SIZE);
			door.setTranslateX(doorCoords.get(i) * SQUARE_SIZE - 1);
			door.setTranslateY(doorCoords.get(i + 1) * SQUARE_SIZE -1 );
			this.getChildren().add(door);
			//	System.out.println(doors.length + " i:" + (doorCoords.get(i+2)-1));
			doors[doorCoords.get(i+2)-1] = door;
		}

		for (int i = 0; i < keyCoords.size(); i += 3) {
			key = new KeyBlock(SQUARE_SIZE);
			key.setTranslateX(keyCoords.get(i) * SQUARE_SIZE - 1);
			key.setTranslateY(keyCoords.get(i + 1) * SQUARE_SIZE - 1);
			this.getChildren().add(key);
			key.setDoor(doors[keyCoords.get(i+2)-1]);
		}

		for (int i = 0; i < robotCoords.size(); i += 2) {
			Robot r = new Robot(SQUARE_SIZE);
			r.setTranslateX(robotCoords.get(i) * SQUARE_SIZE);
			r.setTranslateY(robotCoords.get(i + 1) * SQUARE_SIZE);
			this.getChildren().add(r);
		}
	}

	private void readWallBlocks() throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("level1.txt"));
		int y = -1;
		while (fileReader.hasNextLine()) {
			y++;
			String line = fileReader.nextLine();
			char[] blocks = line.toCharArray();
			int temp = 0;
			for (int x = 0; x < blocks.length; x++) {

				char block = blocks[x];
				Block b = null;



				switch (block) {

					case '#':
						b = new ExampleBlock(SQUARE_SIZE);
						break;

					case 'L':
						temp++;x++;
					case 'D':
					case 'K':
						temp++;x++; // ignore the number after
						break;

					default:
						b = null;
						break;

				}

				if (b != null) {
					b.setTranslateX((x-temp) * SQUARE_SIZE - 1);
					b.setTranslateY(y * SQUARE_SIZE - 1);
					this.getChildren().add(b);
				}



			}


		}

	}
}

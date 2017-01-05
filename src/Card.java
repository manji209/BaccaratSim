import java.awt.image.BufferedImage;

public class Card {
	
	BufferedImage face;
	String name;
	int value;

	public Card(BufferedImage face, String name, int value) {
		this.face = face;
		this.name = name;
		this.value = value;
	}

	public BufferedImage getFace() {
		return face;
	}

	public String getName() {
		return name;
	}


	public int getValue() {
		return value;
	}


}

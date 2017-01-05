import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Deck {
	
	private LinkedList<Card> deck = new LinkedList<Card>();

	public Deck() {

	}
	
	public LinkedList<Card> createDeck() throws IOException{
		
		BufferedImage deckImg = ImageIO.read(new File("./data/Deck.png"));
		
		final int width = 90;
		final int height = 126;
		final int rows = 4;
		final int cols = 13;
		
		//52 cards in a deck 
		//Declare all card instances
		String[] cardName = {"AH", "AS", "AD", "AC", "2H", "2S", "2D", "2C", "3H", "3S", "3D", "3C", "4H", "4S", "4D", "4C",
				"5H", "5S", "5D", "5C", "6H", "6S", "6D", "6C", "7H", "7S", "7D", "7C", "8H", "8S", "8D", "8C",
				"9H", "9S", "9D", "9C", "10H", "10S", "10D", "10C", "JH", "JS", "JD", "JC", "QH", "QS", "QD", "QC", "KH", "KS", "KD", "KC"
		};
		
		int[] cardValue = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 
				10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10
		};
		
		BufferedImage[] cardFace = new BufferedImage[rows * cols];
		
		for (int i = 0; i < cols; i++)
		{
		    for (int j = 0; j < rows; j++)
		    {
		        cardFace[(i * rows) + j] = deckImg.getSubimage(
		            i * width,
		            j * height,
		            width,
		            height
		        );
		    }
		}
		
		for (int i = 0; i < 52; i++){
			Card card = new Card(cardFace[i], cardName[i], cardValue[i]);
			deck.add(card);
		}
		
		Collections.shuffle(deck);
		/*
		//Shuffle the Deck
		for(int i=0; i<52; i++) {
	        int card = (int) (Math.random() * (52-i));
	        deck.addLast(deck.remove(card));
	    }
	    */
		
	    
		
		return deck;
		
	}

	//Deals the first card from the deck
	public Card dealCard() {
		return deck.removeFirst();
	}
	
	public LinkedList<Card> getDeck(){
		return deck;
	}


}

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Shoe {
	
	private LinkedList<Card> shoe = new LinkedList<Card>();

	public Shoe() {
		// TODO Auto-generated constructor stub
	}
	
	//Every Shoe contains 8 decks
	public LinkedList<Card> initShuffleShoe() throws IOException{
		
		LinkedList<Card> deck1 = new Deck().createDeck();
		LinkedList<Card> deck2 = new Deck().createDeck();
		LinkedList<Card> deck3 = new Deck().createDeck();
		LinkedList<Card> deck4 = new Deck().createDeck();
		LinkedList<Card> deck5 = new Deck().createDeck();
		LinkedList<Card> deck6 = new Deck().createDeck();
		LinkedList<Card> deck7 = new Deck().createDeck();
		LinkedList<Card> deck8 = new Deck().createDeck();
		
		// Insert cutCard to split the shoe and move the front to back
		int cutCard = 27 + (int) (Math.random() * (385 - 27));
		
		shoe.addAll(deck1);		
		shoe.addAll(deck2);
		shoe.addAll(deck3);
		shoe.addAll(deck4);
		shoe.addAll(deck5);
		shoe.addAll(deck6);
		shoe.addAll(deck7);
		shoe.addAll(deck8);
		
		Collections.shuffle(shoe);
		Collections.shuffle(shoe);
		
		//Move the first half of the shoe shoe before the cut card to the back end
		for(int i = 0; i < cutCard; i++)
		{
			shoe.add(shoe.removeFirst());
		}
		
		
		
		
		/*
		//Shuffle the whole shoe
		for(int i=0; i<416; i++) {
	        int card = (int) (Math.random() * (416-i));
	        shoe.addLast(shoe.remove(card));
	    }
	    */
	    
	    
		
		return shoe;
	}

}

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class PlayShoe {

	private int numHands = 0;
	private int numBanker = 0;
	private int numPlayer = 0;
	private int numTie = 0;
	private int numDragon = 0;
	private int numPanda = 0;
	private int numOx = 0;
	private int numKills = 0;
	private int numNaturals = 0;
	private int player;
	private int banker;
	private boolean endOfShoe = false;
	private boolean naturalFound = false;
	List<Character> results = new ArrayList<Character>();

	LinkedList<Card> shoe = new LinkedList<Card>();

	public PlayShoe(LinkedList<Card> shoe) throws IOException {
		this.shoe = shoe;
		Play();
	}

	public void Play() throws IOException {

		BufferedImage red = ImageIO.read(new File("./data/RedCard.png"));
		// Red Card indicates last hand of shoe
		Card redCard = new Card(red, "red", 101);

		// Insert redCard somewhere at the end to signal last hand
		int card = 396 + (int) (Math.random() * (404 - 396));
		shoe.add(card, redCard);
		//System.out.println("Position of Red Card: " + shoe.indexOf(redCard));

		// Pull first card then remove n numbers of cards that correspond to
		// first card value
		Card firstCard = shoe.removeFirst();

		// Remove firstCard.getValue() of shoe
		for (int i = 0; i < firstCard.getValue(); i++) {
			shoe.removeFirst();
		}
		
		while(!endOfShoe){
			PlayHand();
			numHands++;
		}
		
		

	}

	public void PlayHand() {
		int p1, p2, b1, b2;

		// First player card. 101 tests for redCard which indicates end of shoe
		p1 = shoe.removeFirst().getValue();
		if (p1 == 101) {
			p1 = shoe.removeFirst().getValue();
			endOfShoe = true;
		}

		// First banker card. 101 tests for redCard which indicates end of shoe
		b1 = shoe.removeFirst().getValue();
		if (b1 == 101) {
			b1 = shoe.removeFirst().getValue();
			endOfShoe = true;
		}

		// First player card. 101 tests for redCard which indicates end of shoe
		p2 = shoe.removeFirst().getValue();
		if (p2 == 101) {
			p2 = shoe.removeFirst().getValue();
			endOfShoe = true;
		}

		// First banker card. 101 tests for redCard which indicates end of shoe
		b2 = shoe.removeFirst().getValue();
		if (b2 == 101) {
			b2 = shoe.removeFirst().getValue();
			endOfShoe = true;
		}

		
		player = (p1 + p2) % 10;
		banker = (b1 + b2) % 10;

		//Check First first two cards dealt each for naturals.  If Natural or Tie then next hand
		FirstTwoCards(player, banker);

		if (!naturalFound) {
			ThirdCard(player, banker);
		}
		
		//
		naturalFound = false;

	}

	public void FirstTwoCards(int play, int bank) {

		// Test for naturals
		if (play >= 8 || bank >= 8) {
			if (play == bank) {
				numTie++;
				naturalFound = true;
			} else if (play > bank) {
				numPlayer++;
				results.add('P');
				numNaturals++;
				naturalFound = true;
			} else if(bank > play){
				numBanker++;
				numNaturals++;
				results.add('B');
				naturalFound = true;
			}
		}
	}

	public void ThirdCard(int play, int bank) {
		int p3 = 0;
		int b3 = 0;


		// If the player's total is less than or equal to 5 the player's hand
		// draws a third card.  101 tests for redCard which indicates end of shoe
		if (play <= 5) {
			p3 = shoe.removeFirst().getValue();
			if (p3 == 101) {
				p3 = shoe.removeFirst().getValue();
				endOfShoe = true;
			}
			player = (player + p3) % 10;
		} else if (bank <= 5) {
			b3 = shoe.removeFirst().getValue();
			if (b3 == 101) {
				b3 = shoe.removeFirst().getValue();
				endOfShoe = true;
			}
			banker = (banker + b3) % 10;
		}

		// Player hit a third card so determine if banker needs to hit third
		// card.  101 tests for redCard which indicates end of shoe
		if (p3 > 0 && banker <= 6) {

			switch (banker) {
			case 0:
				b3 = shoe.removeFirst().getValue();
				if (b3 == 101) {
					b3 = shoe.removeFirst().getValue();
					endOfShoe = true;
				}
				banker = (banker + b3) % 10;
				break;
			case 1:
				b3 = shoe.removeFirst().getValue();
				if (b3 == 101) {
					b3 = shoe.removeFirst().getValue();
					endOfShoe = true;
				}
				banker = (banker + b3) % 10;
				break;
			case 2:
				b3 = shoe.removeFirst().getValue();
				if (b3 == 101) {
					b3 = shoe.removeFirst().getValue();
					endOfShoe = true;
				}
				banker = (banker + b3) % 10;
				break;
			case 3:
				if (p3 != 8) {
					b3 = shoe.removeFirst().getValue();
					if (b3 == 101) {
						b3 = shoe.removeFirst().getValue();
						endOfShoe = true;
					}
					banker = (banker + b3) % 10;
					break;
				}
				break;
			case 4:
				if (p3 == 1 || p3 >= 8) {
					b3 = shoe.removeFirst().getValue();
					if (b3 == 101) {
						b3 = shoe.removeFirst().getValue();
						endOfShoe = true;
					}
					banker = (banker + b3) % 10;
					break;
				}
				break;
			case 5:
				if (p3 >= 4 && p3 <= 7) {
					b3 = shoe.removeFirst().getValue();
					if (b3 == 101) {
						b3 = shoe.removeFirst().getValue();
						endOfShoe = true;
					}
					banker = (banker + b3) % 10;
					break;
				}
				break;
			case 6:
				if (p3 == 6 || p3 == 7) {
					b3 = shoe.removeFirst().getValue();
					if (b3 == 101) {
						b3 = shoe.removeFirst().getValue();
						endOfShoe = true;
					}
					banker = (banker + b3) % 10;
					break;
				}
				break;

			}

		}

		// Cards have been dealt. Determine winner
		if (player == banker) {
			numTie++;
			if((p3 > 0 && player == 6) || (b3 > 0 && banker == 7)){
				numKills++;
			}
		} else if (player > banker) {
			numPlayer++;
			results.add('P');
			if (p3 > 0 && player == 8) {
				numPanda++;
			}

			if (p3 > 0 && player == 6) {
				numOx++;
			}
			
			if (b3 > 0 && banker == 7) {
				numKills++;
			}

		} else if (banker > player) {
			numBanker++;
			results.add('B');
			if (b3 > 0 && banker == 7) {
				numDragon++;
			}
			
			if (p3 > 0 && player == 6) {
				numKills++;
			}
		}
	}

	public int getNumHands() {
		return numHands;
	}

	public int getNumBanker() {
		return numBanker;
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public int getNumTie() {
		return numTie;
	}

	public int getNumDragon() {
		return numDragon;
	}

	public int getNumPanda() {
		return numPanda;
	}

	public int getNumOx() {
		return numOx;
	}
	
	public int getNumKills() {
		return numKills;
	}

	public void setNumKills(int numKills) {
		this.numKills = numKills;
	}

	public int getNumNaturals() {
		return numNaturals;
	}

	public List<Character> getResults() {
		return results;
	}


}

public class Card {
    public String suit;
    public int value;
    public double cardID;

    public Card(int value) {
        this.value = value;
    }

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }
}

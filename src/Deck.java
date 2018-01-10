import java.util.*;

public class Deck {
    public static Card[] allCards;
    public Card[] hand;

    public Deck(boolean tf) {
        if (tf) {
            allCards = new Card[52];
            String suit;
            int index = 0;
            for (int u=1; u<14; u++) {
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        suit = "hearts";
                    } else if (i == 1) {
                        suit = "diamonds";
                    } else if (i == 2) {
                        suit = "spades";
                    } else {
                        suit = "clubs";
                    }
                    allCards[index] = new Card(suit, u);
                    index++;
                }
            }
        }
        else {
            allCards = new Card[13];
            int index = 0;
            for(int u=1; u<14; u++) {
                allCards[index] = new Card(u);
                index++;
            }
        }

        for (double i=1; i<14; i+=0.25) {
            double j = 4*(i-1);
            int b = (int) j;
            allCards[b].cardID = i;
        } //create a cardID (to be able to sort & sort by number and suit) for every card in your deck

    }

    public void swap(int first, int second) {
        Card a = hand[first];
        hand[first] = hand[second];
        hand[second] = a;
    }

    public void dealHand(int numCards) {
        hand = new Card[numCards];
        for(int i=0; i<numCards; i++) {
            hand[i] = allCards[i];
        }
    }

    public void listHand() {
        for(int i=0; i<hand.length; i++) {
            System.out.println(hand[i].value + " " + hand[i].suit + "; " + hand[i].cardID);
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(Arrays.asList(allCards));

    }

    //the sorting algorithms
    public void bubbleSortHand() {
        for(int u=0; u<hand.length; u++) {
            for (int i=0; i<hand.length-u-1; i++) {
                if (hand[i].cardID >= hand[i+1].cardID) {
                    swap(i, i+1);
                }
            }
        }
    }

    public void selectionSortHand() {
        for(int u=0; u<hand.length - 1; u++) {
            int index = u;
            for (int i = u; i < hand.length - 1; i++) {
                if (hand[i + 1].cardID <= hand [index].cardID) {
                    index = i + 1;
                }
            }
            swap(u, index);
        }
    }

    private Card[] numbers;
    private Card[] helper;
    private int number;
    public void mSort(Card[] values) {
        this.numbers = values;
        number = values.length;
        this.helper = new Card[number];
        mergeSort(0, number - 1);
//        for(int i=0; i<numbers.length; i++) {
//            System.out.println(numbers[i].value);
//        }
    }
    private void mergeSort(int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergeSort(low, middle);
            // Sort the right side of the array
            mergeSort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }
    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back to the original array
        while (i <= middle && j <= high) {
            if (helper[i].cardID <= helper[j].cardID) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.
    }

    //the search algorithm
    public String binarySearch(Card arr[], int l, int r, int val, String suit) {
        double cardID = getID(val, suit);
        if (r>=l) {
            int mid = l + (r - l)/2;

            if (arr[mid].cardID == cardID) {
                return "the " + val + " of " + suit + " is at position " + mid;
            }
            else if (arr[mid].cardID > cardID) {
                return binarySearch(arr, l, mid - 1, val, suit);
            }
            else {
                return binarySearch(arr, mid + 1, r, val, suit);
            }
        }
        return "that card is not in your hand";
    }

    public static double getID(int val, String suit) {
        Card a[] = allCards;
        for (int i=0; i<a.length; i++) {
            if (a[i].value == val && a[i].suit.equals(suit)) {
                return a[i].cardID;
            }
        }
        return -1;
    } //used in binarySearch to get the cardID from val and suit (so you don't have to enter it manually as a parameter
      //for binarySearch

}

public class Runner {

    public static void main(String[] args) {
        Deck d = new Deck(true);
        d.shuffleDeck();

        d.dealHand(21);
        d.listHand();

//        d.bubbleSortHand();
//        d.selectionSortHand();
        d.mSort(d.hand);
        d.listHand();

        System.out.println(d.binarySearch(d.hand, 0, 20, 6, "diamonds"));
        //for binary search, l is the leftmost position in the array (0), r is the rightmost position, val and suit are
        //those of the card being searched for


    }

}

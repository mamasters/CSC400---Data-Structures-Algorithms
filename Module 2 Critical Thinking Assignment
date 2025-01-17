import java.util.*;

public class Bag<T> implements Iterable<T> {

    private ArrayList<T> items;

    public Bag() {
        items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    public boolean contains(T item) {
        return items.contains(item);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void merge(Bag<T> other) {
        items.addAll(other.items);
    }

    public Bag<T> distinct() {
        Bag<T> distinct = new Bag<>();
        Set<T> seen = new HashSet<>();

        for (T item : items) {
            if (seen.add(item)) {
                distinct.add(item);
            }
        }
        return distinct;
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
}

import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        // creating some tokens for the cryptoWallet Bag.
        String token1 = "BTC";
        String token2 = "ETH";
        String distinctToken = "XRP";

        // creating some currencies for fiatWallet Bag.
        String fiat1 = "USD";
        String fiat2 = "EUR";
        String distinctFiat = "GBP";

        // creating the cryptoWallet Bag.
        Bag<String> cryptoWallet = new Bag<>();

        // creating the fiatWallet Bag.
        Bag<String> fiatWallet = new Bag<>();

        // adding 2 of each token to the cryptoWallet.
        for (int i = 0; i < 2; i++) {
            cryptoWallet.add(token1);
            cryptoWallet.add(token2);
        }

        // adding distinct element to cryptoWallet.
        cryptoWallet.add(distinctToken);

        // adding 2 of each fiat to the fiatWallet Bag.
        for (int i = 0; i < 2; i++) {
            fiatWallet.add(fiat1);
            fiatWallet.add(fiat2);
        }

        // adding distinct element to fiatWallet.
        fiatWallet.add(distinctFiat);

        // printing the size of each bag.
        System.out.printf("The Crypto Wallet contains %d items.\n", cryptoWallet.size());
        System.out.printf("The Fiat Wallet contains %d items.\n", fiatWallet.size());

        // merging cryptoWallet with fiatWallet.
        cryptoWallet.merge(fiatWallet);

        // printing the contents of the merged wallets.
        System.out.println("The contents of the merged bags:");
        for (String item : cryptoWallet ) {
            System.out.printf("%s\n", item);
        }

        // Creating a new bag containing only the distinct elements from the merged bags.
        Bag<String> distinct = cryptoWallet.distinct();

        // printing the contents of the distinct bag.
        System.out.println("The contents of the distinct bag:");
        for (String item : distinct ) {
            System.out.printf("%s\n", item);
        }
    }
}

package Associator;

public class FrequentItem implements Comparable<FrequentItem> {
    int score;
    String item;

    public FrequentItem(int score, String item) {
        this.score = score;
        this.item = item;
    }

    @Override
    public int compareTo(FrequentItem o) {
        return score < o.score ? 1 : score > o.score ? -1 : 0;
    }

    public int getScore() {
        return score;
    }

    public String getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!FrequentItem.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final FrequentItem other = (FrequentItem) obj;
        return (other.getItem().equals(getItem()));
    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }
}
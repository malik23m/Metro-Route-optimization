import java.util.ArrayList;
import java.util.HashMap;

public class Heap<T extends Comparable<T>> {
    private final ArrayList<T> data = new ArrayList<>();
    private final HashMap<T, Integer> map = new HashMap<>();

    public void add(T item) {
        data.add(item);
        map.put(item, this.data.size() - 1);
        upheapify(data.size() - 1);
    }

    private void upheapify(int ci) {
        int pi = (ci - 1) / 2;
        if (ci > 0 && isLarger(data.get(ci), data.get(pi)) > 0) {
            swap(pi, ci);
            upheapify(pi);
        }
    }

    private void swap(int i, int j) {
        T ith = data.get(i);
        T jth = data.get(j);

        data.set(i, jth);
        data.set(j, ith);
        map.put(ith, j);
        map.put(jth, i);
    }

    public void display() {
        System.out.println(data);
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public T remove() {
        swap(0, this.data.size() - 1);
        T rv = this.data.remove(this.data.size() - 1);
        map.remove(rv);
        if (!this.data.isEmpty()) {
            downheapify(0);
        }
        return rv;
    }

    private void downheapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int mini = pi;

        if (lci < this.data.size() && isLarger(data.get(lci), data.get(mini)) > 0) {
            mini = lci;
        }

        if (rci < this.data.size() && isLarger(data.get(rci), data.get(mini)) > 0) {
            mini = rci;
        }

        if (mini != pi) {
            swap(mini, pi);
            downheapify(mini);
        }
    }

    public T get() {
        return this.data.get(0);
    }

    public int isLarger(T t, T o) {
        return t.compareTo(o);
    }

    public void updatePriority(T item) {
        int index = map.get(item);
        upheapify(index);
        downheapify(index);
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        heap.add(10);
        heap.add(20);
        heap.add(15);
        heap.add(30);
        heap.add(40);

        heap.display();

        System.out.println("Removed: " + heap.remove());
        heap.display();

        System.out.println("Top: " + heap.get());
        heap.display();
    }
}

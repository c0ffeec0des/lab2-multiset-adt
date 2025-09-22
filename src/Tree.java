import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private Integer root;
    private List<Tree> subtrees;

    public Tree() {
        this.root = null;
        this.subtrees = new ArrayList<>();
    }

    public Tree(Integer root, List<Tree> subtrees) {
        this.root = root;
        this.subtrees = (subtrees == null) ? new ArrayList<>() : subtrees;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        if (isEmpty()) return 0;
        int total = 1;
        for (Tree t : subtrees) {
            total += t.size();
        }
        return total;
    }

    public int count(int item) {
        if (isEmpty()) return 0;
        int num = (root == item) ? 1 : 0;
        for (Tree t : subtrees) {
            num += t.count(item);
        }
        return num;
    }

    public boolean contains(int item) {
        if (isEmpty()) return false;
        if (root == item) return true;
        for (Tree t : subtrees) {
            if (t.contains(item)) return true;
        }
        return false;
    }

    public void insert(int item) {
        if (isEmpty()) {
            this.root = item;
        } else if (subtrees.isEmpty()) {
            subtrees.add(new Tree(item, new ArrayList<>()));
        } else {
            Random rand = new Random();
            int choice = rand.nextInt(3) + 1; // 1, 2, or 3
            if (choice == 3) {
                subtrees.add(new Tree(item, new ArrayList<>()));
            } else {
                int idx = rand.nextInt(subtrees.size());
                subtrees.get(idx).insert(item);
            }
        }
    }

    public boolean deleteItem(int item) {
        if (isEmpty()) {
            return false;
        } else if (root == item) {
            deleteRoot();
            return true;
        } else {
            for (int i = 0; i < subtrees.size(); i++) {
                Tree subtree = subtrees.get(i);
                boolean deleted = subtree.deleteItem(item);
                if (deleted) {
                    if (subtree.isEmpty()) {
                        subtrees.remove(i);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private void deleteRoot() {
        if (subtrees.isEmpty()) {
            root = null;
        } else {
            Tree chosen = subtrees.remove(subtrees.size() - 1);
            this.root = chosen.root;
            this.subtrees.addAll(chosen.subtrees);
        }
    }
}

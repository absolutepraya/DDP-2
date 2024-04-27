import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class WarriorList<Warrior> {
    private List<Warrior> warriors = new ArrayList<>();
    private Queue<Warrior> fallenWarriors = new LinkedList<>();

    public void addWarrior(Warrior warrior) {
        // Add warrior ke warriors ArrayList
        warriors.add(warrior);
    }

    public void removeWarrior(Warrior warrior) {
        // Menghapus warrior dari warriors ArrayList
        warriors.remove(warrior);
    }

    public List<Warrior> getWarriors() {
        // Return warriors ArrayList
        return warriors;
    }

    public void addFallenWarrior(Warrior warrior) {
        // Menambah warrior ke fallenWarriors PriorityQueue
        fallenWarriors.add(warrior);
    }

    public Queue<Warrior> getFallenWarriors() {
        // Return fallenWarriors PriorityQueue
        return fallenWarriors;
    }
}

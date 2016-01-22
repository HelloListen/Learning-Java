package enums;

import java.util.Random;

/**
 * Created by zhangbin on 16/1/22.
 */
interface Item {
    Outcome compete(Item it);

    Outcome eval(Paper p);

    Outcome eval(Scissors s);

    Outcome eval(Rock r);
}

class Paper implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return Outcome.DRAW;
    }

    public Outcome eval(Scissors s) {
        return Outcome.LOSE;
    }

    public Outcome eval(Rock r) {
        return Outcome.WIN;
    }
}

class Scissors implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return Outcome.WIN;
    }

    public Outcome eval(Scissors s) {
        return Outcome.DRAW;
    }

    public Outcome eval(Rock r) {
        return Outcome.LOSE;
    }
}

class Rock implements Item {
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    public Outcome eval(Paper p) {
        return Outcome.LOSE;
    }

    public Outcome eval(Scissors s) {
        return Outcome.WIN;
    }

    public Outcome eval(Rock e) {
        return Outcome.DRAW;
    }
}

public class RoShamBo1 {
    static final int size = 20;
    private static Random rand = new Random(47);

    public static Item newItem() {
        switch (rand.nextInt(3)) {
            default:
            case 0:
                return new Scissors();
            case 1:
                return new Paper();
            case 2:
                return new Rock();
        }
    }

    public static void match(Item a, Item b) {
        System.out.println(a + " vs. " + b + " : " + a.compete(b));
    }

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            match(newItem(), newItem());
        }
    }
}

package effectivejava;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbin on 16/2/3.
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        if (type == null) {
            throw new NullPointerException("Type is null");
        }
        favorites.put(type, type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}

enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);
    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }
}

class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
        double num = Operation.DIVIDE.apply(10, 20);
        System.out.println(num);
    }
}

enum Operation {
    PLUS {
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        double apply(double x, double y) {
            return x / y;
        }
    };

    abstract double apply(double x, double y);
}

enum Operations {
    PLUS("+") {
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operations(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}

enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    private static final int HOURS_PER_SHIFT = 8;

    double pay(double hoursWorked, double payRate) {
        double basePay = hoursWorked * payRate;
        double overtimePay;
        switch (this) {
            case SATURDAY:
            case SUNDAY:
                overtimePay = hoursWorked * payRate / 2;
                break;
            default:
                overtimePay = hoursWorked <= HOURS_PER_SHIFT ? 0 : (hoursWorked - HOURS_PER_SHIFT) * payRate / 2;
                break;
        }
        return basePay + overtimePay;
    }
}

enum PayrollDay2 {
    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private static final int HOURS_PER_SHIFT = 8;


    private final PayType payType;

    PayrollDay2(PayType payType) {
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate) {
        return payType.pay(hoursWorked, payRate);
    }

    private enum PayType {
        WEEKDAY {
            double overtimePay(double hours, double payRate) {
                return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            double overtimePay(double hours, double payRate) {
                return hours * payRate / 2;
            }
        };

        abstract double overtimePay(double hours, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }
}

interface Operation1 {
    double apply(double x, double y);
}

enum ExtendedOperation implements Operation1 {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation1> void test(Class<T> opSet, double x, double y) {
        for (Operation1 op : opSet.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}

enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase src;
        private final Phase dst;

        Transition(Phase src, Phase dst) {
            this.src = src;
            this.dst = dst;
        }

        private static final Map<Phase, Map<Phase, Transition>> m = new EnumMap<>(Phase.class);

        static {
            for (Phase p : Phase.values()) {
                m.put(p, new EnumMap<>(Phase.class));
            }
            for (Transition trans : Transition.values()) {
                m.get(trans.src).put(trans.dst, trans);
            }
        }

        public static Transition from(Phase src, Phase dst) {
            return m.get(src).get(dst);
        }
    }
}
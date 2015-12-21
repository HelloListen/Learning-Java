package demo;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.*;

/**
 * Created by zhangbin on 15/12/21.
 */
public class InterfaceVsIterator {
    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + "");
        }
        System.out.println();
    }

    public static void display(Collection<Pet> pets) {
        for (Pet p : pets) {
            System.out.println(p.id() + ":" + p + "");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Pet> petList = Pets.arrayList(8);
        Set<Pet> petSet = new HashSet<>(petList);
        Map<String, Pet> petMap = new LinkedHashMap<>();
        String[] names = ("Ralph,Eric,Robin,lacey, " + "Briney ,Sam, Spot, Fluffy").split(",");
        for (int i = 0; i < names.length; i++) {
            petMap.put(names[i], petMap.get(i));
        }
        display(petList);
        display(petSet);
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println(petMap);
        System.out.println(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());
    }
}

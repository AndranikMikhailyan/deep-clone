package edu.java.test;

import java.lang.reflect.Field;
import java.util.*;

public class CopyUtils {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        List<String> list = new ArrayList<>();
        list.add("jbnweubvir");
        list.add("hkbvhuwebvreub");
        String testString = new String("test");
        list.add(testString);
        Address address = new Address("Lenina", 179);
        Map<String, String> map = new HashMap<>();
        map.put("111", "111");
        Man man = new Man("Name", 12, list, address, map);
        man.setMan(man);

        Man deepCopy = CopyUtils.deepCopy(man);

        deepCopy.setName("new name");
        map.put("111", "222");

        System.out.println(man);
        System.out.println(deepCopy);

    }

    public static <T> T deepCopy(T from) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> objectClass = from.getClass();
        Object to = Class.forName(from.getClass().getName()).newInstance();

        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (isPrimitive(field.getType())) {
                    field.set(to, field.get(from));
                } else if (isCollection(field.get(from))) {
                    Collection original = Collection.class.cast(field.get(from));
                    Collection copiedCollection = Collection.class.cast(field.get(from).getClass().newInstance());
                    for (Object o : original) {
                        if (isPrimitive(o.getClass())) {
                            copiedCollection.add(o);
                        } else {
                            copiedCollection.add(deepCopy(o));
                        }
                    }
                    field.set(to, copiedCollection);
                } else if (isMap(field.get(from))) {
                    Map original = Map.class.cast(field.get(from));
                    Map copiedMap = Map.class.cast(field.get(from).getClass().newInstance());
                    original.forEach((key, value) -> {
                        try {
                            if (!isPrimitive(key.getClass())) {
                                key = deepCopy(key);
                            }
                            if (!isPrimitive(value.getClass())) {
                                key = deepCopy(value);
                            }
                            copiedMap.put(key, value);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    });
                    field.set(to, copiedMap);
                } else {
                    if (field.get(from) != from) {
                        field.set(to, deepCopy(field.get(from)));
                    } else {
                        field.set(to, to);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return (T) to;
    }

    private static boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive()
                || clazz == String.class
                || clazz == Boolean.class
                || clazz == Byte.class
                || clazz == Short.class
                || clazz == Character.class
                || clazz == Integer.class
                || clazz == Float.class
                || clazz == Double.class
                || clazz == Long.class;
    }

    private static boolean isCollection(Object object) {
        return object instanceof Collection<?>;
    }


    private static boolean isMap(Object object) {
        return object instanceof Map<?, ?>;
    }
}

package th.co.geniustree.internship.dataclient.xx;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyContainer {
    public static void main(String[] args) {

        List<Class> classes = scanClassPath();

        List<Class> sevicesClass = findClassAnnoService(classes);

        Map<String, Object> serviceRegistry = createInstance(sevicesClass);

        serviceRegistry.values().stream().forEach(e -> setUpAutoWire(e, serviceRegistry));

    }

    private static Map<String, Object> createInstance(List<Class> sevicesClass) {
        System.out.println("------ Create instance -----");

        Map<String, Object> serviceRegistry = new HashMap<>();

        for (int i = 0; i < sevicesClass.size(); i++) {
            Object inS = newInstance(sevicesClass.get(i));
            serviceRegistry.put(sevicesClass.get(i).getName(), inS);
            if (sevicesClass.get(i).getInterfaces().length > 0) {
                serviceRegistry.put(sevicesClass.get(i).getInterfaces()[0].getName(), inS);
            }
        }
        System.out.println(serviceRegistry);
        System.out.println();
        return serviceRegistry;
    }

    private static List<Class> findClassAnnoService(List<Class> classes) {
        // find only class that annotate with Service
        System.out.println("----- Filter classpath have Annotation \"Service\" -----");
        List<Class> sevicesClass = classes.stream()
                .filter(e -> (e.getDeclaredAnnotation(Service.class) != null))
                .collect(Collectors.toList());
        sevicesClass.stream().forEach(System.out::println);
        System.out.println();
        return sevicesClass;
    }

    private static List<Class> scanClassPath() {
        //Scan class path for each class start with th.co.geniustree.internship.dataclient
        System.out.println("----- Scan classpath start with \"th.co.geniustree.internship.dataclient\" -----");
        List<Class> classes = new FastClasspathScanner().scan().getNamesOfAllClasses()
                .stream()
                .filter(e -> e.startsWith("th.co.geniustree.internship.dataclient"))
                .map(MyContainer::mapToClass).collect(Collectors.toList());
        classes.stream().forEach(System.out::println);
        System.out.println();
        return classes;
    }

    private static void setUpAutoWire(Object instance, Map<String, Object> serviceRegistry) {

        List<Field> field = Stream.of(instance.getClass().getDeclaredFields())
                .map(e -> {
                    e.setAccessible(true);
                    return e;
                })
                .filter(f -> f.getDeclaredAnnotation(AutoWired.class) != null)
                .collect(Collectors.toList());


        if (field.size() != 0) {
            for (int i = 0; i < field.size(); i++) {

                Object objects = serviceRegistry.get(field.get(i).getType().getName());

                try {

                    field.get(i).set(instance, objects);
                    System.out.println("----- Set instance -----");
                } catch (Exception e) {
                    new RuntimeException(e);
                }
            }

            System.out.println("--- Run test ---");
            run(instance);
        }
    }

    private static void run(Object instance) {
        try {
            Method test = instance.getClass().getDeclaredMethod("test", null);
            System.out.println("running method test()");
            test.invoke(instance, null); //against
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static Class mapToClass(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Object newInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

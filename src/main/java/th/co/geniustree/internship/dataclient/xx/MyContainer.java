package th.co.geniustree.internship.dataclient.xx;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyContainer {
    public static void main(String[] args) {
        //Scan class path for each class start with th.co.geniustree.internship.dataclient
        System.out.println("----- Scan classpath start with \"th.co.geniustree.internship.dataclient\" -----");
        List<Class> classes = new FastClasspathScanner().scan().getNamesOfAllClasses()
                .stream()
                .filter(e -> e.startsWith("th.co.geniustree.internship.dataclient"))
                .map(MyContainer::mapToClass).collect(Collectors.toList());

        classes.stream().forEach(System.out::println);

        System.out.println();
        System.out.println("----- Filter classpath have Annotation \"Service\" -----");

        // find only class that annotate with Service
        List<Class> sevicesClass = classes.stream()
                .filter(e -> e.getDeclaredAnnotation(Service.class) != null)
                .collect(Collectors.toList());

        sevicesClass.stream().forEach(System.out::println);

        System.out.println();
        System.out.println("------ Create instance -----");

// create instance of each class and put it in Map<className,instance>
        Map<String, List<Object>> serviceRegistry = sevicesClass.stream()
                .map(e -> newInstance(e))
                .collect(Collectors.groupingBy(e -> e.getClass().getName()));

        System.out.println("registry:----------------");
        System.out.println(serviceRegistry);
        System.out.println();
        serviceRegistry.values().stream().forEach(e -> setUpAutoWire(e.get(0), serviceRegistry));
    }

    private static void setUpAutoWire(Object instance, Map<String, List<Object>> serviceRegistry) {

        Optional<Field> field = Stream.of(instance.getClass().getDeclaredFields())
                .map(e -> {
                    e.setAccessible(true);
                    return e;
                })
                .filter(f -> f.getDeclaredAnnotation(AutoWired.class) != null)
                .findAny();
        if (field.isPresent()) {


            List<Object> objects = serviceRegistry.get(field.get().getType().getName());
            try {
                field.get().set(instance, objects.get(0));
                System.out.println("----- Set instance -----");
            } catch (IllegalAccessException e) {
                new RuntimeException(e);
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

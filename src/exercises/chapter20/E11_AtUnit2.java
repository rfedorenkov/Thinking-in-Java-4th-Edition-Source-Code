package exercises.chapter20;

import net.mindview.atunit.*;
import net.mindview.util.*;

import java.io.File;
import java.lang.reflect.*;
import java.util.*;

import static net.mindview.util.Print.*;

/**
 * Exercise 11
 * Add an @TestNote annotation to @Unit, so that the
 * accompanying note is simply displayed during testing.
 * {RunByHand}
 */
public class E11_AtUnit2 implements ProcessFiles.Strategy {
    static Class<?> testClass;
    static List<String> failedTests = new ArrayList<>();
    static long testsRun = 0;
    static long failures = 0;

    public static void main(String[] args) throws Exception {
        ClassLoader.getSystemClassLoader()
                .setDefaultAssertionStatus(true); // Enable asserts
        new ProcessFiles(new E11_AtUnit2(), "class").start(args);
        if (failures == 0)
            print("OK (" + testsRun + " tests)");
        else {
            print("(" + testsRun + " tests)");
            print("\n>>> " + failures + " FAILURE" +
                    (failures > 1 ? "S" : "") + " <<<");
            for (String failed : failedTests)
                print("  " + failed);
        }
    }

    @Override
    public void process(File cFile) {
        try {
            String cName = ClassNameFinder.thisClass(
                    BinaryFile.read(cFile));
            if (!cName.contains("."))
                return; // Ignore unpackaged classes
            testClass = Class.forName(cName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TestMethods testMethods = new TestMethods();
        Method creator = null;
        Method cleanup = null;
        Map<Method, String> testNotesMap = new HashMap<>();
        for (Method m : testClass.getDeclaredMethods()) {
            testMethods.addIfTestMethod(m);
            if (m.getAnnotation(TestNote.class) != null) {
                if (m.getAnnotation(Test.class) == null)
                    throw new RuntimeException("@TestNote method must be a @Test method too");
                testNotesMap.put(m, m.getAnnotation(TestNote.class).value());
            }

            if (creator == null)
                creator = checkForCreatorMethod(m);
            if (cleanup == null)
                cleanup = checkForCleanupMethod(m);
        }
        if (testMethods.size() > 0) {
            if (creator == null)
                try {
                    if (!Modifier.isPublic(testClass
                            .getDeclaredConstructor().getModifiers())) {
                        print("Error: " + testClass +
                                " default constructor must be public");
                        System.exit(1);
                    }
                } catch (NoSuchMethodException e) {
                    // Synthesized default constructor; OK
                }
            print(testClass.getName());
        }
        for (Method m : testMethods) {
            printnb("  . " + m.getName() + " ");
            if (testNotesMap.containsKey(m))
                printnb(" : " + testNotesMap.get(m) + " ");
            try {
                Object testObject = createTestObject(creator);
                boolean success = false;
                try {
                    if (m.getReturnType().equals(boolean.class))
                        success = (Boolean) m.invoke(testObject);
                    else {
                        m.invoke(testObject);
                        success = true; // If no assert fails
                    }
                } catch (InvocationTargetException e) {
                    // Actual exception is inside e:
                    print(e.getCause());
                }
                print(success ? "" : "(failed)");
                testsRun++;
                if (!success) {
                    failures++;
                    failedTests.add(testClass.getName() +
                            ": " + m.getName());
                }
                if (cleanup != null)
                    cleanup.invoke(testObject, testObject);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class TestMethods extends ArrayList<Method> {
        void addIfTestMethod(Method m) {
            if (m.getAnnotation(Test.class) == null)
                return;
            if (!(m.getReturnType().equals(boolean.class) ||
                    m.getReturnType().equals(void.class)))
                throw new RuntimeException("@Test method must return boolean or void");
            m.setAccessible(true); // In case it's private, etc.
            add(m);
        }
    }

    private static Method checkForCreatorMethod(Method m) {
        if (m.getAnnotation(TestObjectCreate.class) == null)
            return null;
        if (!m.getReturnType().equals(testClass))
            throw new RuntimeException("@TestObjectCreate " +
                    "must return instance of Class to be tested");
        if ((m.getModifiers() & Modifier.STATIC) < 1)
            throw new RuntimeException("@TestObjectCreate must be static.");
        m.setAccessible(true);
        return m;
    }

    private static Method checkForCleanupMethod(Method m) {
        if (m.getAnnotation(TestObjectCleanup.class) == null)
            return null;
        if (!m.getReturnType().equals(void.class))
            throw new RuntimeException("@TestObjectCleanup " +
                    "must return void");
        if ((m.getModifiers() & Modifier.STATIC) < 1)
            throw new RuntimeException("@TestObjectCleanup " +
                    "must be static.");
        if (m.getParameterTypes().length == 0 || m.getParameterTypes()[0] != testClass)
            throw new RuntimeException("@TestObjectCleanup " +
                    "must take an argument of the tested type.");
        m.setAccessible(true);
        return m;
    }

    private static Object createTestObject(Method creator) {
        if (creator != null) {
            try {
                return creator.invoke(testClass);
            } catch (Exception e) {
                throw new RuntimeException("Couldn't run " +
                        "@TestObject (creator) method.");
            }
        } else { // Use the default constructor:
            try {
                return testClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Couldn't create a test object. " +
                        "Try using a @TestObject method.");
            }
        }
    }
}
/* Output:
exercises.chapter20.E11_HashMapTestNote
  . _get  : Test map.get(Object key) method
  . _isEmpty  : Test map.isEmpty() method
  . _remove  : Test map.remove(Object key) method
  . _size  : Test map.size() method
  . _clear  : Test map.clear() method
  . _containsKey  : Test map.containsKey(Object key) method
  . _containsValue  : Test map.containsValue(Object value) method
OK (7 tests)
 */
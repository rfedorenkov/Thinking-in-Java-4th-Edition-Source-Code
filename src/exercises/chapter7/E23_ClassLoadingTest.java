package exercises.chapter7;

/**
 * Prove that class loading takes place only once
 * and may be caused by either the creation of the first
 * instance of that class or by accessing a static member.
 */
class LoadTest {
    static int x = load();

    int i;

    LoadTest() {
        System.out.println("LoadTest constructor. i = " + i);
        i = 47;
    }

    LoadTest(int i) {
        System.out.println("LoadTest constructor. i = " + i);
    }

    static int load() {
        System.out.println("static LoadTest.load()");
        return 47;
    }
}

public class E23_ClassLoadingTest {
    public static void main(String[] args) {
        System.out.println("main()");
        test1();
        // test2();
    }

    public static void test1() {
        System.out.println("test1()");
        System.out.println("Create LoadTest() part 1");
        LoadTest test1 = new LoadTest();
        System.out.println("Create LoadTest() part 2");
        LoadTest test2 = new LoadTest();
    }

    public static void test2() {
        System.out.println("test2()");
        System.out.println("Call static member, part 1");
        System.out.println(LoadTest.x);
        System.out.println("Call static member, part 2");
        System.out.println(LoadTest.x);
    }
}
/* Output:
main()
test1()
Create LoadTest() part 1
static LoadTest.load()
LoadTest constructor. i = 0
Create LoadTest() part 2
LoadTest constructor. i = 0

main()
test2()
Call static member, part 1
static LoadTest.load()
47
Call static member, part 2
47
 */
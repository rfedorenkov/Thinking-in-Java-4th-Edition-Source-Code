package exercises.chapter5;

/**
 * Exercise 18
 * Create objects to attach to the array of references for Exercise 17.
 */
class InitTest2 {
    String s;

    InitTest2(String s) {
        System.out.println("InitTest2(" + s + ")");
        this.s = s;
    }
}

public class E18_ObjectReferences2 {
    public static void main(String[] args) {
        InitTest2[] arr = new InitTest2[5];
        for (int i = 0; i < arr.length; i++)
            arr[i] = new InitTest2("#" + (i + 1));
    }
}
/* Output:
InitTest2(#1)
InitTest2(#2)
InitTest2(#3)
InitTest2(#4)
InitTest2(#5)
 */
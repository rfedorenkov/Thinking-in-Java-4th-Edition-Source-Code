package initialization;

// {CompileTimeError} (Won't compile)
public class OverloadingVarargs2 {
    static void f(float i, Character... args) {
        System.out.println("first");
    }

    static void f(Character... args) {
        System.out.println("second");
    }

    public static void main(String[] args) {
        f(1, 'a');
        // f('a', 'b');
    }
}
/*
java: reference to f is ambiguous
  both method f(float,java.lang.Character...) in initialization.OverloadingVarargs2 and method f(java.lang.Character...) in initialization.OverloadingVarargs2 match
 */
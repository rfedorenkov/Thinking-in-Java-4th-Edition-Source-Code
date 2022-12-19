package reusing;

/**
 * {CompileTimeError} (Won't compile)
 */
class Lisa extends Homer {
    //! @Override // method does not override or implement a method from a supertype
    void doh(Milhouse m) {
        System.out.println("doh(Milhouse)");
    }
}
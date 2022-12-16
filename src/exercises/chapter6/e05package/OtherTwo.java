package exercises.chapter6.e05package;

public class OtherTwo {
    private String a = "private";
    String b = "default";
    protected String c = "protected";
    public String d = "public";

    private String getA() {
        return a;
    }

    String getB() {
        return b;
    }

    protected String getC() {
        return c;
    }

    public String getD() {
        return d;
    }
}
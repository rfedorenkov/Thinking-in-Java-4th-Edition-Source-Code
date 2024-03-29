package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Collections.fill() & Collections.nCopies() methods.
 */
class StringAddress {
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s;
    }
}

public class FillingLists {
    public static void main(String[] args) {
        List<StringAddress> list = new ArrayList<>(
                Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("World!"));
        System.out.println(list);
    }
}
/* Output:
[containers.StringAddress@75bd9247 Hello, containers.StringAddress@75bd9247 Hello, containers.StringAddress@75bd9247 Hello, containers.StringAddress@75bd9247 Hello]
[containers.StringAddress@4411d970 World!, containers.StringAddress@4411d970 World!, containers.StringAddress@4411d970 World!, containers.StringAddress@4411d970 World!]
 */
package exercises.chapter19;

import java.util.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 5
 * Modify control/VowelsAndConsonants.java so that it uses
 * three enum types: VOWEL, SOMETIMES_A_VOWEL, and CONSONANT.
 * The enum constructor should take the various letters that
 * describe that particular category. Hint: Use varargs, and
 * remember that varargs automatically creates an array for you.
 */
enum Letter {
    VOWEL('a', 'e', 'i', 'o', 'u') {
        @Override
        public String toString() {
            return "vowel";
        }
    },
    SOMETIMES_A_VOWEL('y', 'w') {
        @Override
        public String toString() {
            return "Sometimes a vowel";
        }
    },
    CONSONANT('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k',
            'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z') {
        @Override
        public String toString() {
            return "consonant";
        }
    };

    private Set<Character> characters = new HashSet<>();

    private Letter(Character... c) {
        characters.addAll(Arrays.asList(c));
    }

    public static Letter getType(char c) {
        if (VOWEL.characters.contains(c))
            return VOWEL;
        else if (SOMETIMES_A_VOWEL.characters.contains(c))
            return SOMETIMES_A_VOWEL;
        else
            return CONSONANT;
    }
}

public class E05_VowelsAndConsonants2 {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 100; i++) {
            char c = (char) (rand.nextInt(26) + 'a');
            printnb(c + ", " + (int) c + ": ");
            print(Letter.getType(c));
        }
    }
}
/* Output:
y, 121: Sometimes a vowel
n, 110: consonant
z, 122: consonant
b, 98: consonant
r, 114: consonant
n, 110: consonant
y, 121: Sometimes a vowel
g, 103: consonant
c, 99: consonant
f, 102: consonant
o, 111: vowel
w, 119: Sometimes a vowel
z, 122: consonant
n, 110: consonant
t, 116: consonant
c, 99: consonant
q, 113: consonant
r, 114: consonant
g, 103: consonant
s, 115: consonant
e, 101: vowel
g, 103: consonant
z, 122: consonant
m, 109: consonant
m, 109: consonant
j, 106: consonant
m, 109: consonant
r, 114: consonant
o, 111: vowel
e, 101: vowel
s, 115: consonant
u, 117: vowel
e, 101: vowel
c, 99: consonant
u, 117: vowel
o, 111: vowel
n, 110: consonant
e, 101: vowel
o, 111: vowel
e, 101: vowel
d, 100: consonant
l, 108: consonant
s, 115: consonant
m, 109: consonant
w, 119: Sometimes a vowel
h, 104: consonant
l, 108: consonant
g, 103: consonant
e, 101: vowel
a, 97: vowel
h, 104: consonant
k, 107: consonant
c, 99: consonant
x, 120: consonant
r, 114: consonant
e, 101: vowel
q, 113: consonant
u, 117: vowel
c, 99: consonant
b, 98: consonant
b, 98: consonant
k, 107: consonant
i, 105: vowel
n, 110: consonant
a, 97: vowel
m, 109: consonant
e, 101: vowel
s, 115: consonant
b, 98: consonant
t, 116: consonant
w, 119: Sometimes a vowel
h, 104: consonant
k, 107: consonant
j, 106: consonant
u, 117: vowel
r, 114: consonant
u, 117: vowel
k, 107: consonant
z, 122: consonant
p, 112: consonant
g, 103: consonant
w, 119: Sometimes a vowel
s, 115: consonant
q, 113: consonant
p, 112: consonant
z, 122: consonant
d, 100: consonant
y, 121: Sometimes a vowel
c, 99: consonant
y, 121: Sometimes a vowel
r, 114: consonant
f, 102: consonant
j, 106: consonant
q, 113: consonant
a, 97: vowel
h, 104: consonant
x, 120: consonant
x, 120: consonant
h, 104: consonant
v, 118: consonant
 */
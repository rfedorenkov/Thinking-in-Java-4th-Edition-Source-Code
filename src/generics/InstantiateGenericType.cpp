// C++, not Java!

template<class T> class Foo {
    T x; // Create a field of type T
    T* y; // Pointer to T
public:
    // Initialize the pointer:
    Foo() { y = new T(); }
}

class Bar {};

int main() {
    Foo<Bar> fb;
    Foo<int> fi; // ... adn it works with primitives
}
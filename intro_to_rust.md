# Intro to Rust
* Speaker: Nathan Stocks (Github)

## Rust
* systems language
* safety, concurrency, speed
* Rust is about 9 years old, 4 years of offical releases

## Mozilla
* Firefox was rewritten in Rust because they were sick of C++
* Safe && Reliable
* Entire classes of bugs are elimiated with grautanteed thread safety
* Tests and documentation

## Editions
* 2018 release has breaking changes, but always supported
* 6 week releases between then too

## Compiler
* The rust compiler is very strict and has useful error messages

## Cargo
* A package manager for a systems level language!
* Build system
* Test runner
* Documentation generator

## Type
* Strongly typed, but it is left out in many cases
* Varaibles are immutable
* `mut` is used to specifically call a varaible mutable

## Scope
* varaibles are available in blocks and nested blocks
* values are dropped out of scope when block ends
* conditional evaluation is handled at run time, so the compiler will not evaluate it

## Functions
* `fn` (pronounced fun)
* snake case for function names like `do_stuff`
* order does not matter because it is compiled
* `->` is for function return type
* implicit returns but the shorter way is always peferred

## Scalar Types
* integers
  * unsigned `u8` - `u128`
  * signed `i8` - `i128`. Defaults to `i32`
  * `usize` and `isize` is the max size available
* Floting Point
  * `f32` and `f64`
* Boolean
  * bool and `true` and `false`
* Character type
  * could be a unicode, emoji, whatever
  * 4 bytes
  * single quotes defined
  * Strings are utf-8 and different
* Compound Types
  * Tuples
    * `let info = (1, 3.3, 999;` accessed at `info.1`
  * Array
    * square brackets, limited to 32 bytes

* Control Flow
  ```
  if num == 5 {
    msg = "five";
  } else if {
    ...
  }
  ````

* Loop
  * break will end the loop
  * continue

## Strings
* There are 6 types of strings. There be dragons!
* We care about the graphemes

## Inheritance
* No struct Inheritance
* compsition over inheritance
* Trait is much like an interface
```
impl Noisy for Redfox {
  ...
}
```

* Enum
```
enum Color {
  Red,
  Green,
  Blue(i32, String)
}
```

## Ownership
1. Each value has an owner. No value in memory it unowned
2. There is only one owner (though there is borrowing)
3. When the owner is out of scope the varaible is dropped
```
let s1 = String::from("abc");
let s2 = s1
```
* S1 cannot be used becaue the value has moved to S2
* `clone()` performs a copy and also copies the heap data and the pointer
* `copy` is used when the stack data is copied.

## Stack and Heap
* Stack is faster than heap
* No leaks, no dangling pointers
* Passing to a function will literally consume the value
* `&String` is a reference to a string, so it's borrowed as a refence instead of reassigned

# Compiler
* Learn to love the compiler and the error messages

# Docs / Resources
* rust-lang.org
* agileperception
* github.com/cleancut
# BareBones Interpreter

This is a humble BareBones interpreter written in Java and here's how it works.

## How It Works

To understand exactly how the interpreter is implemented, one has to first build up some theoretical basin to stand on. To allow computer to exectute the BareBones **code**, we have to first separate **code** -- a stream of characters -- into some structures that make sense to the machine.

Loking at the **code**, we can see that there can be found *three* types of syntactic "beasts":

1. **Code** itself, defined as collection of the "beasts" listed below;
2. **Statement** -- a single line command like `incr X;` or `clear X;` -- there are tree statements in total;
3. **While loop** that consists of three sub-parts (which we shall discuss later).

**Statement** has two parts: **operator** and **varname**.
**While loop** has three parts: **header**, inner **code**, and a terminating **end**.
**While loop's** **header** can also be split up into the constant part and **varname**.

```barebones
while X not 0 do;
    ... code ...
end;
```

In the example above, `while ... not 0 do;` is a constant part, and `X` is **varname** -- it will be different from case to case.

Now, using only the three "beasts", we can split any BareBones file into *recursive* sections:

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ ┌─────────────┐                            ┃
┃ │ clear X;  statement                      ┃    
┃ └─────────────┘                         ┏━━┻━━━┓
┃   incr X;                                 code  
┃   incr X;                               ┗━━┳━━━┛
┃   incr X;                                  ┃    
┃ ╔═══════════════════════╣ while ╠═══╗      ┃
┃ ║ while X not 0 do;                 ║      ┃
┃ ║   ┏━━━━━━━━━━━━━━━━━━━━━━━━┓      ║      ┃
┃ ║   ┃┌───────────┐        ┏━━┻━━━┓  ║      ┃
┃ ║   ┃│decr X;  statement    code    ║      ┃
┃ ║   ┃└───────────┘        ┗━━┳━━━┛  ║      ┃
┃ ║   ┗━━━━━━━━━━━━━━━━━━━━━━━━┛      ║      ┃
┃ ║ end;                              ║      ┃
┃ ╚═══════════════════════════════════╝      ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

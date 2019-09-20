#
# define compiler and compiler flag variables
#
JAVAC=/usr/bin/javac
JFLAGS = -g

all: Transaction.class CashRegister.class

CashRegister.class: CashRegister.java
	$(JAVAC) $(JFLAGS) CashRegister.java

Transaction.class: Transaction.java
	$(JAVAC) $(JFLAGS) Transaction.java

clean:
	rm -f *.class

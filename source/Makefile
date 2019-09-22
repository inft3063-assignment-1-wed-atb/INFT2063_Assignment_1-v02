#
# define compiler and compiler flag variables
#
JAVAC=/usr/bin/javac
JFLAGS = -g

jar: Transaction.class CashRegister.class Receipt.class
	jar -cfe My_Cash_Register.jar CashRegister Transaction.class CashRegister.class Receipt.class

all: Transaction.class CashRegister.class Receipt.class

CashRegister.class: CashRegister.java
	$(JAVAC) $(JFLAGS) CashRegister.java

Transaction.class: Transaction.java
	$(JAVAC) $(JFLAGS) Transaction.java

Receipt.class: Receipt.java
	$(JAVAC) $(JFLAGS) Receipt.java

clean:
	rm -f *.class

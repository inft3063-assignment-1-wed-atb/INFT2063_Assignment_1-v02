#
# define compiler and compiler flag variables
#
JAVAC=/usr/bin/javac
JFLAGS = -g

jar: Transaction.class CashRegister.class Receipt.class
	jar -cfe My_Cash_Register.jar CashRegister Transaction.class CashRegister.class Receipt.class Report.class loyaltyProgram.class UserAuthentication.class profile.class

all: Transaction.class CashRegister.class Receipt.class Report.class loyaltyProgram.class UserAuthentication.class profile.class

CashRegister.class: CashRegister.java
	$(JAVAC) $(JFLAGS) CashRegister.java

Transaction.class: Transaction.java
	$(JAVAC) $(JFLAGS) Transaction.java

Receipt.class: Receipt.java
	$(JAVAC) $(JFLAGS) Receipt.java

Report.class: Report.java
	$(JAVAC) $(JFLAGS) Report.java

loyaltyProgram.class: loyaltyProgram.java
	$(JAVAC) $(JFLAGS) loyaltyProgram.java

UserAuthentication.class: UserAuthentication.java
	$(JAVAC) $(JFLAGS) UserAuthentication.java

profile.class: profile.java
	$(JAVAC) $(JFLAGS) profile.java

clean:
	rm -f *.class

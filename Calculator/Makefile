JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Driver.java \
	Calculator.java \
	ButtonPanel.java \
	RadioPanel.java \
	NumberPanel.java \
	CalcMenuBar.java \
	CalcEngine.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

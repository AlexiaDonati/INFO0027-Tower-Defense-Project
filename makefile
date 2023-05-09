JC = javac

# Sources files
SOURCES = $(wildcard *.java)

# Output directory
BINDIR = bin

# Class where the main is
MAIN = Game

all: 
	$(JC) -d $(BINDIR) -cp bin:graphics.jar $(SOURCES)

run:
	cd $(BINDIR) && java -cp .:../graphics.jar $(MAIN)
	
clean:
	rm -f $(BINDIR)/*.class

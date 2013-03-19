# the "make all" is wrong because it compile 
# all class in ./src but not ./bin

all: \
	src/Judge.java \
	src/TetrisBlock.java \
	src/Viewer.java \
	src/Main.java \
	src/TetrisBot.java
	javac src/*.java -d ./bin

run:
	cd bin; java Main

clean:	
#	rm bin/*.class
	rm src/*.class


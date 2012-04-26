all: src/Judge.java src/TetrisBlock.java src/Viewer.java src/Main.java src/TetrisBot.java
	javac src/*.java -cp bin

clean:	
	rm bin/*


javac -cp ./src -d ./bin ./src/*.java
java -cp bin client 0 0 lowmem members 0
pause
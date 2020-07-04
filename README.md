# DEV TEST
> Author: Cwilik Dawid

Project based on maven as a multi module for faster management.

### Build and run

In main directory run command

``
mvn package
``

in each subfolder run command

``
java -jar /target/task.jar [arguments]
``

### Examples
* Task-one

    ``
    java -jar task-one/target/task.jar 1 10 20 20 2 5
    ``

* Task-two

    ``
    java -jar task-two/target/task.jar 1 2 10 7 5 3 6 6 13 0
    ``

* Task-three

    ````
    java -jar task-three/target/task.jar 3
    1 2
    2 3
    5 6
    ````
    where `3` is input size, and then put per line two values

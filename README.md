## куда смотреть: 
все лежит в папке src/com.company
PersonState.java - это тот самый enum в который зашиты поля для проверки в виде строк
Person.java - класс который имеет атрибут PersonState
Main.java - вот тут есть validate(Person person, PersonState state) и именно там используется рефлексия (import java.lang.reflect.Field;) - если хочется посмотреть как работает, запускать тоже тут_

## а что тут вообще происходит (пример максимально простой)
проверка всех указанных в enum для определенного PersonState полей на ne null
проверка всех указанных в enum полей для определенного PersonState типа int на >18 
проверка всех логических полей на соответствие истина 


# ToDo Demo
A basic ToDo app to demonstrate Android Architecture components using MVVM using Kotlin

# Features
  - LiveData :- To update UI in sync with underlying data changes
  - Room :-  To fetch from sqlite database
  - ViewModel :-  To maintain data across activity orientation/recreation

# Architecture
App is built based on below architecture

![App Architecture](https://github.com/harishtandale/todoDemo/blob/master/App_Architecture.png?raw=true?branch=master)
Where ,
  - "Activity / Fragment" :- is View responsible for Displaying UI
  - As name suggests ViewModel is ViewModel :- Responsible to store data being aware of component lifecycle
  - Model is the data class :- Holds the data

# Screenshots
| Main Screen        |   New Todo         |  Delete All Todos |
| ------------- |:-------------:| -----:|
|![App Architecture](https://github.com/harishtandale/todoDemo/blob/master/Screenshots/1_app_main_screen.jpg?raw=true?branch=master)       | ![App Architecture](https://github.com/harishtandale/todoDemo/blob/master/Screenshots/2_new_todo.jpg?raw=true?branch=master) | ![App Architecture](https://github.com/harishtandale/todoDemo/blob/master/Screenshots/3_delete_all.jpg?raw=true?branch=master)  |

 

License
----

Apache License Version 2.0

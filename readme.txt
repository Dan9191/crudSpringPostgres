
          car!!!!!!


1) GET     http://localhost:4001/api/car
 добавляет 2 записи в бд

2) POST    http://localhost:4001/api/car/create
добавляет запись

body {
       "name": "Mustang 6",
       "engine": {
       "name" : "Coyote",
       "power" : 421
       },
       "gear": {
       "name" : "шестеренка в мустанге",
       "quantity" : 130
       },
       "manual": {
       "name" : "путеводитель по больницам"
       },
       "steeringWheel": {
       "name" : "узда",
       "material" : "кожа"
       }
     }

3)GET  http://localhost:4001/api/car/read/{id}

если выведенный id (который является primary key) больше, чем число записей в таблице, то выводятся все авто

4)POST http://localhost:4001/api/car/update/{id}

апгрейдит запись, если такой записи нет, то выводит NoEntityException

body
{
  "name": "oka",
  "engine": {
  "name" : "1111",
  "power" : 30
  },
  "gear": {
  "name" : "шестеренка в оке",
  "quantity" : 13
  },
  "manual": {
  "name" : "ак собрать табуретку"
  },
  "steeringWheel": {
  "name" : "баранка",
  "material" : "пластик"
  }
}

5) POST http://localhost:4001/api/car/read/{id}

удаляет запись id, если такой записи нет, выкидывает ошибку NoEntityException


          engine!!!!!

1) POST http://localhost:4001/api/engine/create

создает двигатель

{
  "name" : "1111",
  "power" : 30
}

2)GET http://localhost:4001/api/engine/read/{id}

если выведенный id (который является primary key) больше, чем число записей в таблице, то выводятся все engine

3)POST http://localhost:4001/api/engine/update/{id}
body
{
  "name" : "РД-180",
  "power" : 99999999999
}

4)POST http://localhost:4001/api/engine/delete/{id}
удаляет запись id, если такой записи нет, выкидывает ошибку NoEntityException


по всем оставшимся сущностям тот же принцип:

Gear
body
{
"name" : "название шестерней",
"quantity" : 1234
}

Manual
body
{
"name" : "название инструкции"
}

SteeringWheel
body
{
"name" : "название руля",
"material" : "материал руля"
}
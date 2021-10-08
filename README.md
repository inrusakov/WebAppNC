# Simple-Web-Application

## Локальный запуск


```sh
cmd:
$ mvnw package
$ heroku local:start
```
или запустить через класс <b> com/example/Application </b> 

Приложение должно быть поднято на [localhost:5000](http://localhost:5000/).

## Запуск в облаке Heroku

```sh
cmd:
$ heroku git:remote some-little-project
$ git commit -m "some message"
$ git push heroku main
$ heroku open
```

# REST api for witness bot

Rest api written in Play2 and it's used by [bot messenger](https://github.com/Burize/scala-bot-witness)

It is intended to store users and their reports. User passes registration by using telegram messenger. 
<em>Also there is should be another role: Judge, who check reports and set the status to them, but it hasn't implemented</em>

## Used features
- [Play2](https://www.playframework.com/) - framework for Scala
- [Slick](http://scala-slick.org/) - database query and access library for Scala
- [Circe](https://circe.github.io/circe/) - for JSON encoding/decoding

### Controllers

- WitnessController - for messenger user, who can send report
- ReportsController - for user's reports
- AuthController - for authenticating judge via some client by login/password - hasn't implemented

## Getting started 

1. For launch project you need:
[Scala](https://www.scala-lang.org/download/) 
[Sbt](https://www.scala-sbt.org/download.html)
[Postgress](https://www.postgresql.org/)

2. There is `conf/application-template.conf`. Copy it to the same directory and rename to `application.cong`. Then change `slick.dbs.default.db.properties.url` to your database credentials.

3. Run project: if you use intellij IDEA, you can run the project by it or via `sbt run` сomamnd. All tables are needed for the database will be created on the first launch.

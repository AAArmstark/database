scalaVersion := "2.13.6"
name := "lichess-db"
organization := "org.lichess"
version := "1.3"
resolvers += "lila-maven" at "https://raw.githubusercontent.com/ornicar/lila-maven/master"

val akkaVersion = "2.6.15"

libraryDependencies += "org.reactivemongo"  %% "reactivemongo"            % "1.0.4"
libraryDependencies += "org.reactivemongo"  %% "reactivemongo-akkastream" % "1.0.4"
libraryDependencies += "com.github.ornicar" %% "scalalib"                 % "7.0.2"
libraryDependencies += "org.lichess"        %% "scalachess"               % "10.2.5"
libraryDependencies += "com.typesafe.akka"  %% "akka-actor"               % akkaVersion
libraryDependencies += "com.typesafe.akka"  %% "akka-stream"              % akkaVersion
libraryDependencies += "com.typesafe.akka"  %% "akka-slf4j"               % akkaVersion
/* libraryDependencies += "ch.qos.logback"     % "logback-classic"           % "1.2.3" */
libraryDependencies += "joda-time"    % "joda-time"   % "2.10.10"
libraryDependencies += "org.lichess" %% "compression" % "1.6"

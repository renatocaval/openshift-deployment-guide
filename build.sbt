enablePlugins(ParadoxPlugin)

name := "lightbend-openshift-guide"

paradoxProperties ++= Map(
  "akka.management.version" -> "0.20.0",
  "akka.version" -> "2.5.20",
  "sbt.native.packager.version" -> "1.3.17",
  "adoptopenjdk.docker.image.version" -> "jdk8u202-b08",
  "strimzi.version" -> "0.9.0",
  "lagom.akka.discovery.version" -> "0.0.3+2-b0c9135e+20181211-1036"
)

// Exclude the includes directory from being compiled directly
(Compile / paradoxMarkdownToHtml / excludeFilter) := (Compile / paradoxMarkdownToHtml / excludeFilter).value ||
  ParadoxPlugin.InDirectoryFilter((Compile / paradox / sourceDirectory).value / "includes")

paradoxTheme := Some(builtinParadoxTheme("generic"))
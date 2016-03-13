1. to run tests:
gradle clean test

2. to make war
gradle :rest:war
find file.war
{projectDir}/rest/build/libs/status-report-1.0.war


URLs
{host:port}/warName/status-report-1.0
{host:port}/warName/status-report-1.0/v
{host:port}/warName/status-report-1.0/task?key=IMPL-1111

(examples)
http://localhost:8080/status-report-1.0/statusreport/task?key=IMPL-1111
http://localhost:8080/status-report-1.0/statusreport/v
http://localhost:8080/status-report-1.0/statusreport


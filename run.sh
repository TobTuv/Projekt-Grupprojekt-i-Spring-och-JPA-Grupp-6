echo "cooking up the madest meals"
mvn compile
mvn exec:java "-Dexec.mainClass=se.yrgo.client.Menu"
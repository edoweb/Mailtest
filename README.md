# Mailtest

# Get

```
git clone https://github.com/edoweb/Mailtest
```

# Clean

```
cd Mailtest
rm -rf mailer
```

# Build

```
mvn clean install
mkdir mailer
cp README.md mailer
cp target/Mailtest-0.0.1-SNAPSHOT-jar-with-dependencies.jar mailer
cp mail.properties mailer
cd mailer
```

# Configure

```
editor Mail.txt #Email text, default will send the README.md
editor mail.properties
```

# Send Mail

```
java -classpath mail.properties -jar Mailtest-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

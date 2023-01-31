# iStore
2JAVA PROJECT

<table><tr>
<td align="center"><img src="https://cdn.discordapp.com/attachments/774340712585625603/1069928031419891803/java-logo-1.png" width="90" /><br /></td>
<td align="center"><img src="https://cdn.discordapp.com/attachments/774340712585625603/1069928031109529610/Apache_Maven_logo.svg.png" width="90" /><br /></td>
<td align="center"><img src="https://cdn.discordapp.com/attachments/774340712585625603/1069928031889674280/1200px-MySQL.svg.png" width="90" /><br /></td>
</tr></table>

## Deploy project with IntelliJ 🌪️

Import the MySQL connector:
```
File >> Project structure >> Modules >> Dependencies
ALT+INSERT >> JARs or Directories...
Select the .jar "iStore\lib\mysql-connector-j-8.0.31.jar"
Ok
```

Install the Maven dependencies:
```
Dependencies >> All Modules >> iStore
Search for the following dependencies :
 - io.github.cdimascio [v2.3.1]
Add
```

Deploy the database with MySQL:
```bash
mysql -u istore -p istore < istore.sql
```

Configuring the environment variables:
```
In the .env file located in the root folder iStore/.env:
DATABASE_URL=jdbc:mysql://localhost:3306/istore
DATABASE_USER=istore
DATABASE_PASSWORD=ChangeMe
```

Build the project:
```
CTRL+F9
```

Run the project:
```
MAJ+F10
```
<br>
The setup is completed! 🎉

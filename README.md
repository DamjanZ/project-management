#Project-Management
==================
Project-management is Clojure application designed for managing with projects in single company.
Inended for small companies, users can create new projects, employees that work in company and meetings whitin the active projects.
User can also edit personal informations about choosen employee.

Guide to start application
=====
1. Clone project with command "git clone https://github.com/DamjanZ/project-management.git" from github terminal or simple download it from project site on button "Download ZIP"
2. This application uses SQL database. There are many relationship-management applications who supports SQL and I suggest MySQL Workbench or phpMyAdmin as good free solutions.
3. Download Leiningen 
3. Start SQL application.
4. Import database "project_management.sql" with same schema name.
5. Default username and password for database access are "root" and "". If you are using different parameters, change them in "db.clj" file on route directory: "project-management\src\clj\project_management\model".
6. Port 3000 needs to be available on machine, cause that is port which will web server use.
6. From project root in console, run command "lein repl"
7. In repl, run command "(start-server)".
 
## Libraries used
- [Ring](https://github.com/ring-clojure/ring) and [Compojure](https://github.com/weavejester/compojure) - Used for server manipulation
- [Valip] (https://github.com/cemerick/valip) - Used for data validation
- [Enlive] (https://github.com/cgrand/enlive) - HTML selector, used for templating
- [Domina] (https://github.com/levand/domina) - DOM manipulation
- [JDBC] (https://github.com/clojure/java.jdbc) - A low-level Clojure wrapper for JDBC-based access to databases.
- [clj-time] (https://github.com/KirinDave/clj-time) - A date and time library for Clojure, wrapping the [Joda Time](http://joda-time.sourceforge.net/) library.
- [Sandbar] (https://github.com/brentonashworth/sandbar) - Used with Ring and Compojure
- [Clojurescript] (https://github.com/clojure/clojurescript) - Javascript compiler, using Clojure.

## License
Distributed under the Eclipse Public License, as Clojure

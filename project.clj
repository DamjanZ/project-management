(defproject project-management "0.0.1-SNAPSHOT"

  :description "FIXME: write this!"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [com.cemerick/valip "0.3.2"]
                 [ring "1.3.0"]
                 [compojure "1.1.8"]
                 [clojurewerkz/neocons "3.0.0"]
		             [ring/ring-core "1.3.0"]
		             [ring/ring-jetty-adapter "1.3.0"]
                 [enlive "1.1.5"]
                 [domina "1.0.3-SNAPSHOT"]
                 [sandbar "0.4.0-SNAPSHOT"]
                 [clj-time "0.8.0"]
                 [date-clj "1.0.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [mysql/mysql-connector-java "5.1.25"]
                 ]

  :plugins [[lein-cljsbuild "1.0.2"]
            [lein-ring "0.8.11"]
            [codox "0.8.9"]
            ]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :resource-paths ["resources"]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :cljsbuild {
              :builds{
                      :prod-employee {
                                      :source-paths ["src/cljs/project_management/content/employee"]
                                      :compiler {
                                                  :output-to "resources/public/js/employee.js"
                                                  :optimizations :advanced
                                                }
                                    }
                      
                      :dev-employee {
                                      :source-paths ["src/brepl"
                                                     "src/cljs/project_management/content/employee"]
                                      :compiler {
                                                  :output-to "resources/public/js/employee.js"
                                                  :optimizations :whitespace
                                                  :pretty-print true
                                                }
                                    }
                      }
   }
  :aot [project-management.core]
  ;:main project-management.core
  :main project-management.repl
  :ring {:handler project-management.core/app})

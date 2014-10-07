(ns project-management.repl
  "The starting namespace for the project. This is the namespace that
  users will land in when they start a Clojure REPL. It exists to
  provide convenience functions like 'go' and 'dev-server'."
  (:use [clojure.repl]
	[clojure.java.shell :only [sh]])
  (:import [java.io InputStreamReader
		    BufferedReader]
	   [java.lang Runtime])
  (:require [clojure.java.browse :as browse]
	    [project-management.core :as server]
	    ))
;;komanda da se izadje iz bash sesije u cmd-u
(defn cmd-term
  "Execute command prompt/terminal command"
  [command]
  (let [process (. (Runtime/getRuntime) exec command)
      stdin (.getInputStream process)
      isr (InputStreamReader. stdin)
      br (BufferedReader. isr)]
      (let [seq (line-seq br)]
	seq)))


(defn start-server
  "Start the development server and open the host application in the
  default browser."
  []
  (def server (server/run-server))
  (browse/browse-url "http://localhost:3000/")
  )

(defn stop-server
  "Stop server"
  []
  (.stop server))

(defn restart-server
  "Restart server"
  []
  (stop-server)
  (start-server))

(defn -main [& args]
  (start-server))

(println)
(println "Type (start-server) to launch server.")
(println "Type (restart-server) to restart server.")
(println "Type (stop-server) to stop server.")
(println)

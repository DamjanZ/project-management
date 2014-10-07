(ns project-management.model.db
  (:require [clojure.java.jdbc :as sql]
            [project-management.utils :refer [parse-time]]
    )
  (:gen-class))

(def db {:classname "com.mysql.jdbc.Driver"
         :subprotocol "mysql"
         :subname "//localhost:3306/project_management"
         :user "root"
         :password ""
         }
)

(defn get-user [username password]
  (sql/with-connection db
    (sql/with-query-results 
      rows ["SELECT * FROM user WHERE username = ? AND password = ?" username, password]
      (doall rows)
    )
  )
)

(defn crate-project[project-name project-desc project-start-date project-end-date project-active project-budget project-user]
  (sql/with-connection db
    (sql/insert-values 
      :project [:projectname :projectdesc :startdate :enddate :active :budget :userid]
      [project-name project-desc project-start-date project-end-date project-active project-budget project-user]
    )
  )
)

(defn get-projects [user-id]
  (sql/with-connection db
    (sql/with-query-results 
      rows ["SELECT projectid, projectname FROM project WHERE userid = ? AND active = 1" user-id]
      (doall rows)
    )
  )
)

(defn create-meeting[meeting-name meeting-report meeting-location meeting-date project-id]
  (sql/with-connection db
    (sql/insert-values
      :meeting [:meetingdate :meetingtitle :report :meetingplace :projectid]
      [meeting-date meeting-name meeting-report meeting-location project-id]
    )
  )
)

(defn get-places []
  (sql/with-connection db
    (sql/with-query-results 
      rows ["SELECT postal_code, town_name FROM town"]
      (doall rows)
    )
  )
)

(defn get-employees []
  (sql/with-connection db
    (sql/with-query-results 
      rows ["SELECT employeeid, concat(name, ' ', last_name) as fullname, position, works, gender FROM employee"]
      (doall rows)
    )
  )
)

(defn get-specific-employee [id]
  (sql/with-connection db
    (sql/with-query-results 
      rows ["SELECT * FROM employee WHERE employeeid = ?" id]
      (doall rows)
    )
  )
)

(defn create-employee [employee-name employee-surname employee-gender employee-position employment-date employee-phone-numb employee-salary employee-post-numb employee-birth-date employee-street employee-street-no employee-active]
  (sql/with-connection db
    (sql/insert-values
      :employee [:name :last_name :gender :position :dateofemployment :phonenumber :salary :postal_code :birthdate :streetname :streetnum :works]
      [employee-name employee-surname employee-gender employee-position employment-date employee-phone-numb employee-salary employee-post-numb employee-birth-date employee-street employee-street-no employee-active]
    )
  )
)

(defn update-employee [id employee-map]
  (sql/with-connection db
    (sql/update-values :employee ["employeeid = ?" id] 
      { :name (:e_name employee-map) 
        :last_name (:e_surname employee-map) 
        :gender (:e_gender employee-map) 
        :position (:e_position employee-map) 
        :dateofemployment (parse-time "yyyy-MM-dd" (:e_date_employed employee-map)) 
        :phonenumber (:e_phone employee-map) 
        :salary (:e_salary employee-map) 
        :postal_code (:e_post_num employee-map) 
        :birthdate (parse-time "yyyy-MM-dd" (:dateofbirth employee-map) ) 
        :streetname (:street employee-map) 
        :streetnum (:streetno employee-map) :works (if (= (:e_works employee-map) "true")
                                                        1
                                                        0
                                                    )
      }
    )
  )
)
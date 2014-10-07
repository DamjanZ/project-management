(ns project-management.content.project.project-controller
	(:use (sandbar stateful-session)
			[date-clj])
	(:require [project-management.model.db :as database]
			[project-management.login.login-view :refer [home]]
			[project-management.utils :refer [parse-time parse-double]]
	)
	(:import java.text.SimpleDateFormat)
	(:import java.util.Date)
)

(defn save-project
	"Save new project if client has filled every field."
	[req-params]
	
	(let [project-name (:p_name req-params)
		project-desc (:p_desc req-params)
		project-start-date (parse-time "yyyy-MM-dd" (:p_start_d req-params))
		project-end-date (parse-time "yyyy-MM-dd" (:p_end_d req-params))
		project-budget (parse-double (:p_budget req-params))
		user-id (session-get :login-id)
		project-active true]

		(if 
			(and
				(before? project-start-date project-end-date)
				(after? project-start-date (new java.util.Date))
			)

			(database/crate-project project-name project-desc project-start-date project-end-date project-active project-budget user-id)

			(println "Project start date isn't greater than today or lower than end date!")
		)
	)
	(home)
)
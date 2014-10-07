(ns project-management.content.employee.employee-controller
	(:use (sandbar stateful-session))
	(:require [project-management.model.db :as database]
			  [project-management.utils :refer [parse-time parse-integer]]
	)
)

(defn save-employee
	"Save new employee"
	[req-params]

	(let [employee-name 	(:e_name 		req-params)
		employee-surname 	(:e_surname 	req-params)
		employee-gender		(:e_gender 		req-params)
		employee-position	(:e_position	req-params)
		employment-date		(parse-time "yyyy-MM-dd" (:e_date_employed req-params))
		employee-phone-numb	(:e_phone		req-params)
		employee-salary		(:e_salary 		req-params)
		employee-post-numb	(:e_post_num 	req-params)
		employee-birth-date (parse-time "yyyy-MM-dd" (:dateofbirth req-params))
		employee-street 	(:street 		req-params)
		employee-street-no	(:streetno 		req-params)
		employee-active true]

		(database/create-employee employee-name employee-surname employee-gender employee-position employment-date employee-phone-numb employee-salary employee-post-numb employee-birth-date employee-street employee-street-no employee-active)
	)
)

(defn update-employee-info
	"Update informations about selected employee"
	[req-params]

	(println req-params)
	(let [employee-id (session-get :employeeid)]
		(println employee-id)
		(database/update-employee employee-id req-params)
	)

)
(ns project-management.content.meeting.meeting-controller
	(:use (sandbar stateful-session)
			[date-clj])
	(:require [project-management.model.db :as database]
			[project-management.utils :refer [parse-time parse-double]]
	)
)

(defn save-meeting
	"Save new meeting for selected project."
	[req-params]

	(let [project-id (:m_project req-params)
		meeting-name (:m_name req-params)
		meeting-date (parse-time "yyyy-MM-dd" (:m_date req-params))
		meeting-location (:m_location req-params)
		meeting-report (:m_report req-params)]
		(database/create-meeting meeting-name meeting-report meeting-location meeting-date project-id)
	)
)
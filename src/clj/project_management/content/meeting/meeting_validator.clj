(ns project-management.content.meeting.meeting-validator
  (:require [valip.core :refer [validate]]
	    [valip.predicates :refer [present? matches email-address?]]))

(defn create-project-errors
  "Validators for project form"
  [params]
  (validate params
	    [:meeting-name present? "Name can't be empty."]
	    [:meeting-date present? "Date can't be empty."]
	    [:meeting-location present? "Location can't be empty."]
	    [:meeting-report present? "Report can't be empty."]
	    [:meeting-hall present? "Meeting hall can't be empty."]
  )
)

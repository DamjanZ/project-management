(ns project-management.content.project.project-validator
  (:require [valip.core :refer [validate]]
	    [valip.predicates :refer [present? matches email-address?]]))

(defn create-project-errors
  "Validators for project form"
  [params]
  (validate params
	    [:project-name present? "Name can't be empty."]
	    [:project-desc present? "Description can't be empty."]
	    [:project-start-date present? "Start date can't be empty."]
	    [:project-end-date present? "End date can't be empty."]
	    [:project-budget present? "Budget can't be empty."]
  )
)

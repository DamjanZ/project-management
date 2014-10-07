(ns project-management.content.employee.employee-validator
  (:require [valip.core :refer [validate]]
	    [valip.predicates :refer [present? matches email-address?]]))

(defn create-project-errors
  "Validators for project form"
  [params]
  (validate params
	    [:employee-name present? "Name can't be empty."]
	    [:employee-surname present? "Surname can't be empty."]
	    [:employee-work-date present? "Date when employed can't be empty."]
	    [:employee-phone present? "Telephone number can't be empty."]
	    [:employee-salary present? "Salary can't be empty."]
		[:employee-salary decimal-string? "Salary must be decimal number."]
	    [:employee-birth-date present? "Birth date can't be empty."]
	    [:employee-address-street present? "Street address can't be empty."]
	    [:employee-address-numb present? "Number of address can't be empty."]
  )
)

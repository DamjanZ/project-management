(ns project-management.login.login-validators
  (:require [valip.core :refer [validate]]
	    [valip.predicates :refer [present?]]))

(defn login-errors
  "Validators for login form"
  [params]
  (validate params
	    [:username present? "Username can't be empty."]
	    [:password present? "Password can't be empty."]))

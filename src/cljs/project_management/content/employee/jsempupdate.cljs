(ns project-management.content.employee.jsempupdate
  (:require [domina :as dom]
  			[domina.css :as domcss]
  			[domina.events :as evts]
  			[clojure.string :as cstring]
      )
)

(defn edit-employee
	"Send to server request with id of employee"
	[employee-id]
  	(let [xmlhttp (js/XMLHttpRequest.)]
		(.open xmlhttp "POST" (str "/edit-employee/"employee-id) true)
		(.send xmlhttp)
	)
)

(defn update-click-listener
	"Set listener to open new form to update existing info for employee"
	[]
	(let [sel-values (map dom/attrs (dom/nodes (domcss/sel "a[id*='update")))]
		(doseq [sel-value sel-values]
			(evts/listen! (dom/by-id (:id sel-value))
			      :click
			      (fn []
					  (edit-employee (cstring/replace (:id sel-value)
									   "eupdate"
									   ""
									   )
					  )
				  )
			)
		)
	)
)

(defn ^export init[]
	(if (and js/document
			(.getElementById js/document)
		)
		(update-click-listener)
	)
)
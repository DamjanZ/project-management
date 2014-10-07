(ns project-management.content.project.project-create
  (:use [domina :only [by-id value] ])
  )

(defn validate-form []
	(let [project-name (by-id "cProjectName")
		project-desc (by-id "cProjectDesc")
		project-start-date (by-id "cProjectStartD")
		project-end-date (by-id "cProjectEndD")
		project-budget (by-id "cProjectBudget")
		]
		

		)
)
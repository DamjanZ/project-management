(ns project-management.login.login-controller
	(:use (sandbar stateful-session))
	(:require [project-management.login.login-view :as lv]
			[project-management.model.db :as database]
	)
)

(defn login-validation
	"Function for loging user to server"
	[req-params]
	(let [user (:username req-params)
		password (:password req-params)	]

		(let [ to-log (database/get-user user password)	]
			(session-put! :login-id (:userid (first to-log)))
			(session-put! :login-try 1)
			(session-put! :username user)
			(session-put! :pass password)
			(session-put! :first-name (:name (first to-log)))
			(session-put! :last-name (:last_name (first to-log)))
		)
	)
)

(defn is-logged-in
	"Function that checks if user exist. If doesn't, return him to login page."
	[response-fn]
	(if (= (session-get :login-id) nil)
		(lv/login)
		( do (session-pop! :login-try 1)
		response-fn)
	)
)
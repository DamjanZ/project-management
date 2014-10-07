(ns project-management.login.login-view
 (:use (sandbar stateful-session))
  (:require [project-management.html-generator :as hg]
	    [net.cgrand.enlive-html :as en]
	    [project-management.model.db :as database]
	    )
)

  (en/deftemplate login
	  (hg/build-html-page [{:temp-sel [:div.content],
							:comp "public/login/login_form.html",
							:comp-sel [:div]
							}])
	  []
	  [:title] (en/content "Login")

	  [:div.script] (en/content {:tag :script,
				     :attrs {:src "http://localhost:3000/js/login.js"},
				     :content nil})
	  [:div.script] (en/append {:tag :script,
				    :attrs nil,
				    :content "project-management.login.jslogin.init();"})
  )

  (en/deftemplate home
	  (hg/build-html-page [{:temp-sel [:div#header],
							:comp "public/navmenu.html",
							:comp-sel [:div.container]}

				  			{:temp-sel [:div.content],
							:comp "public/login/home.html",
							:comp-sel [:h1]}
				]
		)
	  []
	  [:title] (en/content "Home")

	  [:div#header] (en/set-attr :class "navbar navbar-default navbar-fixed-top"
	  							 :role "navigation"
	  				)

	  [:a.navbar-brand] (en/append { :tag :div,
	  									 :attrs nil,
	  									 :content (format "%s %s" (session-get :first-name) (session-get :last-name))
	  									}
	  						)

	  [:div.content] (en/set-attr :class "form-container")
	  [:h1]	(en/append { :tag :div,
	  					:attrs nil,
	  					:content (format "%s %s" (session-get :first-name) (session-get :last-name)
	  						)
	  					}
	  		)
	  [:div.script] (en/content {:tag :script,
				     :attrs {:src "http://localhost:3000/js/login.js"},
				     :content nil})
	  [:div.script] (en/append {:tag :script,
				    :attrs nil,
				    :content "project-management.login.jslogin.init();"})
  )

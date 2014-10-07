(ns project-management.content.meeting.meeting-view
 (:use (sandbar stateful-session))
  (:require [project-management.html-generator :as hg]
	    [net.cgrand.enlive-html :as en]
	    [project-management.model.db :as database]
	    [project-management.content.meeting.meeting-controller :as mc]
	    )
)

(en/deftemplate create-meeting
	(hg/build-html-page [{:temp-sel [:div#header],
                          :comp "public/navmenu.html",
                          :comp-sel [:div.container]}

                          {:temp-sel [:div.content],
                    	   :comp "public/content/meeting/create_meeting.html",
                    	   :comp-sel [:form]}
                        ]
	)
	[]
	[:title] (en/content "Create meeting")

	[:div#header] (en/set-attr :class "navbar navbar-default navbar-fixed-top"
                   :role "navigation"
                  )

	[:a.navbar-brand] (en/append { :tag :div,
                       :attrs nil,
                       :content (format "%s %s" (session-get :first-name) (session-get :last-name))
                      }
                )

	[:div.content] (en/set-attr :class "container form-container")

	[:form] (en/set-attr :action "/save-meeting"
                         :id "insert_meeting"
                         :method "post"
            )

	[:option.option-control] (en/clone-for [projects  ( into [] (database/get-projects (session-get :login-id)))]
								(comp (en/set-attr :value (get projects :projectid)) (en/content (get projects :projectname))
                                )
							)
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
    [:h1] (en/content "Congratulation! You succesfully created new meeting for project.")
  )

(defn save-new-meeting
	[req-params]
	(mc/save-meeting req-params)
	(home)
)

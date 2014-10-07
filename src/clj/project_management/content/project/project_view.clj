(ns project-management.content.project.project-view
 (:use (sandbar stateful-session))
  (:require [project-management.html-generator :as hg]
	    [net.cgrand.enlive-html :as en]
	    [project-management.model.db :as database]
	    )
)

  (en/deftemplate create-project
  	(hg/build-html-page [{:temp-sel [:div#header],
                          :comp "public/navmenu.html",
                          :comp-sel [:div.container]}

                          {:temp-sel [:div.content],
                    			 :comp "public/content/project/project_create_form.html",
                    			 :comp-sel [:form]}
                        ]
    )
  	[]
  	[:title] (en/content "Create project")

    [:div#header] (en/set-attr :class "navbar navbar-default navbar-fixed-top"
                   :role "navigation"
                  )

    [:a.navbar-brand] (en/append { :tag :div,
                       :attrs nil,
                       :content (format "%s %s" (session-get :first-name) (session-get :last-name))
                      }
                )

    [:div.content] (en/set-attr :class "container form-container")

  	[:form] (en/set-attr :action "/save-project"
                         :id "insert_project"
                         :method "post"
            )
  							;(en/append {:tag :input,
  							;			:attrs {:type "hidden",
  							;				:name "p_user_id"
  							;				:value (session-get :logit-id)
  							;			}
  							;			:content nil
  							;			})
  )
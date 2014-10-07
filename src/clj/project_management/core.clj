(ns project-management.core

  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [project-management.model.db :as database]
            [project-management.login.login-view :as lv]
            [project-management.login.login-controller :as lc]
            [project-management.content.project.project-view :as pv]
            [project-management.content.project.project-controller :as pc]
            [project-management.content.meeting.meeting-view :as mv]
            [project-management.content.employee.employee-view :as ev]

            )
  (:use compojure.core
      (sandbar stateful-session)
      [ring.middleware.params]
      [ring.middleware.multipart-params]
      [compojure.core]
  )
)

(defroutes main-routes
  (GET "/" []
   (lc/is-logged-in (lv/home))
  )

  (POST "/login" request
    (do (lc/login-validation (:params request))
      (lc/is-logged-in (lv/home))
    )
  )

  (GET "/create-project" []
    (lc/is-logged-in (pv/create-project))
  )

  (POST "/save-project" request
      (lc/is-logged-in (pc/save-project (:params request)))
  )

  (GET "/create-meeting" []
    (lc/is-logged-in (mv/create-meeting))
  )

  (POST "/save-meeting" request
      (lc/is-logged-in (mv/save-new-meeting (:params request)))
  )

  (GET "/create-employee" []
    (lc/is-logged-in (ev/create-employee))
  )

  (POST "/save-employee" request
      (lc/is-logged-in (ev/save-new-employee (:params request)))
  )

  (GET "/show-employees" []
    (lc/is-logged-in (ev/show-employees))
  )

  (GET "/edit-employees/:id" [id]
   (lc/is-logged-in (ev/edit-employee id))
  )

  (POST "/update-employee" request
    (lc/is-logged-in (ev/update-existing-employee (:params request)))
  )

  (GET "/logout" []
    (do (destroy-session!)
      (lc/is-logged-in (lv/home)))
  )

  (route/resources "/")
  (route/not-found "Page not found")
)

(def app 
  (-> (handler/site main-routes)
    wrap-stateful-session
    wrap-params
    wrap-multipart-params)
)

(defn run-server []
  (jetty/run-jetty app {:port 3000
                        :join? false})
)
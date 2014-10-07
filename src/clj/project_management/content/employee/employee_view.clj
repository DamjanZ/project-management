(ns project-management.content.employee.employee-view
 (:use (sandbar stateful-session))
  (:require [project-management.html-generator :as hg]
      [net.cgrand.enlive-html :as en]
      [project-management.model.db :as database]
      [clj-time.coerce :refer [from-sql-date to-date]]
      [project-management.utils :refer [format-time]]
      [project-management.content.employee.employee-controller :as ec]
      )
)

(en/deftemplate create-employee
  (hg/build-html-page [{:temp-sel [:div#header],
                          :comp "public/navmenu.html",
                          :comp-sel [:div.container]}

                          {:temp-sel [:div.content],
                         :comp "public/content/employee/employee_create_form.html",
                         :comp-sel [:div.container]}
                        ]
  )
  []
  [:title] (en/content "Create employee")

  [:div#header] (en/set-attr :class "navbar navbar-default navbar-fixed-top"
                   :role "navigation"
                  )

  [:a.navbar-brand] (en/append { :tag :div,
                       :attrs nil,
                       :content (format "%s %s" (session-get :first-name) (session-get :last-name))
                      }
                )

  [:form] (en/set-attr :action "/save-employee"
                         :id "insert_employee"
                         :method "post"
            )

  [:option.gender-option] (en/clone-for [gtype ["Male" "Female"]] (en/content gtype))


  [:option.option-control] (en/clone-for [town  ( into [] (database/get-places ))]
                              (comp (en/set-attr :value (get town :postal_code)) (en/content (format "%s - %s" (get town :postal_code) (get town :town_name))
                                            )
                              )
                            )
)

(en/deftemplate show-employees
  (hg/build-html-page [{:temp-sel [:div#header],
                          :comp "public/navmenu.html",
                          :comp-sel [:div.container]}

                          {:temp-sel [:div.content],
                         :comp "public/content/employee/employee_table.html",
                         :comp-sel [:div]}
                        ]
  )
  []

  [:title] (en/content "Show employees")

  [:div#header] (en/set-attr :class "navbar navbar-default navbar-fixed-top"
                   :role "navigation"
                  )

  [:a.navbar-brand] (en/append { :tag :div,
                       :attrs nil,
                       :content (format "%s %s" (session-get :first-name) (session-get :last-name))
                      }
                )

  [:tr.employee-content] (en/clone-for [employees  (database/get-employees )]

                              [:td.fullname] (en/content (format "%s" (get employees :fullname)))
                              [:td.position] (en/content ( format "%s" (get employees :position)))
                              [:td.works] (en/content (if (= true  (get employees :works))
                                                          "Yes" "No"
                                                      )
                                          )

                              [:td.gender] (en/content (format "%s" (get employees :gender)))

                              [:td.button :a] (en/set-attr :href (str "/edit-employees/" (get employees :employeeid) )
                                                           :class "btn btn-default"
                                              )

                              [:td.button :a] (en/content "Edit")

                              [:td.button :span] (en/set-attr :class "glyphicon glyphicon-pencil")
                            )

  [:div.script] (en/content ;[
                              {:tag :script
                               :attrs nil
                               :content "$(document).ready(function() {
                                        $('#emptable').dataTable();
                                      } );"
                              }
                )
)

(en/deftemplate fill-form-employee
  (hg/build-html-page [{:temp-sel [:div#header],
                          :comp "public/navmenu.html",
                          :comp-sel [:div.container]}

                          {:temp-sel [:div.content],
                           :comp "public/content/employee/employee_create_form.html",
                           :comp-sel [:div.container]}

                           {:temp-sel [:div#gradio],
                           :comp "public/content/employee/employee_radio_buttons.html",
                           :comp-sel [:div.control-group]}
                        ]
  )
  [sel-employee]

  [:title] (en/content "Edit employee")

  [:div#header] (en/set-attr :class "navbar navbar-default navbar-fixed-top"
                   :role "navigation"
                  )

  [:a.navbar-brand] (en/append { :tag :div,
                       :attrs nil,
                       :content (format "%s %s" (session-get :first-name) (session-get :last-name))
                      }
                )

  [:h1] (en/content "Update employee")

  [:form] (en/set-attr :action "/update-employee"
                         :id "update_employee"
                         :method "post"
            )

  [:input#eidname]        (en/set-attr :value (format "%s" (:name (first sel-employee))))
  [:input#eidsurname]     (en/set-attr :value (format "%s" (:last_name (first sel-employee))))
  [:input#eidgender]      (en/set-attr :value (format "%s" (:gender (first sel-employee))))
  [:input#eidate]         (en/set-attr :value 
                                        (format "%s" (let [employe-date (:dateofemployment(first sel-employee))]
                                                (format-time "yyyy-MM-dd" (to-date (from-sql-date employe-date)))
                                              )
                                        )
                          )
  
  [:input#eiposition]     (en/set-attr :value (format "%s" (:gender (first sel-employee))))
  [:input#eiphone]        (en/set-attr :value (format "%s" (:phonenumber (first sel-employee))))
  [:input#eisalary]       (en/set-attr :value (format "%s" (:salary (first sel-employee))))
  [:input#eibirth]        (en/set-attr :value (format "%s" (:postal_code (first sel-employee))))

  [:option.gender-option] (en/clone-for [gtype ["Male" "Female"]] 
                            (if
                              (= (:gender (first sel-employee)) gtype)
                                (comp (en/set-attr :selected "selected") (en/content gtype))
                                (en/content gtype)
                            )
                          )

  [:option.option-control] (en/clone-for [town  ( into [] (database/get-places ))]

                              (if 
                                (= (get town :postal_code) (:postal_code (first sel-employee)))

                                  (comp (en/set-attr :value (get town :postal_code) :selected "selected") (en/content (format "%s - %s" (get town :postal_code) (get town :town_name))))
                                  (comp (en/set-attr :value (get town :postal_code)) (en/content (format "%s - %s" (get town :postal_code) (get town :town_name))))
                              )
                            )
  [:input#eibirth]        (en/set-attr :value 
                                        (format "%s" (let [employe-date (:birthdate(first sel-employee))]
                                                (format-time "yyyy-MM-dd" (to-date (from-sql-date employe-date)))
                                              )
                                        )
                          )

  [:input#eistreet]       (en/set-attr :value (format "%s" (:streetname (first sel-employee))))
  [:input#eistrno]        (en/set-attr :value (format "%s" (:streetnum (first sel-employee))))

  [:input#gradio-true]       (if (= (:works (first sel-employee)) true)
                                (en/set-attr :checked "checked")
                                (en/set-attr :id "gradio-true"))

  [:input#gradio-false]       (if (= (:works (first sel-employee)) false)
                                (en/set-attr :checked "checked")
                                (en/set-attr :id "gradio-false"))

  [:button]               (en/content "Update employee")

)

(defn edit-employee
  "Get information for employee by id and send it to form"
  [id]
  (let [sel-employee (database/get-specific-employee id)]
    (session-put! :employeeid id)
    (fill-form-employee sel-employee)
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
    [:h1] (en/content "Congratulation! You succesfully created new employee.")
)

(defn save-new-employee
  [req-params]
  (ec/save-employee req-params)
  (home)
)

(defn update-existing-employee
  [req-params]

  (ec/update-employee-info req-params)
  (home)
)
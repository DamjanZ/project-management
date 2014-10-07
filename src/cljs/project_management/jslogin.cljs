(ns project-management.login
  (:use [domina :only [by-id value]         ] 
  )
)
;;define function for validation
(defn validate-form []
  ;; get email and password element from their ids in the HTML form
  (let [username (by-id "loginUsername")
        password (by-id "loginPassword")]
    (if (and (> (count (value username)) 0)
             (> (count (value password)) 0))
      true
      (do (js/alert "Please, complete the form!")
          false))))

(defn ^export init []
  ;; verify that js/document exists and that it has a getElementById
  ;; property
  (if (and js/document
           (.-getElementById js/document))
    ;; get loginForm by element id and set its onsubmit property to
    ;; our validate-form function
    (let [login-form (.getElementById js/document "loginForm")]
      (set! (.-onsubmit login-form) validate-form))))
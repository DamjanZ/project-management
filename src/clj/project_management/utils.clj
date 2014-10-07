(ns project-management.utils
  "Useful functions that are repeatedly called all over the project"
  (:require [cljs.reader :refer [read-string]]
	    [clojure.test :refer :all])
  (:refer-clojure :exclude [read-string])
  (:import java.text.SimpleDateFormat)
)

(with-test
(defn parse-integer
  "Parse string to integer"
  [s]
  (if (and (string? s) (re-matches #"\s*[+-]?\d+\s*" s))
    (read-string s)))
 (is (= 1 (parse-integer "1")))
 (is (= nil (parse-integer "1.5")))
 (is (= nil (parse-integer "1a")))
 (is (= 1 (parse-integer "01")))
 (is (= nil (parse-integer "integer")))
)

(with-test
(defn parse-double
  "Parse string to double"
  [s]
  (if (and (string? s) (re-matches #"\s*[+-]?\d+(\.\d+(M|M|N)?)?\s*" s))
    (read-string s)))
 (is (= 2 (parse-double "2")))
 (is (= 2.1 (parse-double "2.1")))
 (is (= 2.1 (parse-double "02.1")))
 (is (= nil (parse-double "02a.1")))
 (is (= nil (parse-double "double")))
 (is (= nil (parse-double "6.5E-5")))
 (is (= nil (parse-double "2.1.23")))
)

(with-test
(defn parse-number
  "Parse string to number"
  [x]
  (if (and (string? x) (re-matches #"^-?\d+\.?\d[E]?-?\d*$|^-?\d+\.?\d*$" x))
    (read-string x)))
 (is (= 2 (parse-number "2")))
 (is (= 2.1 (parse-number "2.1")))
 (is (= 2.1 (parse-number "02.1")))
 (is (= 6.5E-5 (parse-number "6.5E-5")))
 (is (= nil (parse-number "02a.1")))
 (is (= nil (parse-number "2.1.23")))
)

(with-test
(defn add-data-to-map
  "Add node data to map"
  [start-map node]
  (let [id (:id node)
	data (:data node)]
       (assoc start-map id data)))
 (is (= {1 {:data1 1
	    :data2 "2"}} (add-data-to-map {} {:id 1
					       :data {:data1 1
						      :data2 "2"}})))
)

(defn parse-time [format date]
  (let [fdt (java.text.SimpleDateFormat. format)]
    (.parse fdt date)
  )
)

(defn format-time [date-format date]
  (.format (java.text.SimpleDateFormat. date-format) date)
)
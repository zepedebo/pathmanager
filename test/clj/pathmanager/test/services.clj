(ns pathmanager.test.services
  (:use clojure.test
        ragtime.core
        ring.mock.request
        pathmanager.handler)
  (:require [pathmanager.db.test :as db]
            [ragtime.sql.database :as rtdb]
            [ragtime.sql.files :as rtfiles]
            ))

(defn services-fixture [f]
  (def c (connection "jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1"))
  (def m (rtfiles/migrations))
  (rollback-last c)
  (migrate-all c m)
  (f)
  )

;(deftest test-app
;  (testing "Get player list"
;    (db/add-player<! {:name "Steve"})
;    (let [response (app (request :get "/players"))]
;      (is (= 200 (:status response)))
;      (is (= "Steve" response)))))

(ns pathmanager.test.db
  (:use clojure.test
        ragtime.core)
  (:require [pathmanager.db.test :as db]
            [ragtime.sql.database :as rtdb]
            [ragtime.sql.files :as rtfiles]
            ))

(defn db-fixture [f]
  (def c (connection "jdbc:h2:mem:test_db;DB_CLOSE_DELAY=-1"))
  (def m (rtfiles/migrations))
  (migrate-all c m)
  (f)
  )

(use-fixtures :once db-fixture)

(deftest test-new-player
  (testing "add first player"
    (db/add-player<! {:name "steve"})
    (def p (db/get-player {:id 1}))
    (is (= 1 (count p))))
  (testing "add second player"
    (db/add-player<! {:name "ray"})
    (def p (db/get-player {:id 2}))
    (is (= 1 (count p))))
  (testing "get player list"
    (let [player-list (db/get-players)]
      (is (= 2 (count player-list))))))


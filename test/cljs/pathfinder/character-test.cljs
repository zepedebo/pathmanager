(ns pathfinder.character-test
  (:use [pathmanager.character :only [attrib-adjustment race-adjustment]]))

(defn attrib-adjustment-test []
  (assert (= (attrib-adjustment 1) -5))
  (assert (= (attrib-adjustment 2) -4))
  (assert (= (attrib-adjustment 3) -4))
  (assert (= (attrib-adjustment 10) 0))
  (assert (= (attrib-adjustment 12) 1))
  (assert (= (attrib-adjustment 30) 10))
  (assert (= (attrib-adjustment 31) 10)))

(defn race-adjustment-test []
  (assert (= (race-adjustment "dwarf") {:con 2 :wis 2 :cha -2})))

(defn run []
  (attrib-adjustment-test)
  (race-adjustment-test))

(ns pathfinder.test
  (:require [pathfinder.character-test :as char]))

(def success 0)

(defn ^:export run []
  (.log js/console "Example test started.")
  (char/run)
  success)
(ns pathmanager.character
  (:require[pathmanager.db.core :as db]))

(defn set-active! [playerid characterid]
  (db/clear-active-for-player! playerid)
  (db/set-character-active! characterid)
  )

(def attrlist (list "str" "con" "dex" "int" "wis" "cha"))

(defn rest-party [id]
  )

(defn attrib-adjustment [attrval]
  (let [adjattr (if (< attrval 10) (- attrval 1) attrval)]
    (-> (/ adjattr 2) (- 5) int )))

(defn race-adjustment [race]
  (case race
    "dwarf" {:con 2 :wis 2 :cha -2}
    "elf" {:dex 2 :int 2 :con -2}
    "gnome" {:con 2 :cha 2 :str -1}
    "halfling" {:dex 2 :cha 2 :str -2}
    {}))

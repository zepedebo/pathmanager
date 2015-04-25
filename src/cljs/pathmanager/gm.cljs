(ns pathmanager.gm
  (:require [reagent.core :as reagent :refer [atom]][reagent.session :as session]
            [reagent-forms.core :refer [bind-fields]]
            [ajax.core :refer [GET POST]]))

(def active-characters (reagent/atom {}))

(defn character-item [character]
  [:tr [:td (character "name")][:td (character "class")][:td (character "race")][:td (character "allignment")]])



(defn character-list []
  (.log js/console "Rendering character list")
  [:div [:table  {:style {:border  1}} [:tr [:th "Name"][:th "Class"][:th "Race"][:th "Alignment"]] (for [character @active-characters]
          [character-item character])]
   ])



(defn list-handler [response]
  "Save the list of player from the get players request. This will kick off rendering the player list"
  (.log js/console (str "Got: " (pr-str response) ))
  (reset! active-characters response))

(defn gm-screen []
  (GET "/characters/active" {:handler list-handler})
  [:div "The GM Screen"])

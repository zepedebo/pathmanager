(ns pathmanager.player
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [reagent-forms.core :refer [bind-fields]]
            [ajax.core :refer [GET POST]]
            [secretary.core :as secretary]
            [goog.events :as events]
            )
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History
           goog.history.EventType))

;; Global State Values
(def player-id (reagent/atom 0))
(def all-players (reagent/atom {}))


(defn player-item [player]
  [:tr {:on-click #(secretary/dispatch! (str "/player/" (player "id")) )} [:td  (player "name")]])

;; Restful handler stuff
(defn error-handler [{:keys [status status-text]}]
  "Deal with HTTP errors"
  (.log js/console
    (str "something bad happened: " status " " status-text)))

(defn new-player-handler [response]
  "Start the process of creating a new player"
  (.log js/console (str "New Player!: " response ))
  (reset! player-id (response "id")))

(defn list-handler [response]
  "Save the list of player from the get players request. This will kick off rendering the player list"
  (.log js/console (str "Got: " (pr-str response) ))
  (reset! all-players response))

(defn show-new-player-list [response]
  "Show a refreshed player list"
  (.log js/console "Showing new players")
  (GET "/players" {:handler list-handler :error-handler error-handler}))


(defn add-player! [player-name]
  "Post a new player to the server"
  (POST "/players"
        {:headers {"Accept" "application/json"}
         :finally show-new-player-list
         :format :edn
         :handler new-player-handler
         :error-handler error-handler
         :params {:name player-name}}))

(defn new-player []
  "Create the new player sub form"
  (let [val (reagent/atom "")]
    (fn []
      [:div
       [:input {:type "text"
                :placeholder "Player Name"
                :value @val
                :on-change #(reset! val (-> % .-target .-value))}]
       [:button {:on-click #(let []
                              (add-player! @val)
                              (reset! val ""))}
        "Add"]])))


(defn player-list []
  "Render a list of all the current players with the option to create a new one"
  [:div [:table [:tr [:th "Player Name"]](for [player @all-players]
          [player-item player])]
   [new-player]])

;(defn player-list []
;  [:div { :on-click #(reset! player-id 1)} "The player list"])

; Force a load of the current player list when the application loads
(GET "/players" {:handler list-handler})



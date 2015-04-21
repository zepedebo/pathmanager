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

;; Render the player list
;(defn goto-player-screen [new-player-id]
;  (reset! player-id new-player-id)
;  (show-character-list))


(defn player-item [player]
  [:div  [:h2 {:class "label label-primary" :on-click #(secretary/dispatch! (str "/player/" (player "id")) )} (player "name")]])

;; Restful handler stuff
(defn error-handler [{:keys [status status-text]}]
  (.log js/console
    (str "something bad happened: " status " " status-text)))

(defn new-player-handler [response]
  (.log js/console (str "New Player!: " response ))
  (reset! player-id (response "id")))

(defn list-handler [response]
  (.log js/console (str "Got: " (pr-str response) ))
  (reset! all-players response))

(defn show-new-player-list [response]
  (.log js/console "Showing new players")
  (GET "/players" {:handler list-handler :error-handler error-handler}))


(defn add-player! [player-name]
  (POST "/players"
        {:headers {"Accept" "application/json"}
         :finally show-new-player-list
         :format :edn
         :handler new-player-handler
         :error-handler error-handler
         :params {:name player-name}}))

(defn new-player []
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
  [:div (for [player @all-players]
    [player-item player])
   [new-player][:button {:type "submit"
                 :class "btn btn-default"
                 :on-click #(secretary/dispatch! "/wut")} "back"]])

;(defn player-list []
;  [:div { :on-click #(reset! player-id 1)} "The player list"])


(GET "/players" {:handler list-handler})



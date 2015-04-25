(ns pathmanager.core
  (:require [pathmanager.player :as player]
            [pathmanager.character :as character]
            [pathmanager.gm :as gm]
            [reagent.core :as reagent :refer [atom]]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.session :as session]
            [reagent-forms.core :refer [bind-fields]]
            [ajax.core :refer [GET POST]]
            )
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History
           goog.history.EventType))



(comment (defn home-page []

  (cond
   (= @player/player-id 0) [player/player-list]
   (= @character/character-id 0) [character/get-character-list @player/player-id]
   :else
   (character/character-sheet @player/player-id @character/character-id)
   )))

(defn show-character-list []
  (.log js/console "Show the list!")
  (session/put! :page character/get-character-list))


(defn page []
  [:div [(session/get :page)]])

(defroute home-path "/" []
  (session/put! :page player/player-list))

(defroute dm-path "/gm" []
  (.log js/console (str "GM"))
  (session/put! :page gm/gm-screen))


(defroute player-page "/player/:id" [id]
  (.log js/console (str "Get charcters for " id))
  (session/put! :player id)
  (session/put! :page character/get-character-list))

(defroute new-character-page "/new-character/:id" [id]
  (.log js/console (str "Get charcters for " id))
  (session/put! :player id)
  (session/put! :page character/character-form))

(defroute character-list "/character-list" []
  (session/put! :page character/character-list))


(defn mount-components []
  (reagent/render-component [page] (.getElementById js/document "app")))

(defn init! []
  (secretary/set-config! :prefix "#")
  (session/put! :page player/player-list)
  (mount-components))

(let [h (History.)]
  (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto h (.setEnabled true)))

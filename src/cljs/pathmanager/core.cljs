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


(def pages
  {:player-list #'player/player-list
   :character-list #'character/character-list
   :character-form #'character/character-form
   :character-sheet #'character/character-sheet
   :gm #'gm/gm-screen})

(defn page []
  (.log js/console "In page")
  [(pages (session/get :page))])

;(defroute home-path "/" []
;  (session/put! :page player/player-list))

(defroute dm-path "/gm" []
  (.log js/console (str "GM"))
  (session/put! :page :gm))


(defroute player-page "/player/:id" [id]
  (.log js/console (str "Get charcters for " id))
  (session/put! :player id)
  (session/put! :page :character-list))

(defroute new-character-page "/new-character/:id" [id]
  (.log js/console (str "Creating charcter for " id))
  (session/put! :player id)
  (session/put! :page :character-form))

(defroute character-list "/character-list" []
  (session/put! :page :character-list))

 (defroute character-sheet "/characters/:id" [id]
   (session/put! :character id)
   (session/put! :page :character-sheet))

 (defroute player-list "/" []
   (session/put! :page :player-list))

(defn mount-components []
  (reagent/render-component [page] (.getElementById js/document "app")))

(defn init! []
  (.log js/console "Resetting")
  (secretary/set-config! :prefix "#")
  (when (nil? (session/get :page))
  (session/put! :page :player-list))
  (mount-components))

(doto (History.)

  (goog.events/listen EventType/NAVIGATE #(secretary/dispatch! (.-token %)))

  (.setEnabled true))

;(let [h (History.)]
;  (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
;  (doto h (.setEnabled true)))

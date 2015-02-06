(ns pathmanager.routes.home
  (:require [compojure.core :refer :all]
            [pathmanager.layout :as layout]
            [pathmanager.util :as util]
            [pathmanager.db.core :as db]))

(defn home-page [& [name message error]]
  (layout/render
    "home.html" {:error error
                 :name name
                 :message message
                 :players (db/get-players)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))

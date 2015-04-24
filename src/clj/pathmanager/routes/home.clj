(ns pathmanager.routes.home
  (:require [pathmanager.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :refer [redirect]]
            [pathmanager.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]))

(defn home-page [{:keys [flash]}]
  (layout/render
    "home.html"
    ))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (GET "/dm" request (home-page request))
;  (POST "/" request (save-message! request))
  (GET "/about" [] (about-page)))

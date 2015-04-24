(ns pathmanager.routes.player
  (:require [pathmanager.layout :as layout]
            [clojure.string :as string]
            [compojure.core :refer [defroutes GET POST ANY]]
            [ring.util.response :refer [redirect]]
            [pathmanager.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [clojure.data.json :as json]
            [liberator.core :refer [resource defresource]]
            [taoensso.timbre :as timbre]))


(defn string-keys-to-keywords [post-info]
  (into {}
        (for [[k v] post-info]
          [(keyword k) v])))



(defroutes player-routes
  (POST "/players" []
        (resource :available-media-types ["application/transit+json" "application/json"]
                  :allowed-methods [:post]
                  :handle-created (fn [context] (println (str "created called" (get context ::instance))) (::instance context))
                  :post! (fn [context]

                            (let [body (read-string (slurp (get-in context [:request :body])))
                                  newplayer (db/add-player<! body)]
                            {::instance  (json/write-str  (read-string (string/replace newplayer #":scope_identity\(\)" "\"id\"")))}
                              ))
                  :respond-with-entity?	true))

  (GET "/players/:id{[0-9]+}" [id]
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-player {:id id})))))


  (GET "/players/:id{[0-9]+}/characters" [id]
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-characters-for-player {:id id})))))

  (GET "/players" []
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-players))))))







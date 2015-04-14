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

(defn validate-player [params]
  (first
    (b/validate
      params
      :name v/required
      )))

(defn player-page [{:keys [flash params]}]
  (let [playerinfo (params :id)
    playerlist {:players (db/get-players)}
    characters {:characters (db/get-characters-for-player {:id playerinfo})}]
  (layout/render
    "player.html"
    (merge  playerlist {:playerid playerinfo} characters
          (select-keys flash [:name :errors]))
   :name (:name flash))))

;(defn add-player! [{:keys [params]}]
;  (if-let [errors (validate-player params)]
;    (-> (redirect "/player")
;        (assoc :flash (assoc params :errors errors)))
;    (do
;      (db/add-player!  params )
;      (redirect "/"))))

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
                            {::instance  (string/replace newplayer #"[\(\)]" "")}
                              ))
                  :respond-with-entity?	true))

  (GET "/players/:id{[0-9]+}/characters" [id]
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-characters-for-player {:id id})))))

  (GET "/players" []
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-players))))))







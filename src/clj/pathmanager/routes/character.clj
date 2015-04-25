(ns pathmanager.routes.character
  (use pathmanager.character)
  (:require [clojure.string :as string]
            [pathmanager.layout :as layout]
            [compojure.core :refer [defroutes GET POST PUT]]
            [ring.util.response :refer [redirect]]
            [pathmanager.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [clojure.data.json :as json]
            [liberator.core :refer [resource defresource]]))


(defn get-race-adjustments [{:keys [params]}]
  (let [adjustments (pathmanager.character/race-adjustment (params :race))]
    (str {:body (json/write-str adjustments)})
    ))

(defresource character-stats [char-id]
  :available-media-types ["application/json"]
  :handle-ok (fn [_] (json/write-str (db/get-info-for-character-id {:id char-id}))))


(defroutes character-routes
  (POST "/characters" []
        (resource :available-media-types ["application/transit+json" "application/json"]
                  :allowed-methods [:post]
                  :handle-created (fn [context] (println (str "created called" (get context ::instance))) (::instance context))
                  :handle-ok (fn [context] (get context ::instance))
                  :post! (fn [context]
                           (let [body (read-string (slurp (get-in context [:request :body])))
                                 newcharacter (db/add-character<! body)]
                             {::instance  (json/write-str  (read-string (string/replace newcharacter #":scope_identity\(\)" "\"id\"")))}
                             )
                           )
                  :respond-with-entity?	true))



  (GET "/characters/:id{[0-9]+}" [id]
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-info-for-character-id {:id id})))))

  (GET "/characters/active" []
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:get]
                 :handle-ok (fn [context] (json/write-str (db/get-active-characters)))))


  (PUT "/characters/:id{[0-9]+}/activate" [id]
       (resource :available-media-types ["application/transit+json" "application/json"]
                 :allowed-methods [:put]
                 :handle-ok (fn [context] (get context ::instance))
                 :put! (fn [context]
                         (db/set-character-active! {:id id})))))

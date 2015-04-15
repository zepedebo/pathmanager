(ns pathmanager.character
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [reagent-forms.core :refer [bind-fields]]
            [ajax.core :refer [GET POST]]
            [pathmanager.player]
            [clojure.string]
            [secretary.core :as secretary]
            [goog.events :as events])
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History
           goog.history.EventType))

(def character-id (reagent/atom 0))
(def all-characters (reagent/atom 0))

(def races ["Dwarf" "Elf" "Gnome" "Half-elf" "Half-orc" "Halfling" "Human"])

(def allignments ["Lawful Good" "Neutral Good" "Chaotic Good"
                  "Lawful Neutral" "Neutral" "Chaotic Neutral"
                  "Lawful Evil" "Neutral Evil" "Chaotic Evil"])

(def dieties ["Erastil" "Iomedae" "Torag" "Sarenrae" "Shelyn" "Desna" "Cayden Cailean"
              "Abadar" "Irori" "Gozreh" "Pharasma" "Nethys" "Gorum" "Calistria"
              "Asmodeus" "Zon-Kuthon" "Urgathoa" "Norgorber" "Lamashtu" "Rovagug"])

(defn list-picker [label source form-id]
  [:div.row
   [:div.col-md-2 [:label label]]
   [:div.col-md-5
    [:select.form-control {:field :list :id form-id}
               (for [item source]
                 [:option {:key item} item])]]])


(defn row [label input]
  [:div.row
    [:div.col-md-2 [:label label]]
    [:div.col-md-5 input]])

(defn attrlist []
  (for [attr ["str" "dex" "con" "int" "wis" "cha"]]
    (row (str (clojure.string/capitalize attr) ":") [:input.form-control {:field :text :id (keyword attr)}])))
;    [:div.form-group
;    [:label  {:class "control-label col-sm-2" :for attr} (str (clojure.string/capitalize attr) ":")]
;     [:div.col-sm-10 [:input.form-control {:field :text :id (keyword attr)}]]]))



(def character-template
  [:div
   (row "Name:" [:input {:field :text :id :name}])
;   (row "Race:" [:select.form-control {:field :list :id :race}
;               (for [item races]
;                 [:option {:key item} item])])
   (list-picker "Race: " races :race)
   (list-picker "Allignment: " allignments :allignment)
   (list-picker "Diety:" dieties :diety)
   (list-picker "Gender:" ["Male" "Female"] :gender)
   (row "Hit points:" [:input {:field :text :id :hit-points}])

;   [:div.form-group [:label {:class "control-label col-sm-2" :for :hit-points} "Hit Points: "] [:div {:class "col-sm-10"}[:input.form-control {:field :text :id :hit-points}]]]
   (attrlist)
   [:button {:on-click #(.log js/console (pr-str))}
        "Create!"]])


(def form-template
  [:div
   (row "first name" [:input {:field :text :id :first-name}])
   (row "last name" [:input {:field :text :id :last-name}])
   (row "age" [:input {:field :numeric :id :age}])
   (row "email" [:input {:field :email :id :email}])
   (row "comments" [:textarea {:field :textarea :id :comments}])])


(defn character-form []
  (let [doc (reagent/atom {:player (session/get :player)})]
    (fn []
      [:div
       [:div.page-header [:h1 "Reagent Form"]]
       [bind-fields character-template doc]
       [:label (str @doc)]])))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console
    (str "something bad happened: " status " " status-text)))

(defn character-item [character]
   [:div  [:h2 {:class "label label-primary" :on-click #(reset! character-id (character "id"))} (character "name")]])

(defn show-new-character-list []
  [:div "New list"])

(defn new-character-handler []
  [:div "handle new character"])

(defn add-character! [name]
  (POST "/characters/"
        {:headers {"Accept" "application/json"}
         :finally show-new-character-list
         :format :edn
         :handler new-character-handler
         :error-handler error-handler
         :params {:name name}}))


(defn new-character []
  (let [val (reagent/atom "")]
    (fn []
      [:div

       [:button {:on-click #(let []
                              (character-form )
                              )}
        "New Charcter"]])))


(defn character-list []
  (.log js/console "Rendering character list")
  [:div (for [character @all-characters]
    [character-item character])
   [new-character]])


(defn list-handler [response]
  (.log js/console (str "Got: " (pr-str response)))
  (if (empty? response)
    (let [target (str "/new-character/" (session/get :player))]
      (.log js/console (str "empty going to " target)) (secretary/dispatch! target))
    (let [] (.log js/console "not empty") (reset! all-characters response))))

(defn get-character-list []
  (let [player-id (session/get :player)]
  (GET (str "/players/" player-id "/characters") {:handler list-handler :error-handler error-handler})
   [:div (str "Getting character list for " player-id)]))

;(defn character-list [pid]
 ; [:div { :on-click #(reset! character-id 1)} (str "The character list for " pid)])

(defn character-sheet [pid cid]
  [:div (str "Character " cid " for player " pid)])




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

(defn attrib-adjustment [attrval]
  (let [adjattr (if (< attrval 10) (- attrval 1) attrval)]
    (-> (/ adjattr 2) (- 5) int )))

(defn race-adjustment [race]
  (case race
    "dwarf" {:con 2 :wis 2 :cha -2}
    "elf" {:dex 2 :int 2 :con -2}
    "gnome" {:con 2 :cha 2 :str -1}
    "halfling" {:dex 2 :cha 2 :str -2}
    {}))



(def character-id (reagent/atom 0))
(def all-characters (reagent/atom {}))

(def races ["Dwarf" "Elf" "Gnome" "Half-elf" "Half-orc" "Halfling" "Human"])

(def allignments ["Lawful Good" "Neutral Good" "Chaotic Good"
                  "Lawful Neutral" "Neutral" "Chaotic Neutral"
                  "Lawful Evil" "Neutral Evil" "Chaotic Evil"])

(def dieties ["Erastil" "Iomedae" "Torag" "Sarenrae" "Shelyn" "Desna" "Cayden Cailean"
              "Abadar" "Irori" "Gozreh" "Pharasma" "Nethys" "Gorum" "Calistria"
              "Asmodeus" "Zon-Kuthon" "Urgathoa" "Norgorber" "Lamashtu" "Rovagug"])

(def classes ["Barbarian" "Bard" "Cleric" "Druid" "Fighter" "Monk"
              "Paladin" "Ranger" "Rogue" "Sorcerer" "Wizard"])



(defn list-picker [label source form-id]
  "Render a list picker for things like race and alignment and bind the value to an ID"
  [:div.row
   [:div.col-md-2 [:label label]]
   [:div.col-md-3
    [:select.form-control {:field :list :id form-id}
     (for [item source]
       [:option {:key item} item])]]])


(defn row [label input]
  "Render a single row for a simple text input with a label and bind it to an ID"
  [:div.row
    [:div.col-md-2 [:label label]]
    [:div.col-md-5 input]])

(defn attrlist []
  "Render the main character ability score list"
  (for [attr ["str" "dex" "con" "int" "wis" "cha"]]
    (conj (row (str (clojure.string/capitalize attr) ":") [:input.form-control {:field :numeric :id (keyword attr)}] )
          [:div.col-md-2 [:label {:field :numeric :id (keyword (str "race-adjustments." attr))}]])))



(def character-template
  [:div
   (row "Name:" [:input {:field :text :id :name}])
   (list-picker "Race: " races :race)
   (list-picker "Allignment: " allignments :alignment)
   (list-picker "Diety:" dieties :diety)
   (list-picker "Gender:" ["Male" "Female"] :gender)
   (list-picker "Class:" classes :class)
   (row "Hit points:" [:input {:field :text :id :hitpoints}])

   (attrlist)])


(defn error-handler [{:keys [status status-text]}]
  "Handle errors from HTTP requests"
  (.log js/console
    (str "something bad happened: " status " " status-text)))


(defn show-new-character-list []
  [:div "New list"])

(defn new-character-handler []
  [:div "handle new character"])

(defn add-character! [new-char]
  "Post a new character to the server"
  (let [adjs (new-char :race-adjustments)
        adj-char (dissoc (merge-with + new-char adjs) :race-adjustments)]
    (POST "/characters"
          {:headers {"Accept" "application/json"}
           :finally show-new-character-list
           :format :edn
           :handler new-character-handler
           :error-handler error-handler
           :params  (assoc adj-char :player (session/get :player))})))



(defn character-form []
  "Render a form to create a new character"
  (let [doc (reagent/atom {:player (session/get :player)})]
    (fn []
      [:div
       [:div.page-header [:h1 "Reagent Form"]]
       [bind-fields
        character-template
        doc
        (fn [id value {:keys [race] :as doc}]
          (when (some #{:race} id)
            (let [adjs (race-adjustment (clojure.string/lower-case value))]
              (assoc-in doc [:race-adjustments] adjs))))]
       [:label (str @doc)]
       [:button.btn.btn-default
         {:on-click
          #(add-character! @doc)}
         "save"]])))



(defn new-character []
  (let [val (reagent/atom "")]
    (fn []
      [:div

       [:button {:on-click #(let [player-id (session/get :player)]
                              (secretary/dispatch! (str "/new-character/" player-id) )
                              )}
        "New Charcter"]])))

(defn character-item [character]
  "Display one character"
   [:div  [:h2 {:class "label label-primary" :on-click #(reset! character-id (character "id"))} (str (character "name") " : " (character "class"))]])


(defn list-handler [response]
  "When the character list is sent to the client, update the UI with the list"
  (.log js/console (str "Got: " (pr-str response)))
  (if (empty? response)
    (let [target (str "/new-character/" (session/get :player))]
      (.log js/console (str "empty going to " target))
      (secretary/dispatch! target))
    (let [] (reset! all-characters response)
      (.log js/console (str "loading list"))
      (secretary/dispatch! "/character-list"))))


(defn character-list []
  (.log js/console "Rendering character list")
  [:div (for [character @all-characters]
          [character-item character])
   [new-character]])



(defn get-character-list []
  "Send a request to the server to give us the character list for the current player"
  (let [player-id (session/get :player)]
    (.log js/console "Sending request")
    (GET (str "/players/" player-id "/characters") {:handler list-handler :error-handler error-handler})
    [:div (str "Getting full character list for " player-id)]))




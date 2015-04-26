(ns pathmanager.character
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [reagent-forms.core :refer [bind-fields]]
            [ajax.core :refer [GET POST PUT]]
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
(def character-info (reagent/atom nil))
(def all-characters (reagent/atom nil))

(def races ["Dwarf" "Elf" "Gnome" "Half-elf" "Half-orc" "Halfling" "Human"])

(def alignments ["Lawful Good" "Neutral Good" "Chaotic Good"
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
          [:div.col-md-2 [:label {:field :label :id (keyword (str "race-adjustments." attr))}]])))



(def character-template
  [:div
   (row "Name:" [:input {:field :text :id :name}])
   (list-picker "Race: " races :race)
   (list-picker "Alignment: " alignments :alignment)
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
  (reset! all-characters nil)
  (secretary/dispatch! (str "/player/" (session/get :player)) ))

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

(defn back-to-list []
    (PUT (str "/characters/" (session/get :character) "/active/false")
            {:headers {"Accept" "application/json"}
             :finally #(secretary/dispatch! "/character-list")
             :format :edn
;             :handler new-character-handler
             :error-handler error-handler
             :params  {}}))

(defn to-list-button []
  [:div
   [:button.btn.btn-default {:on-click #(back-to-list)}
    "Cancel"]])


(defn cancel-button []
  (let [val (reagent/atom "")]
    (fn []
      [:div
       [:button.btn.btn-default {:on-click #(secretary/dispatch! "/character-list")}
        "Cancel"]])))


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
       [:button.btn.btn-default
         {:on-click
          #(add-character! @doc)}
         "save"][cancel-button]])))



(defn new-character []
  (let [val (reagent/atom "")]
    (fn []
      [:div

       [:button {:on-click #(let [player-id (session/get :player)]
                              (secretary/dispatch! (str "/new-character/" player-id) )
                              )}
        "New Charcter"]])))


(defn select-character [char-id]
  (.log js/console (str "Selected " char-id))
  (session/put! :character char-id)
  (PUT (str "/characters/" char-id "/active/true")
            {:headers {"Accept" "application/json"}
             :finally #(secretary/dispatch! (str "/characters/" char-id))
             :format :edn
;             :handler new-character-handler
             :error-handler error-handler
             :params  {}}))
(defn hello []
  [:div "Hello from character"])

;(defn character-item [character]
;  "Display one character"
;   [:div  [:h2 {:class "label label-primary" :on-click #(reset! character-id (character "id"))} (str (character "name") " : " (character "class"))]])

(defn character-item [character]
  [:tr {:on-click #(select-character (character "id"))} [:td (character "name")][:td (character "class")][:td (character "race")][:td (character "alignment")]])


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

(defn get-character-list []
  "Send a request to the server to give us the character list for the current player"
  (let [player-id (session/get :player)]
    (.log js/console "Sending request")
    (GET (str "/players/" player-id "/characters") {:handler list-handler :error-handler error-handler})
    [:div (str "Getting full character list for " player-id)]))


(defn character-list []
  (.log js/console "Rendering character list")
  [:div (if (nil? @all-characters)
          (get-character-list))

   [:table  {:style {:border  1}}
    [:tr [:th "Name"][:th "Class"][:th "Race"][:th "Alignment"]]
    (for [character @all-characters]
      [character-item character])]
   [new-character]])


(defn character-handler [response]
  (reset! character-info response))

(defn cs-line [label value]
  [:div (str label value)])

(defn character-sheet []
  (.log js/console (str "Getting character sheet for " (session/get :character)))
  (if (nil? @character-info)
    (let [] (GET (str "/characters/" (session/get :character))
                 {:handler character-handler
                  :error-handler error-handler})
      [:div "Fetching character"])
    [:div (for [character @character-info]
            [:div
             (cs-line "Name: " (character "name"))
             (cs-line "Class: " (character "class"))
             (cs-line "Race: " (character "race"))
             (cs-line "Alignment: " (character "alignment"))
             (cs-line "Diety: " (character "diety"))
             (cs-line "Gender: " (character "gender"))
             (cs-line "Class: " (character "class"))
             (cs-line "Level: " (character "level"))
             (cs-line "Hit Points: " (character "hitpoints"))
             (for [attr ["str" "dex" "con" "int" "wis" "cha"]] (cs-line (str (clojure.string/capitalize attr) ": ") (character attr)))
             ])[to-list-button]]))


     ;   (list-picker "Diety:" dieties :diety)
     ;   (list-picker "Gender:" ["Male" "Female"] :gender)
     ;   (list-picker "Class:" classes :class)
     ;   (row "Hit points:" [:input {:field :text :id :hitpoints}])
   ;  ]))




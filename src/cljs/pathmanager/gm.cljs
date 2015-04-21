(ns pathmanager.gm
  (:require [reagent.core :as reagent :refer [atom]][reagent.session :as session]
            [reagent-forms.core :refer [bind-fields]]
            [ajax.core :refer [GET POST]]))

(defn gm-screen []
  [:div "The GM Screen"])

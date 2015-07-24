(ns gg.zfo.www.handlers
  (:require [re-frame.core :as re-frame]
            [gg.zfo.www.db :as db]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]))


(trace-forms
  {:tracer (tracer :color "green")}

  (defn initialize-db
    [_ _]
    db/default-db)

  (defn set-active-panel
    [db [_ active-panel]]
    (assoc db :active-panel active-panel)))


(defonce handlers
  (do
    (re-frame/register-handler :initialize-db
                                initialize-db)

    (re-frame/register-handler :set-active-panel
                                set-active-panel)))


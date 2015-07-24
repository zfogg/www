(ns gg.zfo.www.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [clairvoyant.core :refer-macros [trace-forms]]
            [re-frame-tracer.core :refer [tracer]]))


(trace-forms
  {:tracer (tracer :color "brown")}

  (defn my-name [db]
    (reaction (:my-name @db)))

  (defn active-panel [db _]
    (reaction (:active-panel @db))))


(defonce handlers
  (do
    (re-frame/register-sub :my-name
                            my-name)

    (re-frame/register-sub :active-panel
                            active-panel)))

